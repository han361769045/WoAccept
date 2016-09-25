package com.neusoft.woaccept.model;

import java.util.List;

/**
 * Created by LeoLu on 2016/9/23.
 */

public class ResPhoneNumber extends ResBaseModel {

    private List<PhoneNumber> numInfo;

    public List<PhoneNumber> getNumInfo() {
        return numInfo;
    }

    public void setNumInfo(List<PhoneNumber> numInfo) {
        this.numInfo = numInfo;
    }
}

