package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService extends BaseService<User,Integer> {

    @Autowired
    private UserMapper userMapper;

    /**
     * 1.参数校验
     *    用户名  非空
     *    密码  非空
     * 2.根据用户名  查询用户记录
     * 3.校验用户存在性
     *     不存在  -->记录不存在 方法结束
     * 4.用户存在
     *     校验密码
     *       密码错误-->密码不正确  方法结束
     * 5.密码正确
     *     用户登录成功  返回用户相关信息
     */

    public UserModel login(String userName, String userPwd){

        //参数非空校验
        checkLoginParams(userName,userPwd);

        //参数不为空，根绝userName查询User对象
        User user = userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(null==user,"用户已注销或不存在!");
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(userPwd))),"密码错误");

        return buildUserModelInfo(user);
    }

    /**
     *构建user对象
     * @param user
     * @return
     */
    private UserModel buildUserModelInfo(User user) {
        return new UserModel(user.getId(),user.getUserName(),user.getUserPwd());
    }

    /**
     * 登录参数的非空校验
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {

        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空!");
    }
}
