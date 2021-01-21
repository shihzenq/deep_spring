package com.shizhenqiang.framework.container.ioc.dependency.lookup;

import com.shizhenqiang.framework.container.ioc.annotation.Child;
import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookUpDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/MATE-INF/dependency-lookup-context.xml");
        lookUpRealName(applicationContext);
        lookUpLazy(applicationContext);
        lookUpByType(applicationContext);
        lookUpCollectionByType(applicationContext);

        lookUpByAnnotation(applicationContext);
    }

    private static void lookUpByAnnotation(ApplicationContext applicationContext) {
        if (applicationContext instanceof ListableBeanFactory) {
            Map<String, Object> objectMap = applicationContext.getBeansWithAnnotation(Child.class);
            System.out.println("按注解查找" +objectMap);
        }
    }

    // 使用这种方式查找时，需要注释掉返回单个类型查找
    private static void lookUpCollectionByType(ApplicationContext applicationContext) {
        if (applicationContext instanceof ListableBeanFactory) {
            Map<String, User> userMap = applicationContext.getBeansOfType(User.class);
            System.out.println("根据类型返回map集合" + userMap);
        }
    }

    private static void lookUpByType(ApplicationContext applicationContext) {
        User user = applicationContext.getBean(User.class);
        System.out.println( "根据类型查找" +user);
    }

    private static void lookUpLazy(ApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)applicationContext.getBean("objectFactory");
        System.out.println( "懒加载查找" +objectFactory.getObject());
    }

    private static void lookUpRealName(ApplicationContext applicationContext) {
        User user = (User)applicationContext.getBean("user");
        System.out.println("根据名称查找" + user);
    }
}
