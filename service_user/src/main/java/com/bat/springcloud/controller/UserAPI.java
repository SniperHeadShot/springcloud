package com.bat.springcloud.controller;

import com.bat.springcloud.domain.User;
import com.bat.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope //使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中
public class UserAPI {

    @Autowired
    private UserService userService;

    @Value("${spring1.application1.name1}")
    private String spring2;

    @GetMapping("/test")
    public String test() {
        return spring2;
    }

    @GetMapping("/user/list")
    public List<User> getUserList(@RequestParam(value = "currentPage") String currentPage, @RequestParam(value = "pageSize") String pageSize, User user) {
        return userService.getUserList(currentPage, pageSize, user);
    }

    @GetMapping("/user")
    public User getUser(String userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/user")
    public Boolean addUser(User user) {
        return userService.addUser(user);
    }

    @PutMapping("/user")
    public Boolean updateUser(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user")
    public Boolean deleteUser(String userId) {
        return userService.deleteUser(userId);
    }
}
