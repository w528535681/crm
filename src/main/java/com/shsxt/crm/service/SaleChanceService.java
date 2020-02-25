package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.BaseService;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService<SaleChance,Integer> {

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
}
