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

    /**
     * 使用反射获取注解信息
     *
     * @param
     * @return com.bat.springcloud.annotation.CodeEntity
     * @author ZhengYu
     * @date 2019/6/4
     */
    default CodeEntity codeEntity() {
        try {
            return this.getClass().getField(this.toString()).getAnnotation(CodeEntity.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否成功
     *
     * @param
     * @return boolean
     * @author ZhengYu
     * @date 2019/6/4
     */
    default boolean success() {
        return this.codeEntity().success();
    }

    /**
     * 状态码
     *
     * @param
     * @return int
     * @author ZhengYu
     * @date 2019/6/4
     */
    default int errCode() {
        return this.codeEntity().errCode();
    }

    /**
     * 提示信息
     *
     * @param
     * @return java.lang.String
     * @author ZhengYu
     * @date 2019/6/4
     */
    default String msg() {
        return codeEntity().msg();
    }
}
