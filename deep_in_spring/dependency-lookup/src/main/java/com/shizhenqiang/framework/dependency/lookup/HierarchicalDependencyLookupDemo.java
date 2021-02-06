package com.shizhenqiang.framework.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前的 BeanFactory 的 Parent BeanFactory ：" + beanFactory.getParentBeanFactory());
        HierarchicalBeanFactory parentBeanFactory = createBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前的 BeanFactory 的 Parent BeanFactory ：" + beanFactory.getParentBeanFactory());
        displayLocalBean(beanFactory, "user");
        displayLocalBean(parentBeanFactory, "user");
        applicationContext.refresh();
        applicationContext.close();
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s], 是否包含bean[name : %s] : %s \n",
                beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    private static HierarchicalBeanFactory createBeanFactory() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String load = "classpath:/MATE-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(load);
        return defaultListableBeanFactory;
    }
}
