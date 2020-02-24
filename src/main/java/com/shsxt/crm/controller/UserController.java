package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}
