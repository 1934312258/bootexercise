package com.kevin.os;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kevin
 * @date 2019-10-18 9:04
 * @description todo
 **/
public class testing {
    static String string = new String();

    public static void main(String[] args) {
        String str = "{\"code\":\"0\",\"msg\":null,\"data\":{\"content\":[{\"id\":\"1\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025156\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"2\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025157\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"3\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025158\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"4\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025159\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"5\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025160\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"6\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025161\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"7\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025162\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"8\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025163\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"9\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025164\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null},{\"id\":\"10\",\"createts\":\"2019-10-17 15:56:42\",\"modifyts\":null,\"createBy\":\"liuliu3\",\"modifiedBy\":null,\"templateId\":1.0,\"couponCode\":\"couponTemplateCode16_0000025165\",\"totalNum\":20.0,\"startDate\":null,\"endDate\":null,\"days\":10.0,\"boundDate\":null,\"miniProgramCounponCode\":null,\"customerCode\":null,\"couponStatus\":\"CREATED\",\"couponTemplatecode\":\"couponTemplateCode16\",\"name\":\"couponTemplateName\",\"supportMultiple\":true,\"consumeChannel\":\"ONLINE\",\"couponType\":\"DIS\",\"threshold\":1.0,\"discount\":0.87,\"single\":true,\"giftProductDatas\":null}],\"last\":false,\"totalElements\":20.0,\"totalPages\":2.0,\"number\":0.0,\"size\":10.0,\"sort\":[{\"direction\":\"DESC\",\"property\":\"createts\",\"ignoreCase\":false,\"nullHandling\":\"NATIVE\",\"ascending\":false,\"descending\":true}],\"first\":true,\"numberOfElements\":10.0}}";
        Gson gson = new Gson();
        JsonObject object = (JsonObject) new JsonParser().parse(str);
        String data = object.get("data").toString();
        JsonObject object1 = (JsonObject) new JsonParser().parse(data);
        Map<String, Map<String, testBean>> map = new HashMap<>();
        Map<String, testBean> map1 = new HashMap<>();
        testBean bean = new testBean();
        map1.put("bean", bean);
        map.put("test", map1);
        Map<String, testBean> map2 = map.get("test");
        testBean test = map2.get("bean");
        test.setName("kevin");
        System.out.println(map2.get("bean").getName());
        System.out.println(map.get("test").get("bean").getName());


    }
}
