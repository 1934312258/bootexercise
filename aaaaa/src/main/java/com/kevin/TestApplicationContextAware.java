package com.kevin;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaowenjian
 * @since 2022/1/5 16:29
 */
@Component
public class TestApplicationContextAware implements ApplicationContextAware {

    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        TestApplicationContextAware applicationContextAware = new TestApplicationContextAware();
        System.out.println();
    }
}
