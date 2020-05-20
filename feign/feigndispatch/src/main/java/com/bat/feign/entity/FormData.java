package com.bat.feign.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * TODO
 *
 * @author ZhengYu
 * @version 1.0 2020/5/20 11:53
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormData {

    String contentType;

    String fileName;

    byte[] data;
}
