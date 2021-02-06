package com.shizhenqiang.springboot.boot_test.model;

public class ChildUser extends User{

    private String address;

    public ChildUser(Long id, String name, String age, String address) {
        super(id, name, age);
        this.address = address;
    }

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
