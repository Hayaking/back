package com.example.demo;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){   //在本地window环境测试时用localhost
            System.out.println("this is  windows");
            config.setHostname("localhost");
        } else {
//            config.setHostname("129.204.82.119");   //部署到你的远程服务器正式发布环境时用服务器公网ip
            config.setHostname("localhost");

        }
        config.setPort(9092);

		/*config.setAuthorizationListener(new AuthorizationListener() {//类似过滤器
			@Override
			public boolean isAuthorized(HandshakeData data) {
				//http://localhost:8081?username=test&password=test
				//例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
				// String username = data.getSingleUrlParam("username");
				// String password = data.getSingleUrlParam("password");
				return true;
			}
		});*/


        final SocketIOServer server = new SocketIOServer(config);

        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}
