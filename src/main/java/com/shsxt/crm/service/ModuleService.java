package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dto.TreeDto;
import com.shsxt.crm.vo.Module;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    public List<TreeDto> queryAllModules(){
        return moduleMapper.queryAllModules();
    }
}
