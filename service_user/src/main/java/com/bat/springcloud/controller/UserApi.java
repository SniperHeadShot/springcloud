package com.bat.springcloud.controller;

import com.bat.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;


}
