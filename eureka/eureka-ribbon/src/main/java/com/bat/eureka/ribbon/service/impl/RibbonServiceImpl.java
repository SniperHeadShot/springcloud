package com.bat.eureka.ribbon.service.impl;

import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.response.CommonResult;
import com.bat.eureka.ribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon Service Impl
 *
 * @author ZhengYu
 * @version 1.0 2019/11/12 10:44
 **/
@Service
public class RibbonServiceImpl implements RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET 方法
     *
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @Override
    public CommonResult customGet() {
        return restTemplate.getForObject("http://EUREKA-CLIENT/custom/get", CommonResult.class);
    }

    /**
     * POST 方法
     *
     * @param customStructure POST
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @Override
    public CommonResult customPost(CustomStructure customStructure) {
        return restTemplate.postForObject("http://EUREKA-CLIENT/custom/post", customStructure, CommonResult.class);
    }

    /**
     * PUT 方法
     *
     * @param customStructure PUT
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @Override
    public CommonResult customPut(CustomStructure customStructure) {
        // restTemplate.put 无法获取到返回值
        return sendRestTemplateRequest(customStructure, CommonResult.class, HttpMethod.PUT, "http://EUREKA-CLIENT/custom/put");
    }

    /**
     * DELETE 方法
     *
     * @param customStructure DELETE
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @Override
    public CommonResult customDelete(CustomStructure customStructure) {
        // restTemplate.delete 无法获取到返回值
        return sendRestTemplateRequest(customStructure, CommonResult.class, HttpMethod.DELETE, "http://EUREKA-CLIENT/custom/delete");
    }

    /**
     * 使用 RestTemplate 发送 Http 请求
     *
     * @param t          请求体
     * @param k          返回值
     * @param httpMethod 请求方式
     * @param url        请求URL
     * @return K 响应
     * @author ZhengYu
     */
    private <T, K> K sendRestTemplateRequest(T t, Class<K> k, HttpMethod httpMethod, String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> httpEntity = new HttpEntity<>(t, httpHeaders);
        ResponseEntity<K> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, k);
        return responseEntity.getBody();
    }
}
