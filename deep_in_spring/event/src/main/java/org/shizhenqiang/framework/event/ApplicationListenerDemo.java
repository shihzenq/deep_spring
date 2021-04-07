package org.shizhenqiang.framework.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

// 开启异步执行
@EnableAsync
public class ApplicationListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册监听
        applicationContext.addApplicationListener(event -> {
            System.out.printf("addApplicationListener 收到事件 = %s, source = %s \n", event, event.getSource());
        });

//        applicationContext.addApplicationListener(new MyApplicationListener());
        applicationContext.register(MyApplicationListener.class);

        applicationContext.register(ApplicationListenerDemo.class);


        applicationContext.refresh();

        applicationContext.publishEvent(new MyApplicationEvent(applicationContext));

        applicationContext.start();

        applicationContext.close();
    }

    static class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

        @Override
        public void onApplicationEvent(MyApplicationEvent event) {
            System.out.printf("MyApplicationListener 收到事件 = %s, source = %s, 线程名称 = %s \n", event, event.getSource(), Thread.currentThread().getName());
        }
    }

    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.printf("EventListener 收到事件 = %s, source = %s \n", event, event.getSource());
    }

    @EventListener
    @Async
    public void onApplicationEventAsync(ApplicationEvent event) {
        System.out.printf("EventListener（异步） 收到事件 = %s, source = %s, 线程名称 = %s \n", event, event.getSource(), Thread.currentThread().getName());
    }
}
