package com.bat.springcloud.service.impl;

import com.bat.springcloud.domain.UserDO;
import com.bat.springcloud.request.UserDORequest;
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
    public List<UserDO> getUserList(UserDORequest userDORequest) {
        List<UserDO> userDOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userDOList.add(new UserDO(atomicLong.incrementAndGet(), CommonUtil.getRandomUsername(), "111111", 1, 1, 1, 1));
        }
        userDOList.add(new UserDO(atomicLong.incrementAndGet(), CommonUtil.getRandomUsername(), "111111", 0, 0, 0, 0));
        return userDOList;
    }

    @Override
    public UserDO getUser(String userId) {
        return new UserDO(atomicLong.incrementAndGet(), "YuShen", "111111", 1, 1, 1, 1);
    }

    @Override
    public Boolean addUser(UserDO userDO) {
        return CommonUtil.getRandomBooleanResult();
    }

    @Override
    public Boolean updateUser(UserDO userDO) {
        return CommonUtil.getRandomBooleanResult();
    }

    @Override
    public Boolean deleteUser(String userId) {
        return CommonUtil.getRandomBooleanResult();
    }
}
