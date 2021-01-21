package com.shizhenqiang.framework.container.ioc.domain;

import com.shizhenqiang.framework.container.ioc.annotation.Child;

@Child
public class ChildUser extends User{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ChildUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
