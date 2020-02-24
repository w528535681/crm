package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    /**
     * 登录页
     * @return
     */
    @GetMapping("index")
    public String index(){

        return "index";
    }

    @RequestMapping("main")
    public String main(){

        return "main";
    }

}
