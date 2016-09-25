package com.neusoft.woaccept.model;

/**
 * Created by LeoLu on 2016/9/23.
 */

public class ReqPayment {

    private String tradeTypeCode ; //业务编码，办理业务的代码标识（业务参数编码表）默认9999 String(4)
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
    private String woOrderId; //受理系统订单编号 String(20)
    private PayFeeExtraParam para; //保留字段 String(20)
    private String feeTime;

    /**
     * 2,3G
     */
    private String is_city_code = null;
    private String in_service_kind = null;
    private String is_service_id = null;
    private String in_customer_id = null;
    private String in_account_id = null;
    private String in_user_id = null;
    private String in_pay_fee = null;
    private String in_pay_way = null;
    private String is_dealer_id = null;
    private String is_operate_id = null;
    private String is_ip_address = null;
    private String is_operate_date = null;
    private String is_wo_order_id = null;

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

    public String getWoOrderId() {
        return woOrderId;
    }

    public void setWoOrderId(String woOrderId) {
        this.woOrderId = woOrderId;
    }

    public PayFeeExtraParam getPara() {
        return para;
    }

    public void setPara(PayFeeExtraParam para) {
        this.para = para;
    }

    public String getFeeTime() {
        return feeTime;
    }

    public void setFeeTime(String feeTime) {
        this.feeTime = feeTime;
    }

    public String getIs_city_code() {
        return is_city_code;
    }

    public void setIs_city_code(String is_city_code) {
        this.is_city_code = is_city_code;
    }

    public String getIn_service_kind() {
        return in_service_kind;
    }

    public void setIn_service_kind(String in_service_kind) {
        this.in_service_kind = in_service_kind;
    }

    public String getIs_service_id() {
        return is_service_id;
    }

    public void setIs_service_id(String is_service_id) {
        this.is_service_id = is_service_id;
    }

    public String getIn_customer_id() {
        return in_customer_id;
    }

    public void setIn_customer_id(String in_customer_id) {
        this.in_customer_id = in_customer_id;
    }

    public String getIn_account_id() {
        return in_account_id;
    }

    public void setIn_account_id(String in_account_id) {
        this.in_account_id = in_account_id;
    }

    public String getIn_user_id() {
        return in_user_id;
    }

    public void setIn_user_id(String in_user_id) {
        this.in_user_id = in_user_id;
    }

    public String getIn_pay_fee() {
        return in_pay_fee;
    }

    public void setIn_pay_fee(String in_pay_fee) {
        this.in_pay_fee = in_pay_fee;
    }

    public String getIn_pay_way() {
        return in_pay_way;
    }

    public void setIn_pay_way(String in_pay_way) {
        this.in_pay_way = in_pay_way;
    }

    public String getIs_dealer_id() {
        return is_dealer_id;
    }

    public void setIs_dealer_id(String is_dealer_id) {
        this.is_dealer_id = is_dealer_id;
    }

    public String getIs_operate_id() {
        return is_operate_id;
    }

    public void setIs_operate_id(String is_operate_id) {
        this.is_operate_id = is_operate_id;
    }

    public String getIs_ip_address() {
        return is_ip_address;
    }

    public void setIs_ip_address(String is_ip_address) {
        this.is_ip_address = is_ip_address;
    }

    public String getIs_operate_date() {
        return is_operate_date;
    }

    public void setIs_operate_date(String is_operate_date) {
        this.is_operate_date = is_operate_date;
    }

    public String getIs_wo_order_id() {
        return is_wo_order_id;
    }

    public void setIs_wo_order_id(String is_wo_order_id) {
        this.is_wo_order_id = is_wo_order_id;
    }

    public String getInfoList() {
        return infoList;
    }

    public void setInfoList(String infoList) {
        this.infoList = infoList;
    }
}
