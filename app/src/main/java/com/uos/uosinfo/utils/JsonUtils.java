package com.uos.uosinfo.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uos.uosinfo.domain.CareerResult;

import java.util.List;

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
    public static <T> List<T> JsonToList(String json,Class<T> clazz) {
        Gson gson = new Gson();
        List<T> object = gson.fromJson(json,new TypeToken<List<T>>(){}.getType());
        return object;
    }
    public static List<CareerResult> JsonToResultList(String json) {
        Gson gson = new Gson();
        List<CareerResult> object = gson.fromJson(json,new TypeToken<List<CareerResult>>(){}.getType());
        return object;
    }
}
