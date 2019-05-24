package com.bat.springcloud.response;

import com.bat.springcloud.annotation.CodeEntity;

/**
 * @ClassName CodeEnumInterface
 * @Description 通过反射将注解值读出来
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:17
 **/
public interface CodeEnumInterface {

    default CodeEntity codeEntity() {
        try {
            return this.getClass().getField(this.toString()).getAnnotation(CodeEntity.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    default boolean success() {
        return this.codeEntity().success();
    }

    default int errCode() {
        return this.codeEntity().errCode();
    }

    default String msg() {
        return codeEntity().msg();
    }
}
