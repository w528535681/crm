package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@SuppressWarnings("all")
public class CustomerService extends BaseService<Customer,Integer> {

    @Autowired
    private CustomerMapper customerMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCustomer(Customer customer){

        /**
         * 1.参数校验
         *    客户名称 name 非空  不可重复
         *    phone 联系电话  非空  格式符合规范
         *    法人  非空
         * 2.默认值设置
         *     isValid  state  cteaetDate  updadteDate
         *      khno 系统生成 唯一  (uuid| 时间戳 | 年月日时分秒  雪花算法)
         *3.执行添加  判断结果
         */
        checkParams(customer.getName(),customer.getPhone(),customer.getFr());
        AssertUtil.isTrue(null == customerMapper.queryCustomerByName(customer.getName()),"该客户已存在!");
        customer.setIsValid(1);
        customer.setState(0);
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        String khno = "KH_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        customer.setKhno(khno);
        AssertUtil.isTrue(insertSelective(customer)<1,"客户添加失败!");
    }

    /**
     * 参数校验
     * @param name
     * @param phone
     * @param fr
     */
    private void checkParams(String name, String phone, String fr) {
        AssertUtil.isTrue(StringUtils.isBlank(name),"请指定客户名称!");
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号格式非法!");
        AssertUtil.isTrue(StringUtils.isBlank(fr),"请指定公司法人!");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer){
        /**
         * 1.参数校验
         *    记录存在校验
         *    客户名称 name 非空  不可重复
         *    phone 联系电话  非空  格式符合规范
         *    法人  非空
         * 2.默认值设置
         *      updadteDate
         *3.执行更新  判断结果
         */
        AssertUtil.isTrue(null == customer.getId() || null == selectByPrimaryKey(customer.getId()),"待更新记录不存在!");
        checkParams(customer.getName(),customer.getPhone(),customer.getFr());
        Customer temp = customerMapper.queryCustomerByName(customer.getName());
        AssertUtil.isTrue(null != temp || temp.getId().equals(customer.getId()),"该客户已存在!");
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(customer)<1,"客户更新失败!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(Integer cid){

        Customer customer = selectByPrimaryKey(cid);
        /**
         * 如果客户被删除
         *     级联 客户联系人 客户交往记录 客户订单  被删除
         *
         * 如果客户被删除
         *     如果子表存在记录  不支持删除
         */
        AssertUtil.isTrue(null == cid || null == customer,"待删除记录不存在!");
        customer.setIsValid(0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(customer)<1,"客户删除失败!");
    }

}
