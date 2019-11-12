package com.bat.eureka.ribbon.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.response.CommonResult;
import com.bat.eureka.ribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 内部负载均衡接口
 *
 * @author ZhengYu
 * @version 1.0 2019/11/12 11:28
 **/
@RestController
public class ClientRibbonApi {

    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/custom")
    public String custom() {
        CustomStructure customStructure = new CustomStructure("zy", 21);
        return JSONObject.toJSONString(
                new ArrayList<CommonResult>(4) {{
                    add(ribbonService.customGet());
                    add(ribbonService.customPost(customStructure));
                    add(ribbonService.customPut(customStructure));
                    add(ribbonService.customDelete(customStructure));
                }}
                , SerializerFeature.DisableCircularReferenceDetect
        );
    }
}
