package com.example.demo.controller;

import com.example.demo.MessageEventHandler;
import com.example.demo.dao.GroupRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.Contact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class ContactController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private GroupRepository groupRepository;

    /**返回联系人和群组
     * @param token
     * @param name
     * @return
     */
    @GetMapping("/contacts")
    public Object users(String token,String name) {
        if (token.equals(MessageEventHandler.poll.get(name).toString())) {
            Map<String, List<Contact>> map = new HashMap<>();
            List<Contact> list = new LinkedList<>();
            list.addAll(groupRepository.findAllByUserName(name));
            list.addAll(userRepository.findAll());
            map.put("contacts", list);
            return map;
        }
        return "";
    }
    @GetMapping("/search")
    public Object search(String token,String name,String search) {
        if (token.equals(MessageEventHandler.poll.get(name).toString())) {
            return userRepository.queryUserByName(search).setPsw(null).toString();
        }
        return null;
    }
}
