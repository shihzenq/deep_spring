package com.shizhenqiang.springboot.boot_test;

import com.shizhenqiang.springboot.boot_test.lookup.AnnotationLookup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootTestApplication.class, args);
        AnnotationLookup annotationLookup = applicationContext.getBean(AnnotationLookup.class);
        System.out.println(annotationLookup.getClass().getDeclaredFields());
    }

}
