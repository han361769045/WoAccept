package com.neusoft.woaccept.model;

/**
 * Created by LeoLu on 2016/9/22.
 */

public class ResBaseModel<T> {

    private String code;
    private String detail;
    private String sessionID;

    //查询电话号码返回的字段
    private T numInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public T getNumInfo() {
        return numInfo;
    }

    public void setNumInfo(T numInfo) {
        this.numInfo = numInfo;
    }
}
