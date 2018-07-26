package com.shengruitn.utils;
/*
 *
 *   @author tangah
 *   @create 2018-07-05 1:42
 */

import com.shengruitn.Enum.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each: enumClass.getEnumConstants() ){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
