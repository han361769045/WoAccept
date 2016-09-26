package com.neusoft.woaccept.model;

/**
 * Created by LeoLu on 2016/9/26.
 */

public class PhoneNumberType {

    private String queryType;

    private String queryPara;

    private String typeName;

    private String typeDesc;

    public PhoneNumberType() {

    }

    public PhoneNumberType(String queryType, String queryPara, String typeName, String typeDesc) {
        this.queryType = queryType;
        this.queryPara = queryPara;
        this.typeName = typeName;
        this.typeDesc = typeDesc;
    }


    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getQueryPara() {
        return queryPara;
    }

    public void setQueryPara(String queryPara) {
        this.queryPara = queryPara;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
}
