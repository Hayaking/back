package com.example.demo;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;

import com.corundumstudio.socketio.annotation.OnEvent;
import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.Message;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.demo.pojo.Message.TYPE.*;


@Component
public class MessageEventHandler {
    private static SocketIOServer socketIoServer;
    static ArrayList<UUID> listClient = new ArrayList<>();
    static final int limitSeconds = 60;
    private static HashMap<String, UUID> poll = new HashMap<>();
    @Resource
    private UserRepository userRepository;
    private static Gson gson = new Gson();
    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        socketIoServer = server;
    }

    @OnConnect
    public void onConnect(SocketIOClient client) {
        listClient.add(client.getSessionId());
        System.out.println("客户端:" + client.getSessionId() + "已连接");

//        poll.put()
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("客户端:" + client.getSessionId() + "断开连接");
//        for (Map.Entry<String, UUID> entry : poll.entrySet()) {
//            if (client.getSessionId().equals(entry.getValue())) {
//                poll.remove(entry.getKey());
//            }
//        }
    }
    /**收到消息，将name作为key，uuid作为val，存入poll中
     * @param client
     * @param request
     * @param message
     */
    @OnEvent(value = "sendMessage")
    public void onEvent(SocketIOClient client, AckRequest request, Message message) {
        UUID uuid = poll.get(message.getAccount());
        System.out.println("客户端:" + client.getSessionId() + " 发来消息：" + message.toString());
//        System.out.println(uuid==null);
//        System.out.println(uuid.toString());
//        System.out.println(client.getSessionId().toString());
//        System.out.println(client.getSessionId().equals(uuid));
        System.out.println(client.getSessionId().equals(uuid));
        if (uuid == null || !client.getSessionId().equals(uuid)) {
            System.out.println("uuid?");
        } else {
            message.setType(RECEIVED);
            sendBuySingleEvent(message,client.getSessionId());
        }
    }

    @OnEvent(value = "loginMessage")
    public void login(SocketIOClient client, AckRequest request, Message message) {
        System.out.println("客户端:" + client.getSessionId() + " 发来消息：" + message.toString());
        Message res;
        if (userRepository.queryUserByNameAndPsw(message.getAccount(), message.getPassword())!= null) {
            poll.put(message.getAccount(), client.getSessionId());
            res = new Message()
                    .setAccount(message.getAccount())
                    .setType(LOGIN_SUCCESS);
        } else {
            res = new Message()
                    .setAccount(message.getAccount())
                    .setType(LOGIN_FAILED);
        }
        sendBuySingleEvent(res,client.getSessionId());
    }
//
//    //群聊,向所有id不为null的推送消息
//    public static void sendBuyLogEvent(String text) {   //这里就是向客户端推消息了
//        for (UUID clientId : listClient) {
//            if (socketIoServer.getClient(clientId) == null) continue;
//            socketIoServer.getClient(clientId).sendEvent("receiveSelfMessage", text, 1);
//        }
//    }
//
    //单聊,向指定id推送消息
    public static void sendBuySingleEvent(String json) {
        Message message = gson.fromJson(json, Message.class);
        UUID uuid = poll.get(message.getAccount());
        SocketIOClient client = socketIoServer.getClient(uuid);
        if (client != null) {
            client.sendEvent("receiveMessage", json, 1);
        }
    }
    //单聊,向指定id推送消息
    public static void sendBuySingleEvent(Message message) {
        UUID uuid = poll.get(message.getAccount());
        SocketIOClient client = socketIoServer.getClient(uuid);
        if (client != null) {
            client.sendEvent("receiveMessage", gson.toJson(message), 1);
            System.out.println(gson.toJson(message));
        }
    }
    //单聊,向指定id推送消息
    public static void sendBuySingleEvent(Message message,UUID uuid) {
        SocketIOClient client = socketIoServer.getClient(uuid);
        if (client != null) {
            client.sendEvent("receiveMessage", gson.toJson(message));
            System.out.println(gson.toJson(message));
        }
    }

}
