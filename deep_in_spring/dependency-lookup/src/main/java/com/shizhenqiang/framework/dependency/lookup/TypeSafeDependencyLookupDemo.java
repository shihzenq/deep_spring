package com.shizhenqiang.framework.dependency.lookup;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TypeSafeDependencyLookupDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafeDependencyLookupDemo.class);
        applicationContext.refresh();
        noSafeLookup(applicationContext);
        displayObjectProvider(applicationContext);
        applicationContext.close();
    }

    private static void displayObjectProvider(ApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(beanProvider.getIfAvailable());
    }

    private static void noSafeLookup(ApplicationContext applicationContext) {
        try {
            applicationContext.getBean(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
