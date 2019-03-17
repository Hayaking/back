package com.example.demo.controller;

import com.example.demo.MessageEventHandler;
import com.example.demo.dao.GroupRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class UserController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private GroupRepository groupRepository;

    @GetMapping("/users")
    public Object users(String token,String name) {
        if (token.equals(MessageEventHandler.poll.get(name).toString())) {
            Map<String, List> map = new HashMap<>();
            List<Object> list = new LinkedList<>();
            list.addAll(groupRepository.findAllByUserName(name));
            list.addAll(userRepository.findAll());

            map.put("users", list);
            return map;
        }
        return null;
    }
}
