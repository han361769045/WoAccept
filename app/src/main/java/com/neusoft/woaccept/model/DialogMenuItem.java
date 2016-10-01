package com.neusoft.woaccept.model;

/**
 * Created by lyy on 2016/9/15.
 */
public class DialogMenuItem {

    public String operName;
    public int resId;
    public String deviceName;
    public String deviceAddress;
    public boolean isParied;

    public DialogMenuItem(String operName, int resId) {
        this.operName = operName;
        this.resId = resId;

    }
}
