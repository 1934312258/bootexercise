package com.kevin.javaDemo.event.defineEvent;

/**
 * @author kevin
 * @date 2020-7-23 15:44
 * @description 加入控制监听器执行顺序的逻辑
 **/
public class AbstractEventListener implements EventListener {
    //控制监听器的执行顺序
    private int order;

    public int  getOrder(){
        return order;
    }

    public AbstractEventListener(int order){
        this.order=order;
    }

    @Override
    public void OnEvent(Event event) {

    }
}
