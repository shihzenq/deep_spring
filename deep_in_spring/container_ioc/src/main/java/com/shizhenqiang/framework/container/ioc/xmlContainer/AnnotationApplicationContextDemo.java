package com.shizhenqiang.framework.container.ioc.xmlContainer;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Configuration
public class AnnotationApplicationContextDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(AnnotationApplicationContextDemo.class);
        annotationConfigApplicationContext.refresh();
        lookUpCollectionByType(annotationConfigApplicationContext);
        annotationConfigApplicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(3L);
        user.setName("shi");
        user.setAge(18);
        return user;
    }

    private static void lookUpCollectionByType(BeanFactory applicationContext) {
        if (applicationContext instanceof ListableBeanFactory) {
            Map<String, User> userMap = ((ListableBeanFactory) applicationContext).getBeansOfType(User.class);
            System.out.println("根据类型返回map集合" + userMap);
        }
    }
}
