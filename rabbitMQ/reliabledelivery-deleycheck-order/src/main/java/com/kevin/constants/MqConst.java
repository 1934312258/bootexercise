package com.kevin.constants;

/**
 * @author kevin
 * @date 2019-11-14 13:55
 * @description todo
 **/
public class MqConst {
    //订单业务
    public static final String ORDER_TO_PRODUCT_EXCHANGE_NAME = "order-to-product.exchange";
    public static final String ORDER_TO_PRODUCT_QUEUE_NAME = "order-to-product.queue";
    public static final String ORDER_TO_PRODUCT_ROUTING_KEY = "order-to-product.key";

    //回调队列
    public static final String PRODUCT_TO_CALLBACK_QUEUE_NAME = "product_to_callback_queue";
    public static final String PRODUCT_TO_CALLBACK_ROUTING_KEY = "product_to_callback_key";

    //延迟队列
    public static final String ORDER_TO_PRODUCT_DELAY_EXCHANGE_NAME = "order-to-product.delayexchange";

    public static final String ORDER_TO_PRODUCT_DELAY_QUEUE_NAME = "order-to-product.delayqueue";

    public static final String ORDER_TO_PRODUCT_DELAY_ROUTING_KEY = "order-to-product.delaykey";

    //延迟时间
    public static final Integer DELAY_TIME = 30000;

    /**
     * 消息重发的最大次数
     */
    public static final Integer MSG_RETRY_COUNT = 5;

    public static final Integer TIME_DIFF = 100;
}
