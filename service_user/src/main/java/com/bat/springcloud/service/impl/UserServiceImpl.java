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
        for (int i = 0; i < 5; i++) {
            UserDO userDO = new UserDO();
            userDO.setId(atomicLong.incrementAndGet());
            userDO.setUsername(CommonUtil.getRandomUsername());
            userDO.setPassword("111111");
            userDO.setIsCredential(1);
            userDO.setIsEnabled(1);
            userDO.setIsExpired(1);
            userDO.setIsLock(1);
            userDOList.add(userDO);
        }
        return userDOList;
    }

    @Override
    public UserDO getUser(String userId) {
        UserDO userDO = new UserDO();
        userDO.setId(atomicLong.incrementAndGet());
        userDO.setUsername(CommonUtil.getRandomUsername());
        userDO.setPassword("111111");
        userDO.setIsCredential(1);
        userDO.setIsEnabled(1);
        userDO.setIsExpired(1);
        userDO.setIsLock(1);
        return userDO;
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
