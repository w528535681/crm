package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.exceptions.ParamsException;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("queryUserByUserId")
    @ResponseBody
    public User queryUserByUserId(Integer userId){

        return userService.selectByPrimaryKey(userId);
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){

        UserModel userModel = userService.login(userName,userPwd);

        return success("用户登录成功！",userModel);

    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request,String oldPassword, String newPassword,String confirmPassword){

        userService.updateUserPassword(LoginUserUtil.releaseUserIdFromCookie(request),oldPassword,newPassword,confirmPassword);

        return success("密码更新成功！");
    }

    @RequestMapping("index")
    public String index(){
        return "user";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveUser(User user){
        userService.saveUser(user);
        return success("用户记录添加成功!");
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryUsersByParams(UserQuery userQuery){
        return userService.queryByParamsForDataGrid(userQuery);
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户更新成功！");
    }


    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer userId){
        userService.deleteUser(userId);
        return success("用户删除成功！");
    }

}
