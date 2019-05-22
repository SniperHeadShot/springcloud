package com.bat.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {

    private Long id;
    private String username;
    private String password;
    // 账户是否过期 1未过期 0已过期
    private Integer isExpired;
    // 账户是否被锁定 1未锁定 0已锁定
    private Integer isLock;
    // 用户凭证已过期 1未过期 0已过期
    private Integer isCredential;
    // 用户是否被禁用 1未禁用 0已禁用
    private Integer isEnabled;
}
