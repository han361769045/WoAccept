package com.neusoft.woaccept.model;

/**
 * Created by LeoLu on 2016/9/22.
 */

public class ReqSendCode extends ReqBaseModel {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
