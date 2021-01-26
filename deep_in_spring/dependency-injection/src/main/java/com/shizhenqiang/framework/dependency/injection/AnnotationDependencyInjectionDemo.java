package com.shizhenqiang.framework.dependency.injection;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationDependencyInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(3L);
        user.setName("shi");
        user.setAge(18);
        return user;
    }
}
