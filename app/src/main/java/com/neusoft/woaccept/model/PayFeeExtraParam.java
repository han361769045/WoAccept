package com.neusoft.woaccept.model;

/**
 * Created by LeoLu on 2016/9/20.
 */

public class PayFeeExtraParam {
    private String paraId; //保留字段ID String(20)
    private String paraValue; //保留字段值 String(60)

    public String getParaId() {
        return paraId;
    }

    public void setParaId(String paraId) {
        this.paraId = paraId;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }
}
