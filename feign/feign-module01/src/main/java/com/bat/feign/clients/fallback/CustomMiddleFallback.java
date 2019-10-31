package com.bat.feign.clients.fallback;

import com.bat.feign.clients.CustomClient;

/**
 * FallbackFactory 中间接口： 利用接口的多继承特性达到减少代码冗余的目的
 *
 * @author ZhengYu
 * @version 1.0 2019/10/31 17:09
 **/
public interface CustomMiddleFallback extends CustomClient {
}
