package com.shsxt.crm.enums;

/**
 * 开发情况
 */
public enum DevResult {

    // 未开发
    UNDEV(0),

    // 开发中
    DEVING(1),

    // 开发成功
    DEV_SUCCESS(2),

    // 开发失败
    DEV_FAILED(3);

    private Integer ststus;

    DevResult(Integer ststus) {
        this.ststus = ststus;
    }

    public Integer getStstus() {
        return ststus;
    }
}
