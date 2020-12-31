package com.kevin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevin.entity.OrderInfo;
import com.kevin.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author kevin
 * @date 2019-11-13 22:46
 * @description todo
 **/
@RestController
public class OrderController {
    @Autowired
    private OrderInfoService service;

    @RequestMapping("/saveOrder")
    public String saveOrder() throws JsonProcessingException {
        OrderInfo info = new OrderInfo();
        info.setOrderNo(System.currentTimeMillis());
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        info.setUserName("kevin");
        info.setMoney(10000);
        info.setProductNo(1);
        service.saveOrderInfoWithMessage(info);
        return "ok";
    }
}
