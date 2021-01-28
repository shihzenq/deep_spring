package com.shizhenqiang.framework.dependency.injection.resolveDependency;

import com.shizhenqiang.framework.container.ioc.domain.User;
import com.shizhenqiang.framework.dependency.injection.qualfier.QualifierAnnotationDependencyInjectionDemo;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String, Set, TypeConverter)} (DependencyDescriptor, String)}
 */
public class AnnotationDependencyInjectionResolveDemo {


    @Autowired
    private User user;

    @Autowired
    private Map<String, User> userMap;

    @Autowired
    private Optional<User> optionalUser;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolveDemo.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/MATE-INF/dependency-lookup-context.xml");
        applicationContext.refresh();
        AnnotationDependencyInjectionResolveDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolveDemo.class);
        // 依赖注入处理过程：DefaultListableBeanFactory#resolveDependency
        System.out.println(demo.user);
        System.out.println(demo.userMap);
        System.out.println(demo.optionalUser);
        System.out.println(demo.userObjectProvider);
        applicationContext.close();
    }
}
