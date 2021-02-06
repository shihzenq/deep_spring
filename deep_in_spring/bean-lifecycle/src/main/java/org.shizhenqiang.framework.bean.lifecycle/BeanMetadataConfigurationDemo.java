package org.shizhenqiang.framework.bean.lifecycle;

import com.shizhenqiang.framework.container.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(defaultListableBeanFactory);
        Resource resource = new ClassPathResource("MATE-INF/user.properties");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);
        User user = defaultListableBeanFactory.getBean(User.class);
        System.out.println(user);
    }
}
