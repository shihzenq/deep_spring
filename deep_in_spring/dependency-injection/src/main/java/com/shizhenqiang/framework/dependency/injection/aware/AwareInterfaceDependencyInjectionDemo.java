package com.shizhenqiang.framework.dependency.injection.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware, EnvironmentAware,
        ResourceLoaderAware, BeanClassLoaderAware, BeanNameAware, MessageSourceAware, ApplicationEventPublisherAware {

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    private Environment environment;

    private ResourceLoader resourceLoader;

    private MessageSource messageSource;

    private ApplicationEventPublisher applicationEventPublisher;

    private String name;

    private ClassLoader classLoader;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareInterfaceDependencyInjectionDemo.class);
        context.refresh();
        AwareInterfaceDependencyInjectionDemo demo = context.getBean(AwareInterfaceDependencyInjectionDemo.class);
        System.out.println("beanFactory : " + demo.beanFactory);
        System.out.println("applicationContext : " + demo.applicationContext);
        System.out.println("environment : " + demo.environment);
        System.out.println("resourceLoader : " + demo.resourceLoader);
        System.out.println("messageSource : " + demo.messageSource);
        System.out.println("applicationEventPublisher : " + demo.applicationEventPublisher);
        System.out.println("classLoader : " + demo.classLoader);
        System.out.println("name : " + demo.name);
        System.out.println(demo.beanFactory  == context.getBeanFactory());
        System.out.println(demo.applicationContext  == context);

        context.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
