package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerService;
import com.shsxt.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("index")
    public String index(){
        return "customer";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomersByParams(CustomerQuery customerQuery){
        return customerService.queryByParamsForDataGrid(customerQuery);
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveCustomer(Customer customer){
        customerService.saveCustomer(customer);
        return success("客户添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCustomer(Customer customer){
        customerService.updateCustomer(customer);
        return success("客户更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomer(Integer id){
        customerService.deleteCustomer(id);
        return success("客户删除成功");
    }

    @RequestMapping("order_info")
    public String showOrderInfo(Integer cid, Model model){
        model.addAttribute("customer",customerService.selectByPrimaryKey(cid));
        return "customer_order";
    }

    @RequestMapping("queryCustomerContributionByParams")
    @ResponseBody
    public Map<String,Object> queryCustomerContributionByParams(CustomerQuery customerQuery){
        return customerService.queryCustomerContributionByParams(customerQuery);
    }

    @RequestMapping("countCustomerMake")
    @ResponseBody
    public Map<String,Object> countCustomerMake(){
        return customerService.countCustomerMake();
    }

    @RequestMapping("countCustomerMake02")
    @ResponseBody
    public Map<String,Object> countCustomerMake02(){
        return customerService.countCustomerMake02();
    }
}
