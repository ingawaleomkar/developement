package com.demo.connexissample.DAO;

import android.arch.persistence.room.TypeConverter;

import com.demo.connexissample.model.ResponseList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by samrat on 27/1/19.
 */

public class ResponseConverter {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<ResponseList> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ResponseList>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<ResponseList> someObjects) {
        return gson.toJson(someObjects);
    }
}
