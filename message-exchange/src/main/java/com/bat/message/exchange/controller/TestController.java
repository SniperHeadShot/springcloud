package com.bat.message.exchange.controller;

import com.alibaba.fastjson.JSONObject;
import com.bat.commoncode.entity.CacheMsgBody;
import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.enums.MsgExchangeEnum;
import com.bat.message.exchange.service.CacheManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO 此文件将被删除
 *
 * @author ZhengYu
 * @version 1.0 2019/11/27 13:57
 **/
@Slf4j
@RestController
public class TestController {

    @Autowired
    private RabbitTemplate cloudRabbitTemplate;

    private static List<CustomStructure> DB_ADD = new ArrayList<CustomStructure>() {{
        add(new CustomStructure("zs", 10));
        add(new CustomStructure("ls", 20));
        add(new CustomStructure("ww", 30));
    }};

    private static List<CustomStructure> DB_UPDATE = new ArrayList<CustomStructure>() {{
        add(new CustomStructure("zs1", 101));
        add(new CustomStructure("ls1", 201));
        add(new CustomStructure("ww1", 301));
    }};

    @Autowired
    private CacheManageService cacheManageService;

    @GetMapping("/test")
    public String test() {
        cacheManageService.putCache(new CacheMsgBody("highKey", DB_ADD));
        return "succ";
    }

    @GetMapping("/test/get")
    public String testGet() {
        if (new Random().nextBoolean()) {
            return JSONObject.toJSONString(cacheManageService.getCache("highKey"));
        } else {
            return JSONObject.toJSONString(cacheManageService.getCache("lowKey"));
        }
    }

    @GetMapping("/test/update")
    public String testPut() {
        cacheManageService.updateCache(new CacheMsgBody("highKey", DB_UPDATE));
        return "succ";
    }

    @GetMapping("/test/del")
    public String testDel() {
        cacheManageService.delCache("highKey");
        return "succ";
    }

    @GetMapping("/test/rabbitmq")
    public String testRabbitMq(){
        cloudRabbitTemplate.convertAndSend(MsgExchangeEnum.MSG_COMMON_BROADCAST.getExchangeName(), MsgExchangeEnum.MSG_COMMON_BROADCAST.getRoutingKey(), "/test/rabbitmq");
        return "succ";
    }
}