package com.shizhenqiang.framework.container.ioc.xmlContainer;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class XmlApplicationContextDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String load = "classpath:/MATE-INF/dependency-lookup-context.xml";
        int beanDefinitions = reader.loadBeanDefinitions(load);
        System.out.println("加载了" + beanDefinitions+"个 bean");
        lookUpCollectionByType(defaultListableBeanFactory);
    }

    private static void lookUpCollectionByType(BeanFactory applicationContext) {
        if (applicationContext instanceof ListableBeanFactory) {
            Map<String, User> userMap = ((ListableBeanFactory) applicationContext).getBeansOfType(User.class);
            System.out.println("根据类型返回map集合" + userMap);
        }
    }
}
