package com.shizhenqiang.framework.bean.definition;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//@Import(AnnotationBeanDefinitionDemo.MyConfig.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        applicationContext.register(AnnotationBeanDefinitionDemo.MyConfig.class);
        registryBeanDefinition(applicationContext, "user-zhen");
        registryBeanDefinition(applicationContext);
        applicationContext.refresh();

        System.out.println("myConfig" + applicationContext.getBeansOfType(MyConfig.class));
        System.out.println("user" + applicationContext.getBeansOfType(User.class));

        applicationContext.close();

    }


    private static void registryBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", "5");
        beanDefinitionBuilder.addPropertyValue("name", "shizhenqiang");
        beanDefinitionBuilder.addPropertyValue("age", "20");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    private static void registryBeanDefinition(BeanDefinitionRegistry registry) {
        registryBeanDefinition(registry, null);
    }
    @Component
    class MyConfig {

        @Bean
        public User user() {
            User user = new User();
            user.setId(3L);
            user.setName("shi");
            user.setAge(18);
            return user;
        }
    }
}
