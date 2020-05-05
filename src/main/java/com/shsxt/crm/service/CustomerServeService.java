package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.enums.CustomerServeStatus;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.CustomerServe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServeService extends BaseService<CustomerServe,Integer> {


    @Autowired
    private CustomerMapper customerMapper;

    public void saveCustomerServe(CustomerServe customerServe){
        /**
         * 1.参数校验
         *     客户名  非空
         *     客户类型  非空
         * 2.添加默认值
         *    state  设置状态值
         *    isValid  createDate updateDate
         *  3.执行添加 判断结果
         */

        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getCustomer()),"请指定客户!");
        AssertUtil.isTrue(null==customerMapper.queryCustomerByName(customerServe.getCustomer()),"当前客户暂不存在!");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServeType()),"请指定服务类型!");
        customerServe.setIsValid(1);
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());
        customerServe.setState(CustomerServeStatus.CREATED.getState());
        AssertUtil.isTrue(insertSelective(customerServe)<1,"服务记录添加失败!");
    }
}
