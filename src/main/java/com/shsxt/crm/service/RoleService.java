package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("all")
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;

    public List<Map<String,Object>> queryAllRoles(){
        return roleMapper.queryAllRoles();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveRole(Role role){
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"请输入角色名!");
        Role temp = roleMapper.queryRoleByRoleName(role.getRoleName());
        AssertUtil.isTrue(null != temp,"该角色已存在!");
        role.setId(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(insertSelective(role)<1,"角色记录添加失败!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role){
        AssertUtil.isTrue(null==role.getId()||null==selectByPrimaryKey(role.getId()),"待修改的记录不存在!");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"请输入角色名!");
        Role temp = roleMapper.queryRoleByRoleName(role.getRoleName());
        //AssertUtil.isTrue(temp != null && !(temp.getRoleName().equals(role.getRoleName())),"该角色已存在!");
        AssertUtil.isTrue(temp != null && !(temp.getId().equals(role.getId())),"该角色已存在!");
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(role)<1,"角色记录更新失败!");
    }

    public void deleteRole(Integer roleId){
        Role temp = selectByPrimaryKey(roleId);
        AssertUtil.isTrue(temp==null || roleId ==null,"待删除的记录不存在!");
        temp.setIsValid(0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(temp)<1,"角色记录删除失败!");
    }
}
