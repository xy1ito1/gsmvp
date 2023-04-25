package com.gslibrary.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GsonUtils {

    public static <T> T parseJson(String jsonData, Class<T> entityType) {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
        T t = gson.fromJson(jsonData, entityType);
        return t;
    }

    public static <T> List<T> parseJsonArray(String jsonArrayData) {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
        List<T> list = gson.fromJson(jsonArrayData, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    public static <T> List<T> readJsonArray(JSONArray array, Class<T> entityType) {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                T t = gson.fromJson(array.getJSONObject(i).toString(), entityType);
                list.add(t);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static <T> List<T> readJsonArray(String array, Class<T> entityType) throws JSONException {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
        JSONArray jsonArray = new JSONArray(array);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                T t = gson.fromJson(jsonArray.getJSONObject(i).toString(), entityType);
                list.add(t);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
