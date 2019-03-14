package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    private UserRepository userRepository;

    @GetMapping("/users")
    public Object users() {
        Map<String, List<User>> map = new HashMap<>();
        map.put("users", userRepository.findAll());
        return map;
    }
}
