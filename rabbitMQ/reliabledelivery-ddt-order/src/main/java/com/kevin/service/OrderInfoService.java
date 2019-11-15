package com.kevin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevin.entity.MessageContent;
import com.kevin.entity.OrderInfo;

public interface OrderInfoService {

    void saveOrderInfo(OrderInfo info, MessageContent content);

    void saveOrderInfoWithMessage(OrderInfo info) throws JsonProcessingException;
}
