package com.neusoft.woaccept.model;

/**
 * Created by LeoLu on 2016/9/23.
 */

public class ReqPayment {

    private String tradeTypeCode; //业务编码，办理业务的代码标识（业务参数编码表）默认9999 String(4)
    private String serviceClassCode; //电信业务类别（电信业务类别）默认0000  String(4)
    private String serialNumber;  //服务号码 String(40)
    private String fee; //缴费金额，单位分 String(15)
    private String infoList;
    /***
     * String(3)	支付机构编码
     * 000：其它
     * 099：天津滨河
     * 100：银联；
     * 198：mini厅银联；
     * 199：自助终端银联
     * 200：快钱
     * 300：支付宝
     * 400：易宝；
     * 499：自助终端易宝信用卡
     * 500：网银在线
     * 600：财付通
     * 700：环迅
     * 800：现金
     * 900：沃支付
     * 997：沃支付刷卡
     * 998：MINI沃支付
     * 999：自助终端沃支付
     * A00：拉卡拉
     * B00：福卡
     * F00 发票兑奖
     */
    private String chargeParty;// String(3)  800
    private PayFeeExtraParam para; //保留字段 String(20)
    private String flag; //0：快速缴费(直接提交)  1：普通缴费(先查询再提交)
    private String serviceKind; //服务类型(flag=1&&if34g=3必传)if34g为查询返回
    private String customerId;//客户编号(flag=1&&if34g=3必传)
    private String accountId;   //账户编号(flag=1&&if34g=3必传)
    private String userId; //用户编号(flag=1&&if34g=3必传)
    private String orderId; //沃受理系统订单编号 String(20)

    private String channelType;//渠道类型 String(7)
    private String operatorId;//操作员ID String(20)
    private String channelId_bss;
    private String channelType_bss;
    private String city;//地市 String(3)
    private String province;//省分 String(2)
    private String district;//区县 String(10)
    private String channelId;//渠道编码 String(20)
    private String operatorId_bs;
    private String operatorId_cb;

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getChannelId_bss() {
        return channelId_bss;
    }

    public void setChannelId_bss(String channelId_bss) {
        this.channelId_bss = channelId_bss;
    }

    public String getChannelType_bss() {
        return channelType_bss;
    }

    public void setChannelType_bss(String channelType_bss) {
        this.channelType_bss = channelType_bss;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getOperatorId_bs() {
        return operatorId_bs;
    }

    public void setOperatorId_bs(String operatorId_bs) {
        this.operatorId_bs = operatorId_bs;
    }

    public String getOperatorId_cb() {
        return operatorId_cb;
    }

    public void setOperatorId_cb(String operatorId_cb) {
        this.operatorId_cb = operatorId_cb;
    }

    public String getTradeTypeCode() {
        return tradeTypeCode;
    }

    public void setTradeTypeCode(String tradeTypeCode) {
        this.tradeTypeCode = tradeTypeCode;
    }

    public String getServiceClassCode() {
        return serviceClassCode;
    }

    public void setServiceClassCode(String serviceClassCode) {
        this.serviceClassCode = serviceClassCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getChargeParty() {
        return chargeParty;
    }

    public void setChargeParty(String chargeParty) {
        this.chargeParty = chargeParty;
    }


    public PayFeeExtraParam getPara() {
        return para;
    }

    public void setPara(PayFeeExtraParam para) {
        this.para = para;
    }

    public String getInfoList() {
        return infoList;
    }

    public void setInfoList(String infoList) {
        this.infoList = infoList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getServiceKind() {
        return serviceKind;
    }

    public void setServiceKind(String serviceKind) {
        this.serviceKind = serviceKind;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
