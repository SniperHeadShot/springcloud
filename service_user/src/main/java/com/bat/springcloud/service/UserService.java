package com.bat.springcloud.service;

import com.bat.springcloud.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUserList(String currentPage, String pageSize, User user);

    User getUser(String userId);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(String userId);
}
