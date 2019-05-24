package com.bat.springcloud.entity;

import lombok.Data;

@Data
public class AccountDO {
    private Long accountId;

    private String accountUuid;

    private String accountName;

    private String accountPassword;

    private Boolean accountExpired;

    private Boolean locked;

    private Boolean credentialsExpired;

    private Boolean enabled;
}