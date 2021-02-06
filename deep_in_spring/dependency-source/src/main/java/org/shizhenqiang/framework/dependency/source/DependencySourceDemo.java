package org.shizhenqiang.framework.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     *  @PostConstruct 的语义等同于 afterPropertiesSet() 方法，相当于当属性初始化之后，所以 @Autowired 要早于 @PostConstruct
     *
     * 执行结果：
     * init() 方法
     * beanFactory == applicationContext false  beanFactory：org.springframework.beans.factory.support.DefaultListableBeanFactory；applicationContext：org.springframework.context.annotation.AnnotationConfigApplicationContext
     * beanFactory == applicationContext.getBeanFactory() true
     * resourceLoader == applicationContext true
     * ApplicationEventPublisher == applicationContext true
     * initByLookup()方法 ，查不到，是因为这四个类不会被Spring容器管理，也就是不会注册到 org.springframework.beans.factory.support.DefaultListableBeanFactory#beanDefinitionMap中“
     * 既然查不到那为什么会能注入进来呢？ 是org.springframework.beans.factory.support.DefaultListableBeanFactory#findAutowireCandidates(java.lang.String, java.lang.Class, org.springframework.beans.factory.config.DependencyDescriptor)
     * 进行实例化并注入到此类中。
     *
     * 当前类型org.springframework.beans.factory.BeanFactory 无法在 BeanFactory 中查找!
     * 当前类型org.springframework.context.ApplicationContext 无法在 BeanFactory 中查找!
     * 当前类型org.springframework.core.io.ResourceLoader 无法在 BeanFactory 中查找!
     * 当前类型org.springframework.context.ApplicationEventPublisher 无法在 BeanFactory 中查找!
     *
     *
     *
     *
     *
     */

    @PostConstruct
    public void init() {
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
        System.out.println("ApplicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + " 无法在 BeanFactory 中查找!");
        }
        return null;
    }



    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/MATE-INF/dependency-lookup-context.xml");
        applicationContext.refresh();
        applicationContext.close();
    }
}
