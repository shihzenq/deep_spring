package com.shizhenqiang.framework.bean.definition.factory;

import com.shizhenqiang.framework.container.ioc.domain.User;

public interface UserFactory {

    default User createUser(){
        return User.creatUser();
    }
}
