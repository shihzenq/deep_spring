package org.shizhenqiang.framework.bean.lifecycle;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanDefinitionMergedDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitions = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/MATE-INF/dependency-lookup-context.xml");
        System.out.println(beanDefinitions);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User childUser = beanFactory.getBean("childUser", User.class);
        System.out.println(childUser);

    }
}
