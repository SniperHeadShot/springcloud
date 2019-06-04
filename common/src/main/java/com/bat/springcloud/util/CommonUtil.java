package com.bat.springcloud.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName CommonUtil
 * @Description 工具类
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:54
 **/
public class CommonUtil {

    /**
     * @Param []
     * @Return void
     * @Author ZhengYu
     * @Description: 获取UUID
     * @Date 2019/5/27
     */
    public static String getRandomUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Param [text]
     * @Return java.lang.String
     * @Author ZhengYu
     * @Description: 使用 MD5 加密字符串
     * @Date 2019/5/27
     */
    public static String md5Encrypt(String text) {
        return DigestUtils.md5Hex(text);
    }

    /**
     * @Param [text]
     * @Return java.lang.String
     * @Author ZhengYu
     * @Description: 使用 Base64 进行加密
     * @Date 2019/5/27
     */
    public static String base64Encrypt(String text) {
        return new String(Base64.encodeBase64(text.getBytes(), true));
    }

    /**
     * @Param [base64Code]
     * @Return java.lang.String
     * @Author ZhengYu
     * @Description: 对 Base64 加密的字符串进行解密
     * @Date 2019/5/27
     */
    public static String base64Decrypt(String base64Code) {
        return new String(Base64.decodeBase64(base64Code.getBytes()));
    }
}
