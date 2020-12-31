package com.kevin.javaDemo.event.defineEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2020-7-23 16:09
 * @description 事件广播器
 **/
public class EventMulticaster {
    private List<AbstractEventListener> listeners;

    public EventMulticaster(List<AbstractEventListener> listeners) {
        this.listeners = listeners;
    }

    //广播事件
    public void multicastEvent(Event event) {
        //按照设置的顺序执行事件监听器
        for (EventListener listener : listeners) {
            listener.OnEvent(event);
        }
    }

    public static void main(String[] args) {
        List<AbstractEventListener> list = new ArrayList<>();
        list.add(new Test1EventListener(1));
        //根据order做排序
        list = list.stream().sorted(Comparator.comparing(AbstractEventListener::getOrder))
                .collect(Collectors.toList());
        EventMulticaster eventMulticaster = new EventMulticaster(list);

        //定义eventObject
        Order order = new Order();
        EventObject eventObject = new EventObject(order);

        //广播事件
        eventMulticaster.multicastEvent(new OrderCallbackEvent(eventObject));
    }
}
