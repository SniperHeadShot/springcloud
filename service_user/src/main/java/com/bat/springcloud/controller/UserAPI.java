package com.bat.springcloud.controller;

import com.bat.springcloud.domain.UserDO;
import com.bat.springcloud.request.UserDORequest;
import com.bat.springcloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "UserAPI", tags = {"user-base-api"}, description = "用户 - 基础CRUD相关接口")
@RestController
@RefreshScope //使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中
public class UserAPI {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userDORequest", value = "人员检索条件", paramType = "query", dataType = "UserDORequest")})
    @GetMapping("/user/list")
    public List<UserDO> getUserList(UserDORequest userDORequest) {
        return userService.getUserList(userDORequest);
    }

    @GetMapping("/user")
    public UserDO getUser(String userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/user")
    public Boolean addUser(UserDO userDO) {
        return userService.addUser(userDO);
    }

    @PutMapping("/user")
    public Boolean updateUser(UserDO userDO) {
        return userService.updateUser(userDO);
    }

    @DeleteMapping("/user")
    public Boolean deleteUser(String userId) {
        return userService.deleteUser(userId);
    }
}
