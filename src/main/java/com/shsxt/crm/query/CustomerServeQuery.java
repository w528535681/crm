package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class CustomerServeQuery extends BaseQuery {
    // 服务状态
    private String state;
    // 客户
    private String customer;
    // 服务类型
    private String type;

    //  分配用户
    private Integer assigner;

    public Integer getAssigner() {
        return assigner;
    }

    public void setAssigner(Integer assigner) {
        this.assigner = assigner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
