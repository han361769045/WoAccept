package com.neusoft.woaccept.model;

import java.util.List;

/**
 * Created by LeoLu on 2016/9/26.
 */

public class ReqSelectPhoneNumber {

    /**
     * channelId : 84a0099
     * is3G :
     * qryCbss : 1
     * channelType : 1010400
     * city : 841
     * operatorId : SXITEST00
     * queryParas : [{"queryPara":"6","queryType":"04"}]
     * district : 029
     * groupFlag : 3
     * province : 84
     * serType : 1
     * busType : 1
     */

    private String channelId;
    private String is3G;
    private String qryCbss;
    private String channelType;
    private String city;
    private String operatorId;
    private String district;
    private String groupFlag;
    private String province;
    private String serType;
    private String busType;
    /**
     * queryPara : 6
     * queryType : 04
     */

    private List<QueryParasEntity> queryParas;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getIs3G() {
        return is3G;
    }

    public void setIs3G(String is3G) {
        this.is3G = is3G;
    }

    public String getQryCbss() {
        return qryCbss;
    }

    public void setQryCbss(String qryCbss) {
        this.qryCbss = qryCbss;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(String groupFlag) {
        this.groupFlag = groupFlag;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSerType() {
        return serType;
    }

    public void setSerType(String serType) {
        this.serType = serType;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public List<QueryParasEntity> getQueryParas() {
        return queryParas;
    }

    public void setQueryParas(List<QueryParasEntity> queryParas) {
        this.queryParas = queryParas;
    }
}
