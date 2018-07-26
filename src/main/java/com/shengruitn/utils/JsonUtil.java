package com.shengruitn.utils;/*
 *
 *   @author tangah
 *   @create 2018-07-04 0:27
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
