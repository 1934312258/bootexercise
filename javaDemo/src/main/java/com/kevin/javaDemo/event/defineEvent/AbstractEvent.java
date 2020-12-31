package com.kevin.javaDemo.event.defineEvent;

/**
 * @author kevin
 * @date 2020-7-23 15:05
 * @description todo
 **/
public class AbstractEvent implements Event {

    private static final long serialVersionUID = 1800178291032860564L;

    private long timestamp;

    private EventObject eventObject;

    public AbstractEvent(EventObject eventObject) {
        this.setEventObject(eventObject);
    }

    @Override
    public EventObject getEventObject() {
        return eventObject;
    }

    @Override
    public void setEventObject(EventObject eventObject) {
        this.eventObject = eventObject;
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
