package com.bat.feign.clients.fallback;

import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.enums.ConstantEnum;
import com.bat.commoncode.response.CommonResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * FallbackFactory
 *
 * @author ZhengYu
 * @version 1.0 2019/10/31 16:49
 **/
@Component
public class CustomClientFallbackFactory implements FallbackFactory<CustomMiddleFallback> {

    private static final CommonResult DEFAULT_FALLBACK_COMMON_RESULT = CommonResult.buildCommonResult(ConstantEnum.REMOTE_CALL_FAIL);

    @Override
    public CustomMiddleFallback create(Throwable throwable) {
        return new CustomMiddleFallback() {
            @Override
            public CommonResult customGet() {
                return DEFAULT_FALLBACK_COMMON_RESULT;
            }

            @Override
            public CommonResult customPost(CustomStructure customStructure) {
                return DEFAULT_FALLBACK_COMMON_RESULT;
            }

            @Override
            public CommonResult customPut(CustomStructure customStructure) {
                return DEFAULT_FALLBACK_COMMON_RESULT;
            }

            @Override
            public CommonResult customDelete(CustomStructure customStructure) {
                return DEFAULT_FALLBACK_COMMON_RESULT;
            }
        };
    }
}
