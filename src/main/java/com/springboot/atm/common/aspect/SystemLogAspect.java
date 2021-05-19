package com.springboot.atm.common.aspect;

import com.google.gson.Gson;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.springboot.atm.model.SysLog;
import com.springboot.atm.repository.SysLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class SystemLogAspect {
    @Autowired
    private SysLogRepository sysLogRepository;
    @After(value = "execution(public * com.springboot.atm.service.*.*(..))")
    public void saveLog(JoinPoint joinPoint) {
        //log object
        SysLog sysLog = new SysLog();
        sysLog.setOptime(new Date());
        //get target method rute
        String name = joinPoint.getTarget().getClass().getName();
        //get method name
        String signature = joinPoint.getSignature().getName();
        String operation = name + ":" + signature;
        sysLog.setOperation(operation);
        //get method arguments
        Object[] args = joinPoint.getArgs();
        Gson gson = new Gson();
        StringBuffer sb = new StringBuffer();
        boolean flag = true;
        for (Object arg : args) {
            if (flag) {
                sysLog.setCardId(gson.toJson(arg));
                flag = false;
            }
           sb.append(gson.toJson(arg));
        }
        sysLog.setParams(sb.toString());
        //save in database
        sysLogRepository.save(sysLog);
    }
}
