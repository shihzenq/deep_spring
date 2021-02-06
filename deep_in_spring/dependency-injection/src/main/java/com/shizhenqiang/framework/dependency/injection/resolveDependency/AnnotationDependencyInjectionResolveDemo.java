package com.shizhenqiang.framework.dependency.injection.resolveDependency;

import com.shizhenqiang.framework.container.ioc.domain.User;
import com.shizhenqiang.framework.dependency.injection.annotation.MyAutowired;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
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

    @MyAutowired
    private Optional<User> optionalUser;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    @Inject
    private User injectUser;

    /**
     * 如果没有加static，notationDependencyInjectionResolveDemo所在的类并没有初始化为bean。
     * 但是如果要拿到自定义的AutowiredAnnotationBeanPostProcessor，就必须拿到
     *      * 上下文refresh后，会调用registerBeanPostProcessors注册各beanPostProcessors。此时AnAnnotationDependencyInjectionResolveDemo的bean
     * 然后再后调用beanPostProcessor来拿到这个AutowiredAnnotationBeanPostProcessor。
     * 这就必然导致整个AnnotationDependencyInjectionResolveDemo配置类先实例化初始化，也就导致@MyAutowired注入的bean还没有被AutowiredAnnotationBeanPostProcessor解析，
     * 就注入进来了，也就是null。如果加上static，那么就不需要先拿到demo的bean，可以直接调用类方法获取这个AutowiredAnnotationBeanPostProcessor。那么就会导致先拿到processor，
     * 后拿到demo的bean。这样demo在初始化的时候，就会正常解析自定义@MyAutowired。所以对于同时存在bean的定义和bean的注入的配置类，顺序很容易弄错。
     */
    @Bean
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(4);
        autowiredAnnotationTypes.add(MyAutowired.class);
        autowiredAnnotationTypes.add(Autowired.class);
        autowiredAnnotationTypes.add(Value.class);
        autowiredAnnotationTypes.add(Inject.class);
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }

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
        System.out.println("optionalUser: " + demo.optionalUser);
        System.out.println(demo.userObjectProvider);
        System.out.println("injectUser: " + demo.injectUser);
        applicationContext.close();
    }
}
