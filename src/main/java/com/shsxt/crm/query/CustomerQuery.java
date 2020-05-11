package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class CustomerQuery extends BaseQuery {
    private String cusName;

    private String cusNo;

    private String level;

    private String myd;

    // 1  0-1000   2 1000-3000  3  3000-5000  4 50000
    private String type;

    private String time;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMyd() {
        return myd;
    }

    public void setMyd(String myd) {
        this.myd = myd;
    }
}
