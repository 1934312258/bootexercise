package com.kevin.javaDemo.event.defineEvent;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * @author kevin
 * @date 2020-7-23 15:47
 * @description todo
 **/
public class Test1EventListener extends AbstractEventListener {

    public Test1EventListener(int order) {
        super(order);
    }

    @Override
    public void OnEvent(Event event) {
        System.out.println("监听事件执行器1开始执行");
        //获取事件持有的对象
        EventObject eventObject=event.getEventObject();
        System.out.println((new Gson()).toJson(eventObject));
        //业务逻辑
    }
}
