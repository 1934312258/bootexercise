package com.kevin;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * @author
 * @date 2020-6-4 16:30
 * @description todo
 **/
public class B extends A {
    Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public B() {

    }

    public B(int age, List<B> list) {
        this.age = age;
        list.add(this);
    }

    public static String getDateFormat(Date date, String formatStr) {
        return null != formatStr && formatStr.length() > 0 ? (new SimpleDateFormat(formatStr)).format(date) : null;
    }

    private String handleArray(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str + ",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String[] array() {
        String[] strs = new String[2];
        strs[0] = "1";
        strs[1] = "2";
        for (int i = 0; i < strs.length; i++) {
            strs[i] = strs[i] + "kevin";
        }
        return strs;
    }

    private static void test(long longa){

    }


    public static void main(String[] args) {
        Date time = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        calendar.add(Calendar.DATE, 1);
        time = calendar.getTime();
        Integer interval = 5;
        String dateFormat = "ss mm HH dd MM ? yyyy";
        String cornStr = getDateFormat(time, dateFormat);
        if (interval != null && interval.compareTo(0) > 0) {
            cornStr = cornStr.replaceFirst(" ", "/" + interval + " ");
        }
        B b = new B();
        String[] test = b.array();

        Map<String, Object> map = new HashMap();
        map.put("age", 1);


        String str = "[{\"specName\":\"19\",\"specValue\":\"蓝\"},{\"specName\":\"21\",\"specValue\":\"大\"}]";

        List list = new ArrayList();
        list.clear();
        String param = null;
        JSONObject jsonObject = parseObject(param);

        String str1 = "346536";
        Long fdefd = Long.getLong(str1);
        long ewf = Long.valueOf(str1);
        long dewdf = Long.parseLong(str1);
        if(12 <= 13){
            System.out.println(1111);
        }
        DateTime dateTime = DateUtil.offsetMinute(new Date(),1440);
        if(new Integer(0) == null){

        }
        System.out.println();
    }
}
