package com.shsxt.base;

/**
 * 根绝不同条件查询的基本查询类
 */
public class BaseQuery {

    /**
     *  EasyUi分页的当前页
     */

    private Integer page=1;
    /**
     * EasyUi分页的大小
     */
    private Integer rows =10;

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
