package com.shizhenqiang.framework.dependency.lookup;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ObjectProviderDemo{

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        lookupByObjectProvider(applicationContext);
        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(3L);
        user.setName("shi");
        user.setAge(18);
        return user;
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(beanProvider.getObject());
    }
}
