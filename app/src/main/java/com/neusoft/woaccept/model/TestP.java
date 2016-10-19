package com.neusoft.woaccept.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by LeoLu on 2016/10/19.
 */
@Parcel
public class TestP {

    String name;
    int age;

    public TestP() {
    }

    @ParcelConstructor
    public TestP(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
