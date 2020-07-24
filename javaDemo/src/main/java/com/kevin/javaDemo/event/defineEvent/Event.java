package com.kevin.javaDemo.event.defineEvent;

import java.io.Serializable;

public interface Event extends Serializable {
    // 获取事件持有的对象
    EventObject getEventObject();

    //设置事件持有的数据对象
    void setEventObject(EventObject eventObject);
}
