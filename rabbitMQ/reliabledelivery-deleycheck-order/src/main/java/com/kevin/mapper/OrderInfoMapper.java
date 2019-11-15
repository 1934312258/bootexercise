package com.kevin.mapper;

import com.kevin.entity.OrderInfo;

public interface OrderInfoMapper {
    int saveOrderInfo(OrderInfo info);

    int updateOrderStatusById(long orderNo,Integer orderStatus);



}
