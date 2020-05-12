package com.shsxt.proxy;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.shsxt.crm.annotaions.CrmLog;
import com.shsxt.crm.dao.LogMapper;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.LogWithBLOBs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LogProxy {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Around(value = "@annotation(com.shsxt.crm.annotaions.CrmLog)")
    public Object  aroundProceedingJoinPoint(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        LogWithBLOBs log = new LogWithBLOBs();
        log.setCreateDate(new Date());
        log.setCreateMan(userService.selectByPrimaryKey(LoginUserUtil.releaseUserIdFromCookie(request)).getTrueName());
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        CrmLog crmLog = methodSignature.getMethod().getDeclaredAnnotation(CrmLog.class);
        log.setDescription(crmLog.module()+"-"+crmLog.oper());
        log.setExceptionCode("200");
        log.setExceptionDetail("操作成功");
        log.setMethod(methodSignature.toString());
        Long start = System.currentTimeMillis();
        result = pjp.proceed();
        Long end = System.currentTimeMillis();
        log.setExecuteTime((int) (end-start));
        log.setType("1");
        log.setRequestIp(request.getRemoteAddr());

        if (null!=pjp.getArgs()){
            log.setParams(new Gson().toJson(pjp.getArgs()));
        }
        log.setResult(JSON.toJSONString(result));
        logMapper.insertSelective(log);
        return result;
    }
}
