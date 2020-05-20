package com.bat.feign.client.fallback;

import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.enums.ConstantEnum;
import com.bat.commoncode.response.CommonResult;
import com.bat.feign.client.CustomClient;
import org.springframework.stereotype.Component;

/**
 * CustomClient Fallback
 *
 * @author ZhengYu
 * @version 1.0 2020/5/19 10:16
 **/
@Component
public class CustomClientFallback implements CustomClient {

    /**
     * 测试基础的feign功能
     *
     * @param resourceUuid 资源UUID
     * @param param        参数
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @Override
    public CommonResult getFeignStruct(String resourceUuid, String param) {
        return CommonResult.buildCommonResult(ConstantEnum.REMOTE_CALL_FAIL);
    }

    @Override
    public CommonResult feignGetPojo(CustomStructure customStructure) {
        return CommonResult.buildCommonResult(ConstantEnum.REMOTE_CALL_FAIL);
    }
}
