package com.kevin;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author
 * @date 2019-11-26 15:22
 * @description todo
 **/
public class Body {
    public static void adc() throws Exception {
        throw new Exception("ceshi");
    }

    public static void main(String[] args) {
        List<Long> list = Arrays.asList(1L,2L,3L);
        Gson gson = new Gson();
        String str = gson.toJson(list);
//        str = "[{1},{2}]";

        Type type = new TypeToken<ArrayList<Long>>(){}.getType();
        List<Long> result = gson.fromJson(str,type);

    }
}
