package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;
import com.shsxt.crm.vo.Role;

public class RoleQuery extends BaseQuery {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
