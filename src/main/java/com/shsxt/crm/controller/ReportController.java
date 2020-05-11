package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {


    @RequestMapping("{type}")
    public String index(@PathVariable Integer type){
        if(null !=type ){
            if(type ==0){
                return "customer_contri";
            }else{
                return "";
            }
        }else{
            return "";
        }
    }
}
