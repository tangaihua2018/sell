package com.shengruitn.utils;/*
 *   索引、订单等主键生成
 *   @author tangah
 *   @create 2018-06-27 16:11
 */

import java.util.Random;

public class KeyUtil {
    /*
    * 生成唯一主键
    * 格式:时间+随机数
    * @return
    * */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
