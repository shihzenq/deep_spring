package org.shizhenqiang.framework.bean.lifecycle;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();

        System.out.println("-------------------------------------------------------------------");
//        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加BeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitions = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/MATE-INF/dependency-lookup-context.xml");
        System.out.println(beanDefinitions);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User childUser = beanFactory.getBean("childUser", User.class);
        System.out.println(childUser);

        beanFactory.preInstantiateSingletons();
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        // 添加BeanPostProcessor
        applicationContext.setConfigLocations("classpath:/MATE-INF/dependency-lookup-context.xml");

        applicationContext.refresh();
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        User childUser = applicationContext.getBean("childUser", User.class);
        System.out.println(childUser);

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }


}

