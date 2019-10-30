package com.bat.commoncode.response;

import com.bat.commoncode.annotation.CodeEntity;

/**
 * 通过反射将注解值读出来
 *
 * @author ZhengYu
 * @version 1.0 2019/6/22 16:39
 **/
public interface CodeEnumInterface {

    /**
     * 使用反射获取注解信息
     *
     * @return CodeEntity
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
     * @return java.lang.String
     * @author ZhengYu
     * @date 2019/6/4
     */
    default String msg() {
        return codeEntity().msg();
    }
}
