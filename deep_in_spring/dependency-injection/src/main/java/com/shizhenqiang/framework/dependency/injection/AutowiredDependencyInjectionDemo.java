package com.shizhenqiang.framework.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredDependencyInjectionDemo {

    @Autowired
    private  UserHolder userHolder;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AutowiredDependencyInjectionDemo.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String path = "classpath:/MATE-INF/dependency-setter-dependency.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(path);
        applicationContext.refresh();
        AutowiredDependencyInjectionDemo demo = applicationContext.getBean(AutowiredDependencyInjectionDemo.class);
        System.out.println(demo.userHolder);
        applicationContext.close();
    }

}
