package com.bat.commoncode.entity;

import lombok.Data;

/**
 * 用于测试的结构体
 *
 * @author ZhengYu
 * @version 1.0 2019/10/31 17:32
 **/
@Data
public class CustomStructure {

    public CustomStructure() {
    }

    public CustomStructure(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    private Long id;

    private String username;

    private Integer age;
}
