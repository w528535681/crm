package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.utils.*;
import com.shsxt.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service
@SuppressWarnings("all")
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
        return new UserModel(UserIDBase64.encoderUserID(user.getId()),user.getUserName(),user.getTrueName());
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

    /**
     * 用户更新密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserPassword(Integer userId,String oldPassword,String newPassword,String confirmPassword){
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
        checkParams(userId,oldPassword,newPassword,confirmPassword);

        User user = userMapper.selectByPrimaryKey(userId);
        user.setUserPwd(Md5Util.encode(newPassword));
        AssertUtil.isTrue(updateByPrimaryKeySelective(user)<1,"密码更新失败！");
    }

    /**
     * 用户更新密码,非空校验
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkParams(Integer userId, String oldPassword, String newPassword, String confirmPassword) {

        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(userId==null||user==null,"用户未登录或不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"请输入原始密码！");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"请输入新密码！");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"请输入确认密码！");
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)),"新密码输入不一致!");
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(oldPassword))),"原始密码不正确！");
        AssertUtil.isTrue(user.getUserPwd().equals(Md5Util.encode(newPassword)),"新密码不能与旧密码相同！");

    }

    public void saveUser(User user){
        /**
         * 1.参数校验
         *     用户名  非空   唯一
         *     email  非空  格式合法
         *     手机号 非空  格式合法
         * 2.设置默认参数
         *      isValid 1
         *      createDate   uddateDate
         *      userPwd   123456->md5加密
         * 3.执行添加  判断结果
         */
        checkParams(user.getUserName(),user.getEmail(),user.getPhone());
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        AssertUtil.isTrue(insertSelective(user)<1,"用户添加失败!");
    }

    /**
     * 校验添加用户的参数
     * @param id
     * @param email
     * @param phone
     */
    private void checkParams(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(email),"请输入邮箱地址!");
        AssertUtil.isTrue(!(EmailUtil.isEmail(email)),"请输入有效的邮箱地址!");
        AssertUtil.isTrue(StringUtils.isBlank(phone),"请输入手机号码!");
        AssertUtil.isTrue(!(PhoneUtil.isMobile(phone)),"请输入有效的手机号码!");
    }

    public void updateUser(User user){
        /**
         * 1.参数校验
         *     id 非空  记录必须存在
         *     用户名  非空   唯一
         *     email  非空  格式合法
         *     手机号 非空  格式合法
         * 2.设置默认参数
         *        uddateDate
         * 3.执行更新  判断结果
         */
        AssertUtil.isTrue(user.getId()==null||null==selectByPrimaryKey(user.getId()),"待更新记录不存在!");
        checkParams(user.getUserName(),user.getEmail(),user.getPhone());
        User temp = userMapper.queryUserByUserName(user.getUserName());
        if (null != temp && temp.getIsValid()==1){
            AssertUtil.isTrue(!(user.getId().equals(temp.getId())),"该用户已存在!");
        }
        user.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(user)<1,"用户更新失败!");
    }

    public void deleteUser(Integer userId){
        User user = selectByPrimaryKey(userId);
        AssertUtil.isTrue(user==null||userId==null,"待删除记录不存在!");
        user.setIsValid(0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(user)<1,"用户记录删除失败!");
    }
}
