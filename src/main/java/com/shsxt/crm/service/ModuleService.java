package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dao.PermissionMapper;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.dto.TreeDto;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    public List<TreeDto> queryAllModules(){
        return moduleMapper.queryAllModules();
    }

    public List<TreeDto> queryAllModules02(Integer roleId) {
        List<TreeDto> treeDtos = moduleMapper.queryAllModules();

        // 根据角色id 查询角色拥有的菜单id  List<Integer>
        List<Integer> roleHasMids = permissionMapper.queryRoleHasAllModuleIdsByRoleId(roleId);

        if (null != roleHasMids && roleHasMids.size()>0){
            treeDtos.forEach(treeDto -> {
                if (roleHasMids.contains(treeDto.getId())){
                    //说明当前角色 分配了该菜单
                    treeDto.setChecked(true);
                }
            });
        }
        return treeDtos;
    }

    public void saveModule(Module module){

        /**
         * 1.参数校验
         *     模块名-module_name
         *         非空  同一层级下模块名唯一
         *     url
         *         二级菜单  非空  不可重复
         *     上级菜单-parent_id
         *         一级菜单   null
         *         二级|三级菜单 parent_id 非空 必须存在
         *      层级-grade
         *          非空  0|1|2
         *       权限码 optValue
         *          非空  不可重复
         * 2.参数默认值设置
         *     is_valid  create_date update_date
         * 3.执行添加 判断结果
         */
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"请输入菜单名!");
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null==grade||!(grade==0||grade==1||grade==2),"菜单层级不正确！");
        AssertUtil.isTrue(null != moduleMapper.queryModuleByGradeAndModuleName(module.getGrade(),module.getModuleName()),"该层级下菜单重复!");

        if (grade == 1){
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"请指定二级菜单url值");
            AssertUtil.isTrue(null != moduleMapper.queryModuleByGradeAndUrl(module.getGrade(),module.getUrl()),"二级菜单url不可重复!");
        }

        if (grade != 0){
            Integer parenId = module.getParentId();
            AssertUtil.isTrue(null==parenId || null==selectByPrimaryKey(parenId),"请指定上级菜单!");
        }
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"请输入权限码!");
        AssertUtil.isTrue(null != moduleMapper.queryModuleByOptValue(module.getOptValue()),"权限码重复!");

        module.setIsValid((byte)1);
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());
        AssertUtil.isTrue(insertSelective(module)<1,"菜单添加失败!");
    }

    public List<Map<String,Object>> queryAllModulesByGrade(Integer grade){
        return moduleMapper.queryAllModulesByGrade(grade);
    }

    /**
     * 修改菜单
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @SuppressWarnings("all")
    public void updateModule(Module module){
        /**
         * 1.参数校验
         *     id 非空 记录存在
         *     模块名-module_name
         *         非空  同一层级下模块名唯一
         *
         *     url
         *         二级菜单  非空  不可重复
         *     上级菜单-parent_id
         *         二级|三级菜单 parent_id 非空 必须存在
         *      层级-grade
         *          非空  0|1|2
         *       权限码 optValue
         *          非空  不可重复
         * 2.参数默认值设置
         *      update_date
         * 3.执行更新 判断结果
         */
        AssertUtil.isTrue(null == module.getId() || null == selectByPrimaryKey(module.getId()),"待更新记录不存在!");
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"请指定菜单名称!");

        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0||grade==1||grade==2),"菜单层级不合法!");

        Module temp = moduleMapper.queryModuleByGradeAndModuleName(grade,module.getModuleName());
        if(null != temp){
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())),"该层级下菜单已存在!");
        }

        if (grade == 1){
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"请指定二级菜单url值!");
            temp = moduleMapper.queryModuleByGradeAndUrl(grade,module.getUrl());
            if (temp != null){
                AssertUtil.isTrue(!(temp.getId().equals(module.getId())),"该层级下url已存在!");
            }
        }

        if (grade != 0){
            Integer parentId = module.getParentId();
            AssertUtil.isTrue(null == parentId || null == selectByPrimaryKey(parentId),"请指定上级菜单!");
        }

        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"请输入权限码!");

        temp = moduleMapper.queryModuleByOptValue(module.getOptValue());
        if (null != temp){
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())),"权限码已存在!");
        }
        module.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(module)<1,"菜单更新失败!");
    }

    @SuppressWarnings("all")
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModuleById(Integer mid){
        Module temp = selectByPrimaryKey(mid);
        AssertUtil.isTrue(null == temp || null == mid,"待删除记录不存在!");
        /**
         * 如果存在子菜单 不允许删除
         */
        int count = moduleMapper.countSubModuleByParentId(mid);
        AssertUtil.isTrue(count>0,"存在子菜单，不支持删除操作!");
        //  权限表
        count = permissionMapper.countPermissionsByModuleId(mid);
        if (count>0){
            AssertUtil.isTrue(permissionMapper.deletePermissionsByModuleId(mid)<count,"菜单删除失败!");
        }
        temp.setIsValid((byte)0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(temp)<1,"菜单删除失败!");
    }

    public List<ModuleDto> queryUserHasRoleHasModuleDtos(Integer userId){
          /* 1.查询用户角色分配的一级菜单
           * 2.根据一级菜单查询用户角色分配的二级菜单
           */
          List<ModuleDto> moduleDtos = moduleMapper.queryUserHasRoleHasModuleDtos(userId,0,null);
          if (null != moduleDtos && moduleDtos.size()>0){
              moduleDtos.forEach(moduleDto ->{
                  moduleDto.setSubModules(moduleMapper.queryUserHasRoleHasModuleDtos(userId,1,moduleDto.getId()));
              });
          }
          return moduleDtos;
    }
}
