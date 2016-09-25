package com.neusoft.woaccept.model;

import java.util.ArrayList;

/**
 * Created by LeoLu on 2016/9/23.
 */

public class PhoneInfo {
    private String option_value = "";
    private String option_id = "";
    private ArrayList<PhoneItem> option_child;

    public String getOption_value() {
        return option_value;
    }

    public void setOption_value(String option_value) {
        this.option_value = option_value;
    }

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }

    public ArrayList<PhoneItem> getOption_child() {
        return option_child;
    }

    public void setOption_child(ArrayList<PhoneItem> option_child) {
        this.option_child = option_child;
    }
}
