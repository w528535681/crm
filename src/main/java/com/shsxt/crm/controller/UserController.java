package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.exceptions.ParamsException;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("user/queryUserByUserId")
    @ResponseBody
    public User queryUserByUserId(Integer userId){

        return userService.selectByPrimaryKey(userId);
    }

    @PostMapping("user/login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){

        ResultInfo resultInfo = new ResultInfo();

        try {
            UserModel userModel = userService.login(userName,userPwd);
            resultInfo.setResult(userModel);
        } catch (ParamsException pe) {
            pe.printStackTrace();
            resultInfo.setCode(pe.getCode());
            resultInfo.setMsg(pe.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            resultInfo.setMsg("failed");
            resultInfo.setCode(500);
        }
        return resultInfo;
    }

}
