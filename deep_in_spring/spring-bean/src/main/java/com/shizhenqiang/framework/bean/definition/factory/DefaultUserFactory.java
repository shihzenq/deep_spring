package com.shizhenqiang.framework.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {


    @PostConstruct
    public void init() {
        System.out.println("PostConstruct注解DefaultUserFactory 初始化中");
    }


    public void initMethod() {
        System.out.println("自定义initMethod DefaultUserFactory 初始化中");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实现 InitializingBean#afterPropertiesSet() DefaultUserFactory 初始化中");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy 注解销毁");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean 方法destroy  销毁");
    }

    public void doDestroy() throws Exception {
        System.out.println("自定义doDestroy() 销毁");
    }
}
