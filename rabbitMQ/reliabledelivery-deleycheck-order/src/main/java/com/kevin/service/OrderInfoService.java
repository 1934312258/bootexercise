package com.kevin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevin.entity.OrderInfo;

public interface OrderInfoService {
    void saveOrderInfo(OrderInfo info);

    void saveOrderInfoWithMessage(OrderInfo info) throws JsonProcessingException;
}
