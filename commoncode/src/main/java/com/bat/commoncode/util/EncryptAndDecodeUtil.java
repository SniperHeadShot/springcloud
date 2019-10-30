package com.bat.commoncode.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密解密工具类
 *
 * @author ZhengYu
 * @version 1.0 2019/6/22 16:39
 **/
public class EncryptAndDecodeUtil {

    /**
     * 使用 MD5 加密字符串
     *
     * @return java.lang.String
     * @author ZhengYu
     */
    public static String md5Encrypt(String text) {
        return DigestUtils.md5Hex(text);
    }

    /**
     * 使用 Base64 进行加密
     *
     * @return java.lang.String
     * @author ZhengYu
     */
    public static String base64Encrypt(String text) {
        return new String(Base64.encodeBase64(text.getBytes(), true));
    }

    /**
     * 对 Base64 加密的字符串进行解密
     *
     * @return java.lang.String
     * @author ZhengYu
     */
    public static String base64Decrypt(String base64Code) {
        return new String(Base64.decodeBase64(base64Code.getBytes()));
    }
}
