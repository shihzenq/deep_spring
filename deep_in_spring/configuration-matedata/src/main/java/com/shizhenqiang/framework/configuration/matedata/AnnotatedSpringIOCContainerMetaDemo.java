package com.shizhenqiang.framework.configuration.matedata;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;

/**
 * 基于注解的 Spring IOC 容器元信息配置
 */

@ImportResource("classpath:/MATE-INF/dependency-lookup-context.xml")
@Import(User.class)
public class AnnotatedSpringIOCContainerMetaDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotatedSpringIOCContainerMetaDemo.class);

        applicationContext.refresh();

        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);

        for (Map.Entry<String, User> entry : beansOfType.entrySet()) {

            System.out.printf("user bean name : %s, content: %s \n", entry.getKey(), entry.getValue());
        }

        applicationContext.close();
    }
}
