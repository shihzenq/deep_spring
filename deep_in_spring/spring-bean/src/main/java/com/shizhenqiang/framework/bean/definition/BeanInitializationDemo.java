package com.shizhenqiang.framework.bean.definition;

import com.shizhenqiang.framework.bean.definition.factory.DefaultUserFactory;
import com.shizhenqiang.framework.bean.definition.factory.UserFactory;
import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanInitializationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
//        applicationContext.register(UserFactory.class);

        applicationContext.refresh();
        applicationContext.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "doDestroy")
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
