package org.shizhenqiang.framework.bean.lifecycle;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, InitializingBean, SmartInitializingSingleton {

    private User user;

    private Integer number;

    private String beanName;
    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void initPostConstruct() {
       this.description = "the userHolder v4";
        System.out.println("initPostConstruct -> " + description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "the userHolder v5";
        System.out.println("afterPropertiesSet -> " + description);
    }

    public void init() {
        this.description = "the userHolder v6";
        System.out.println("init -> " + description);
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", beanName='" + beanName + '\'' +
                ", classLoader=" + classLoader +
                ", beanFactory=" + beanFactory +
                ", environment=" + environment +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    @Override
    public void afterSingletonsInstantiated() {
        this.description = "the userHolder v8";
        System.out.println("afterSingletonsInstantiated -> " + description);
    }
}
