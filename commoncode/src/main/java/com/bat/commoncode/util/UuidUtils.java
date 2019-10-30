package com.bat.commoncode.util;

import java.util.UUID;

/**
 * UUID 生产器
 *
 * @author ZhengYu
 * @version 1.0 2019/6/22 16:39
 **/
public class UuidUtils {

    /**
     * 获取UUID
     *
     * @return java.lang.String
     * @author ZhengYu
     */
    public static String getRandomUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
