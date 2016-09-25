package com.neusoft.woaccept.model;

import java.util.List;

/**
 * Created by LeoLu on 2016/9/23.
 */

public class MenuCollBean {
    private String menuId;
    private String menuName;
    /**
     * button_id : pre_m_a
     * button_name : 读取身份证
     */

    private List<ButtonListBean> button_list;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<ButtonListBean> getButton_list() {
        return button_list;
    }

    public void setButton_list(List<ButtonListBean> button_list) {
        this.button_list = button_list;
    }
}
