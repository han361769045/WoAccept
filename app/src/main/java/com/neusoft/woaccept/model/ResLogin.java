package com.neusoft.woaccept.model;

import java.util.List;

/**
 * Created by LeoLu on 2016/9/22.
 */

public class ResLogin extends ResBaseModel {

    private String channelType;//渠道类型 String(7)
    private String operatorId;//操作员ID String(20)
    private String wrCard;
    private String channelId_bss;
    private String channelType_bss;
    private String city;//地市 String(3)
    private String province;//省分 String(2)
    private String district;//区县 String(10)
    private String channelId;//渠道编码 String(20)
    private String operatorId_bs;
    private String operatorId_cb;
    private int ifagent;
    private String passwordtest;//预受理密码
    private String organName;

    /**
     * id : 810
     * name : 成都
     */

    private List<CitylistBean> citylist;
    /**
     * select_id : 11
     * select_name :
     * optioninfo : []
     */

    private List<SelectDataBean> selectData;
    /**
     * valid : 1
     * cb_menu :
     * menuId : pre_af
     * f_menu_desc :
     * menuLink : productChange/numberEntry.jsp
     * workNo : cdjf0010
     * menuName : 产品变更
     * bss_menu :
     */

    private List<WorkMenuBean> workMenu;
    /**
     * access_type : B4M
     * CB_ACCESS_TYPE_NAME : NGN
     * net_type_code : 30
     * CB_ACCESS_TYPE : A02
     * access_type_name : IPPBX
     */

    private List<AccessTypeBean> access_type;
    /**
     * REGION_NAME : 不区分
     * REGION_CODE : 810000
     */

    private List<AddressBean> address;
    /**
     * f_page_link : mobileNetwork/mobileNetworkNumber.jsp
     * single_name :
     * button_list : [{"button_id":"pre_m_a","button_name":"读取身份证"},{"button_id":"pre_m_c","button_name":"继承"}]
     * cb_menu :
     * menuId : pre_m
     * systemId : pre1
     * parent_menuId : pre_parent_a
     * f_menu_desc : images/mobile-net.jpg
     * single_name_rgb :
     * menuName : 新装入网
     * bss_menu :
     */

    private List<MenuCollBean> MenuColl;

    public String getPasswordtest() {
        return passwordtest;
    }

    public void setPasswordtest(String passwordtest) {
        this.passwordtest = passwordtest;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

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

    public String getWrCard() {
        return wrCard;
    }

    public void setWrCard(String wrCard) {
        this.wrCard = wrCard;
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

    public int getIfagent() {
        return ifagent;
    }

    public void setIfagent(int ifagent) {
        this.ifagent = ifagent;
    }

    public List<CitylistBean> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CitylistBean> citylist) {
        this.citylist = citylist;
    }

    public List<SelectDataBean> getSelectData() {
        return selectData;
    }

    public void setSelectData(List<SelectDataBean> selectData) {
        this.selectData = selectData;
    }

    public List<WorkMenuBean> getWorkMenu() {
        return workMenu;
    }

    public void setWorkMenu(List<WorkMenuBean> workMenu) {
        this.workMenu = workMenu;
    }

    public List<AccessTypeBean> getAccess_type() {
        return access_type;
    }

    public void setAccess_type(List<AccessTypeBean> access_type) {
        this.access_type = access_type;
    }

    public List<AddressBean> getAddress() {
        return address;
    }

    public void setAddress(List<AddressBean> address) {
        this.address = address;
    }

    public List<MenuCollBean> getMenuColl() {
        return MenuColl;
    }

    public void setMenuColl(List<MenuCollBean> MenuColl) {
        this.MenuColl = MenuColl;
    }


}
