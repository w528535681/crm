package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.annotaions.CrmLog;
import com.shsxt.crm.annotaions.RequirePermission;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @RequestMapping("index")
    @CrmLog(module = "营销机会",oper = "主页展示")
    public String index(){
        return "sale_chance";
    }

    @RequestMapping("list")
    @CrmLog(module = "营销管理",oper = "多条件查询")
    @ResponseBody
    @RequirePermission(code = "101001")
    public Map<String,Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
        return saleChanceService.querySaleChancesByParams(saleChanceQuery);
    }

    @RequestMapping("save")
    @CrmLog(module = "营销管理",oper = "添加")
    @ResponseBody
    @RequirePermission(code = "101002")
    public ResultInfo saveSaleChances(SaleChance saleChance){
        saleChance.setCreateMan(userService.selectByPrimaryKey(LoginUserUtil.releaseUserIdFromCookie(request)).getTrueName());
        saleChanceService.saveSaleChances(saleChance);
        return success("机会数据添加成功！");
    }

    @RequestMapping("update")
    @CrmLog(module = "营销管理",oper = "更新")
    @ResponseBody
    @RequirePermission(code = "101004")
    public ResultInfo updateSaleChance(SaleChance saleChance){
        saleChanceService.updateSaleChance(saleChance);
        return success("数据更新成功!");
    }

    @RequestMapping("delete")
    @CrmLog(module = "营销管理",oper = "删除")
    @ResponseBody
    @RequirePermission(code = "101003")
    public ResultInfo deleteSaleChancesByIds(Integer[] ids){
        saleChanceService.deleteSaleChancesByIds(ids);
        return success("数据删除成功!");
    }
}
