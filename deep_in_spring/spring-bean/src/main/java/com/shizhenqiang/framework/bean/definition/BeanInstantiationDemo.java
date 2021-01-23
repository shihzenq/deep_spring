package com.shizhenqiang.framework.bean.definition;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/MATE-INF/spring-instantiation-bean.xml");
        User user = beanFactory.getBean("shi-create-user", User.class);
        User userFactory = beanFactory.getBean("userByFactory", User.class);
        System.out.println(user);
        System.out.println(userFactory);

    }
}
