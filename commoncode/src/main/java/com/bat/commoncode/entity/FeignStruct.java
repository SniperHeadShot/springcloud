package com.bat.commoncode.entity;

import lombok.Data;

/**
 * Feign 模块测试结构体
 *
 * @author ZhengYu
 * @version 1.0 2020/5/19 9:54
 **/
@Data
public class FeignStruct {

    /**
     * 某个资源的ID
     */
    private String resourceUuid;

    /**
     * 服务的端口
     */
    private Integer serverPort;

    /**
     * param
     */
    private String param;
}
