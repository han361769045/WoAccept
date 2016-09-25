package com.neusoft.woaccept.model;

import java.io.Serializable;


/**
 * Created by LeoLu on 2016/9/23.
 */

public class ResPayment<T> extends ResLogin implements Serializable, Cloneable {

    private T custInfo;
    private String mustPay;
    private String fee;
    private String sysType;
    private String if34g;
    private String realTimeFee;
    private String realTimeBalance;
    private String creditFee;

    public T getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(T custInfo) {
        this.custInfo = custInfo;
    }

    public String getMustPay() {
        return mustPay;
    }

    public void setMustPay(String mustPay) {
        this.mustPay = mustPay;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getIf34g() {
        return if34g;
    }

    public void setIf34g(String if34g) {
        this.if34g = if34g;
    }

    public String getRealTimeFee() {
        return realTimeFee;
    }

    public void setRealTimeFee(String realTimeFee) {
        this.realTimeFee = realTimeFee;
    }

    public String getRealTimeBalance() {
        return realTimeBalance;
    }

    public void setRealTimeBalance(String realTimeBalance) {
        this.realTimeBalance = realTimeBalance;
    }

    public String getCreditFee() {
        return creditFee;
    }

    public void setCreditFee(String creditFee) {
        this.creditFee = creditFee;
    }

    public ResPayment<T> clone() {
        try {
            return (ResPayment<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
