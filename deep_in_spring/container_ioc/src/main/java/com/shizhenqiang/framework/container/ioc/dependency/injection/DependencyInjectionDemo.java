package com.shizhenqiang.framework.container.ioc.dependency.injection;

import com.shizhenqiang.framework.container.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjectionDemo {

    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/MATE-INF/dependency-injection-context.xml");
//        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
//        System.out.println(userRepository.getUsers());
//
//        System.out.println("applicationContext : " +userRepository.getApplicationContext());
//
//        System.out.println(userRepository.getApplicationContext() == applicationContext); // true

        /***
         * IOC 依赖来源
         * • 自定义Bean
         * • 容器內建Bean 对象
         * • 容器內建依赖
         */

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/MATE-INF/dependency-injection-context.xml");
        // 来源一：自定义的Bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository.getUsers());

        // 来源二：内建Bean
        System.out.println("beanFactory : " +userRepository.getBeanFactory()); // DefaultListableBeanFactory

        System.out.println(userRepository.getBeanFactory() == beanFactory); // false,

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());

        System.out.println(objectFactory.getObject() == beanFactory); // true, 都是ClassPathXmlApplicationContext

        // 来源三：容器内建Bean， 默认初始化的Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }


}
