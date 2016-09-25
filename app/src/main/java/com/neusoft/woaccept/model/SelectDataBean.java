package com.neusoft.woaccept.model;

import java.util.List;

/**
 * Created by LeoLu on 2016/9/23.
 */

public class SelectDataBean {
    private String select_id;
    private String select_name;
    private List<PhoneInfo> optioninfo;

    public String getSelect_id() {
        return select_id;
    }

    public void setSelect_id(String select_id) {
        this.select_id = select_id;
    }

    public String getSelect_name() {
        return select_name;
    }

    public void setSelect_name(String select_name) {
        this.select_name = select_name;
    }

    public List<PhoneInfo> getOptioninfo() {
        return optioninfo;
    }

    public void setOptioninfo(List<PhoneInfo> optioninfo) {
        this.optioninfo = optioninfo;
    }
}
