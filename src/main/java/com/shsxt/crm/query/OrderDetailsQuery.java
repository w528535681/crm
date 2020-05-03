package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class OrderDetailsQuery extends BaseQuery {
    // 订单id
    private Integer orderId;
    // 商品名
    private String goodsName;
    // 商品价格区间
    private Integer type;



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
