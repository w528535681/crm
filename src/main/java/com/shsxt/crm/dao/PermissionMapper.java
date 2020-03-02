package com.shsxt.crm.dao;

import com.shsxt.base.BaseMapper;
import com.shsxt.crm.vo.Permission;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    public int countPermissionByRoleId(Integer roleId);

    public int deletePermissionsByRoleId(Integer roleId);
}