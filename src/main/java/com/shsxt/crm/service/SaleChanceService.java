package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.BaseService;
import com.shsxt.crm.enums.DevResult;
import com.shsxt.crm.enums.StateStatus;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.PhoneUtil;
import com.shsxt.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService<SaleChance,Integer> {

    /**
     * 根据条件查询
     * @param saleChanceQuery
     * @return
     */
    public Map<String,Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
        Map<String,Object> result = new HashMap<String, Object>();

        //开启分页
        PageHelper.startPage(saleChanceQuery.getPage(),saleChanceQuery.getRows());

        PageInfo<SaleChance> pageInfo = new PageInfo<>(selectByParams(saleChanceQuery));
        //查询的总数
        result.put("total",pageInfo.getTotal());
        //查询的结果
        result.put("rows",pageInfo.getList());
        return  result;
    }

    /**
     * 营销机会数据添加
     * @param saleChance
     */
    public void saveSaleChances(SaleChance saleChance){
        /**
         * 1.参数校验
         *   customerName:非空
         *   linkMan:非空
         *   linkPhone:非空 11位手机号
         * 2.设置相关参数默认值
         *      state:默认未分配  如果选择分配人  state 为已分配
         *      assignTime:如果  如果选择分配人   时间为当前系统时间
         *      devResult:默认未开发   如果选择分配人  devResult为开发中
         *      isValid :默认有效
         *      createDate updateDate:默认当前系统时间
         *  3.执行添加 判断结果
         */
        checkParams(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone());
        saleChance.setState(StateStatus.UNSTATE.getType());
        saleChance.setDevResult(DevResult.UNDEV.getStstus());
        if (StringUtils.isNotBlank(saleChance.getAssignMan())){
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setDevResult(DevResult.DEVING.getStstus());
            saleChance.setAssignTime(new Date());
        }

        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());

        AssertUtil.isTrue(insertSelective(saleChance)<1,"机会数据添加失败！");

    }

    /**
     * 添加营销机会的参数校验
     * @param customerName
     * @param linkMan
     * @param linkPhone
     */
    private void checkParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName),"请输入客户名！");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan),"请输入联系人！");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"请输入联系电话！");
        AssertUtil.isTrue(!(PhoneUtil.isMobile(linkPhone)),"手机号格式不合法！");
    }

    /**
     * 更新机会数据记录
     * @param saleChance
     */
    public void updateSaleChance(SaleChance saleChance){

        /**
         * 1.参数校验
         *  id 记录存在校验
         *  customerName:非空
         *  linkMan:非空
         *  linkPhone:非空 11位手机号
         * 2. 设置相关参数值
         *      updateDate:系统当前时间
         *         原始记录 未分配 修改后改为已分配(由分配人决定)
         *            state 0->1
         *            assginTime 系统当前时间
         *            devResult 0-->1
         *         原始记录  已分配  修改后 为未分配
         *            state  1-->0
         *            assignTime  待定  null
         *            devResult 1-->0
         * 3.执行更新 判断结果
         */

        //获取要更新的记录id存在校验
        AssertUtil.isTrue(saleChance.getId()==null,"待更新记录不存在!");
        //从数据库查找要更新的临时记录
        SaleChance temp = selectByPrimaryKey(saleChance.getId());
        //判断记录是否存在
        AssertUtil.isTrue(temp==null,"待更新记录不存在!");
        checkParams(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone());
        if (StringUtils.isBlank(temp.getAssignMan())&&StringUtils.isNotBlank(saleChance.getAssignMan())){
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setDevResult(DevResult.DEVING.getStstus());
            saleChance.setAssignTime(new Date());
        }else if(StringUtils.isNotBlank(temp.getAssignMan())&&StringUtils.isNotBlank(saleChance.getAssignMan())){
            saleChance.setAssignMan("");
            saleChance.setAssignTime(null);
            saleChance.setDevResult(DevResult.UNDEV.getStstus());
        }
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(saleChance)<1,"机会数据更新失败!");
    }

    public void deleteSaleChancesByIds(Integer[] ids){
        AssertUtil.isTrue(null==ids||ids.length==0,"请选择待删除的机会数据!");
        AssertUtil.isTrue(deleteBatch(ids)<ids.length,"机会数据删除失败!");
    }
}
