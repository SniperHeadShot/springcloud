package com.bat.springcloud.service.impl;

import com.bat.springcloud.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/27 13:47
 **/
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;
}
