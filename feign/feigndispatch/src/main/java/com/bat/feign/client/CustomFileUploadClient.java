package com.bat.feign.client;

import com.bat.commoncode.response.CommonResult;
import com.bat.feign.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * feign 上传文件
 *
 * @author ZhengYu
 * @version 1.0 2020/5/20 10:23
 **/
@FeignClient(value = "CUSTOM-SERVICE", configuration = FeignMultipartSupportConfig.class, path = "/service/inner")
public interface CustomFileUploadClient {

    @PostMapping(value = "/feign/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonResult feignUpload(@RequestPart(value = "file") MultipartFile file,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "value", required = false) String value);
}
