package com.bat.commoncode.entity;

import lombok.Data;

/**
 * 数据源信息
 *
 * @author ZhengYu
 * @version 1.0 2019/12/13 15:58
 **/
@Data
public class DataSource {

    /**
     * 数据源唯一标识
     */
    private String uniqueKey;

    private String driverClass;

    private String host;

    private Integer port;

    private String username;

    private String password;

    /**
     * 库
     */
    private String aimDb;
}

