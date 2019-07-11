package com.bat.springcloud.user.service.impl;

import com.bat.springcloud.user.dao.UserDao;
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
