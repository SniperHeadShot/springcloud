package com.bat.springcloud.entity;

import lombok.Data;

@Data
public class UserDO {
    private Integer accountUuid;

    private String sex;

    private String birthday;

    private String address;
}