package com.kevin.javaDemo.aspect.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 * @date 2020-7-9 9:53
 * @description todo
 **/
public class LoginVerifyMapping {
    private static Map<String, Boolean> faceFunctionIsNeedLoginVerify = new HashMap<>();

    public static void add(String functionName) {
        faceFunctionIsNeedLoginVerify.put(functionName, Boolean.TRUE);
    }

    public static Boolean get(String functionName) {
        return faceFunctionIsNeedLoginVerify.get(functionName);
    }
}
