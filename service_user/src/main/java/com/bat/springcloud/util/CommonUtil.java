package com.bat.springcloud.util;

import java.util.Random;

public class CommonUtil {

    // 随机出一个boolean值
    public static boolean getRandomBooleanResult() {
        return new Random().nextInt(10) > 5;
    }

    //随机一个用户名
    public static String getRandomUsername() {
        String[] usernameArr = {"张三", "李四", "王五", "赵六", "孙权", "刘备", "周瑜", "诸葛", "宋江", "林冲"};
        return usernameArr[new Random().nextInt(10)];
    }
}
