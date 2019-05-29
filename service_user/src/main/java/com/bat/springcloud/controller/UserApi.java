package com.bat.springcloud.controller;

import com.bat.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserApi
 * @Description 用户信息服务
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:48
 **/
@Slf4j
@RestController
public class UserApi {

    @Autowired
    private UserService userService;
}
