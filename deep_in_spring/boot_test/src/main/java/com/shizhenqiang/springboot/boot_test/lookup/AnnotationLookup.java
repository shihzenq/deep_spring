package com.shizhenqiang.springboot.boot_test.lookup;

import com.shizhenqiang.springboot.boot_test.annotation.MyAutowired;
import com.shizhenqiang.springboot.boot_test.model.ChildUser;
import com.shizhenqiang.springboot.boot_test.model.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.Optional;

@Component
public class AnnotationLookup {

    @Autowired
    private User user;

    @Autowired
    private Map<String, User> userMap;

    @MyAutowired
    private Optional<ChildUser> optionalUser;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Qualifier("childUser")
    @Inject
    private User injectUser;
}
