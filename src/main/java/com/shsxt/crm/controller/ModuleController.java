package com.shsxt.crm.controller;


import com.shsxt.base.BaseController;
import com.shsxt.crm.dto.TreeDto;
import com.shsxt.crm.query.ModuleQuery;
import com.shsxt.crm.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;


    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeDto> queryAllModules(Integer roleId){
        return moduleService.queryAllModules02(roleId);
    }

    @RequestMapping("/index/{grade}")
    public String index(@PathVariable Integer grade, Integer mid, Model model){

        model.addAttribute("mid",mid);

        if (grade==1){
            return "module_1";
        }else  if(grade==2){
            return "module_2";
        }else if(grade==3){
            return "module_3";
        }else{
            return "error";
        }
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryModulesByParams(ModuleQuery moduleQuery){
        return moduleService.queryByParamsForDataGrid(moduleQuery);
    }

}
