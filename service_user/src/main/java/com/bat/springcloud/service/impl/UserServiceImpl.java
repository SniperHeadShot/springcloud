package com.bat.springcloud.service.impl;

import com.bat.springcloud.domain.User;
import com.bat.springcloud.service.UserService;
import com.bat.springcloud.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    private final AtomicLong atomicLong = new AtomicLong();

    @Override
    public List<User> getUserList(String currentPage, String pageSize, User user) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(new User(atomicLong.incrementAndGet(), CommonUtil.getRandomUsername(), "111111", 1, 1, 1, 1));
        }
        userList.add(new User(atomicLong.incrementAndGet(), CommonUtil.getRandomUsername(), "111111", 0, 0, 0, 0));
        return userList;
    }

    @Override
    public User getUser(String userId) {
        return new User(atomicLong.incrementAndGet(), "YuShen", "111111", 1, 1, 1, 1);
    }

    @Override
    public Boolean addUser(User user) {
        return CommonUtil.getRandomBooleanResult();
    }

    @Override
    public Boolean updateUser(User user) {
        return CommonUtil.getRandomBooleanResult();
    }

    @Override
    public Boolean deleteUser(String userId) {
        return CommonUtil.getRandomBooleanResult();
    }
}
