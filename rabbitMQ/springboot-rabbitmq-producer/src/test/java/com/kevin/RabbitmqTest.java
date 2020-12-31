package com.kevin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.compent.MsgSender;
import com.kevin.entity.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author kevin
 * @date 2019-11-19 14:15
 * @description todo
 **/
public class RabbitmqTest {

    @Autowired
    MsgSender sender;

    @Test
    public void testMsgSender() throws JsonProcessingException {
        //创建消息属性
        Map<String, Object> info = new HashMap<>();
        info.put("company", "markor");
        info.put("name", "kevin");

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setUserName("kevin");
        order.setPayMoney(10000);
        order.setCreateDt(new Date());
        ObjectMapper mapper = new ObjectMapper();
        String orderJson = mapper.writeValueAsString(order);
        sender.sendMsg(orderJson, info);
    }
}
