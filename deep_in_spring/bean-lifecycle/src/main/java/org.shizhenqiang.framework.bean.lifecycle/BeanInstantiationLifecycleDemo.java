package org.shizhenqiang.framework.bean.lifecycle;

import com.shizhenqiang.framework.container.ioc.domain.ChildUser;
import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加BeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitions = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/MATE-INF/dependency-lookup-context.xml");
        System.out.println(beanDefinitions);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User childUser = beanFactory.getBean("childUser", User.class);
        System.out.println(childUser);
    }


    static  class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

            if (ObjectUtils.nullSafeEquals("childUser", beanName) && ChildUser.class.equals(beanClass)) {
                return new ChildUser();
            }
            return null;
        }
    }
}
