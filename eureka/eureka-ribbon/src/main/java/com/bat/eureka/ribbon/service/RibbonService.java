package com.bat.eureka.ribbon.service;

import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.response.CommonResult;

/**
 * Ribbon Service
 *
 * @author ZhengYu
 * @version 1.0 2019/11/12 10:44
 **/
public interface RibbonService {

    /**
     * GET 方法
     *
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    CommonResult customGet();

    /**
     * POST 方法
     *
     * @param customStructure POST
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    CommonResult customPost(CustomStructure customStructure);

    /**
     * PUT 方法
     *
     * @param customStructure PUT
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    CommonResult customPut(CustomStructure customStructure);

    /**
     * DELETE 方法
     *
     * @param customStructure DELETE
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    CommonResult customDelete(CustomStructure customStructure);
}
