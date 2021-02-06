package com.shizhenqiang.springboot.boot_test.dependency;


import com.shizhenqiang.springboot.boot_test.model.ChildUser;
import com.shizhenqiang.springboot.boot_test.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AnnotationConfiguration {

    @Bean("user")
    public User user() {
        return new User(1L, "shizhenqiang", "15");
    }

    @Bean("childUser")
    @Primary
    public ChildUser childUser() {
        return new ChildUser(2L, "childUser", "20", "北京");
    }

}
