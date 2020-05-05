package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.service.CustomerServeService;
import com.shsxt.crm.vo.CustomerServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController {

    @Autowired
    private CustomerServeService customerServeService;

    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type){

        if (type==1){
            return "customer_serve_create";
        }else if (type==2){
            return "";
        }else {
            return "";
        }
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveCustomerServe(CustomerServe customerServe){
         customerServeService.saveCustomerServe(customerServe);
         return success("服务创建成功!");
    }
}
