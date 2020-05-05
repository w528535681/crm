package com.shsxt.crm.controller;

import com.github.pagehelper.PageException;
import com.shsxt.base.BaseController;
import com.shsxt.crm.service.ModuleService;
import com.shsxt.crm.service.PermissionService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private ModuleService moduleService;

    /**
     * 登录页
     * @return
     */
    @GetMapping("index")
    public String index(){

        /*if (1==1){

            throw new PageException("参数异常！");
        }*/
        return "index";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        List<String> permissions = permissionService.queryUserHasRolesHasPermissions(userId);
        request.getSession().setAttribute("permissions",permissions);
        request.setAttribute("user",userService.selectByPrimaryKey(userId));
        request.getSession().setAttribute("modules",moduleService.queryUserHasRoleHasModuleDtos(userId));
        return "main";
    }

}
