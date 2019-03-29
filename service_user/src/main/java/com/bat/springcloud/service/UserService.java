package com.bat.springcloud.service;

import com.bat.springcloud.domain.User;

import java.util.List;

public interface UserService {

    public List<User> getUserList(String currentPage, String pageSize, User user);

    public User getUser(String userId);

    public Boolean addUser(User user);

    public Boolean updateUser(User user);

    public Boolean deleteUser(String userId);
}
