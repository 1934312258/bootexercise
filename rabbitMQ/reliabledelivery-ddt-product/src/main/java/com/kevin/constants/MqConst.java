package com.kevin.constants;

/**
 * @author kevin
 * @date 2019-11-12 10:10
 * @description todo
 **/
public class MqConst {
    //使用final修饰的变量，修改之后必须重新编译项目才能应用，否则无法使用，因为常亮实在编译期加入内存的

    public static final String PRODUCT_TO_CALLBACK_NAME = "order-to-callback.exchange";

    public static final String PRODUCT_TO_CALLBACK_QUEUE_NAME="order-to-callback.queue";

    public static final String PRODUCT_TO_CALLBACK_ROUTINGKEY="order-to-callback.key";

}
