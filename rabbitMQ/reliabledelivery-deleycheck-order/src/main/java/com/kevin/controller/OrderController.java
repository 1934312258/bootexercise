package com.kevin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevin.bo.MessageBo;
import com.kevin.compent.MsgSender;
import com.kevin.entity.OrderInfo;
import com.kevin.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author kevin
 * @date 2019-11-14 16:55
 * @description todo
 **/
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderInfoService service;

    @Autowired
    MsgSender sender;


    @RequestMapping("/saveOrder")
    public String saveOrder() throws JsonProcessingException {
        OrderInfo info = new OrderInfo();
        info.setProductNo(1);
        info.setOrderNo(System.currentTimeMillis());
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        info.setMoney(100000);
        info.setUserName("kevin");

        service.saveOrderInfoWithMessage(info);
        return "ok";
    }

    @RequestMapping("/retryMsg")
    public String retryMsg(@RequestBody MessageBo message) {
        log.info("消息重新发送：{}", message);

        sender.sendMsg(message);
        sender.sendDelayMsg(message);
        return "ok";
    }
}
