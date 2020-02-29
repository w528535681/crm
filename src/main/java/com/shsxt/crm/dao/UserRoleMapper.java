package com.shsxt.crm.dao;

import com.shsxt.base.BaseMapper;
import com.shsxt.crm.vo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {


    public int countRoleByUserId(int userId);
    public int deleteUserRoleByUserId(Integer userId);

}