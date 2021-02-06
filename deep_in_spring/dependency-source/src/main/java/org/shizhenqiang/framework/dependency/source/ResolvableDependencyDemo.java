package org.shizhenqiang.framework.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class ResolvableDependencyDemo {

    
    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencyDemo.class);

        // 做postProcessor回调
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "hello, world");
        });
        applicationContext.refresh();

        applicationContext.close();
    }
}
