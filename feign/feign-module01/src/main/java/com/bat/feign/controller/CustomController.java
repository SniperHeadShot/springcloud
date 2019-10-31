package com.bat.feign.controller;

import com.alibaba.fastjson.JSONObject;
import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.response.CommonResult;
import com.bat.feign.clients.CustomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 测试
 *
 * @author ZhengYu
 * @version 1.0 2019/10/31 15:41
 **/
@RestController
public class CustomController {

    @Autowired
    private CustomClient customClient;

    @GetMapping("/custom")
    public String test() {
        CustomStructure customStructure = new CustomStructure("zy", 25);
        return JSONObject.toJSONString(new ArrayList<CommonResult>() {{
            add(customClient.customGet());
            add(customClient.customPost(customStructure));
            add(customClient.customPut(customStructure));
            add(customClient.customDelete(customStructure));
        }});
    }
}
