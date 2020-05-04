package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.CustomerLossMapper;
import com.shsxt.crm.vo.CustomerLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLossService extends BaseService<CustomerLoss,Integer> {

    @Autowired
    private CustomerLossMapper customerLossMapper;

    public CustomerLoss queryCustomerLossByCusNo(String cusNo) {
       return customerLossMapper.queryCustomerLossByCusNo(cusNo);
    }
}
