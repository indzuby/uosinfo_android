package com.uos.uosinfo.utils;

import com.google.gson.Gson;

/**
 * Created by 주현 on 2016-01-10.
 */
public class JsonUtils {

    public static String objectToJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
    public static <T> T JsonToObject(String json,Class<T> clazz) {
        Gson gson = new Gson();
        T object = gson.fromJson(json,clazz);
        return object;
    }
}
