package com.neusoft.woaccept.model;

/**
 * Created by LeoLu on 2016/9/22.
 */

public class ReqLogin extends ReqSendCode {

    private String checkCode;

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
