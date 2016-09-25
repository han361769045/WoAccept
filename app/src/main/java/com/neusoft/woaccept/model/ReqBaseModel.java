package com.neusoft.woaccept.model;

/**
 * Created by LeoLu on 2016/9/22.
 */

public class ReqBaseModel<T> {

    private String action;
    private String sessionID;
    private String type = "android";
    private String ip = "127.0.0.1";
    private T req;

    /***
     * 4G
     */
    private String if34g = null;
    private String is_wo_order_id = null;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public T getReq() {
        return req;
    }

    public void setReq(T req) {
        this.req = req;
    }

    public String getIf34g() {
        return if34g;
    }

    public void setIf34g(String if34g) {
        this.if34g = if34g;
    }

    public String getIs_wo_order_id() {
        return is_wo_order_id;
    }

    public void setIs_wo_order_id(String is_wo_order_id) {
        this.is_wo_order_id = is_wo_order_id;
    }
}
