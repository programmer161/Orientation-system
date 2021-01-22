package com.Digital_Orientation_System.log;


import com.Digital_Orientation_System.dao.ExcepMyLogMapper;
import com.Digital_Orientation_System.dao.MyLogMapper;
import com.Digital_Orientation_System.dao.SysUserMapper;
import com.Digital_Orientation_System.entity.AdminLog;
import com.Digital_Orientation_System.entity.ExcepMyLog;
import com.alibaba.fastjson.JSON;
import com.Digital_Orientation_System.components.IPUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/** 系统日志：切面处理类 */
@Aspect
@Component
public class SysLogAspect {
    /**我这里是使用log4j2把一些信息打印在控制台上面，可以不写 */
    private static final Logger log = LogManager.getLogger(SysLogAspect.class);

    /**操作数据库 */
    @Autowired
    private MyLogMapper myLogMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    UserDetails userDetails;
    @Autowired
    private ExcepMyLogMapper excepMyLogMapper;




    /**
     * 设置操作正常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("@annotation(com.Digital_Orientation_System.log.MyLog)")
    public void logPoinCut() {
    }
    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
     @Pointcut("execution(* com.Digital_Orientation_System.controller..*.*(..))")
      public void exceptionLogPoinCut() {

     }

    //切面 配置通知
    //正常操作
    @AfterReturning(value = "logPoinCut()",returning = "keys")         //AfterReturning
    public void saveOperation(JoinPoint joinPoint,Object keys) {
        log.info("---------------接口日志记录---------------");
        //用于保存日志

        AdminLog adminLog = new AdminLog();
        try {//从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作--方法上的Log的值
            MyLog myLog = method.getAnnotation(MyLog.class);
            if (myLog != null) {
                //保存操作事件
                String operation = myLog.operation();
                adminLog.setOperation(operation);

                //保存日志类型
//            int type = myLog.type();
//            adminLog.setType(type);

            log.info("operation="+operation);
            }

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestURL = request.getRequestURL().toString();
            adminLog.setUrl(requestURL);
            // 客户端ip
//        String ip = request.getRemoteAddr();
            String ip = IPUtils.getCliectIp(request);
            adminLog.setLoginip(ip);
            log.info("url=" + requestURL, "ip=" + ip);
            //请求方法
            String methodName = joinPoint.getTarget().getClass().getName() + "," + method.getName();
            adminLog.setMethodName(methodName);

//      获取用户的信息  用户名 用户ID name
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userId = sysUserMapper.getUserIdByUserName(userDetails.getUsername());
            String name = sysUserMapper.getNameByUserName(userDetails.getUsername());
            adminLog.setUserName(userDetails.getUsername());
            adminLog.setUserId(userId);
            adminLog.setName(name);
//            log.info("userDetails=" + userDetails);
            //调用service保存Operation实体类到数据库
            //我id使用的是UUID，不需要的可以注释掉
//        String id = UUIDUtil.getUUID();
//        adminLog.setId();
            adminLog.setRemark(null);
            Object[] args = joinPoint.getArgs();
            String params = JSON.toJSONString(args);
            adminLog.setParams(params);
            adminLog.setResult(JSON.toJSONString(keys));//返回值
            myLogMapper.insert(adminLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//异常
    @AfterThrowing(value = "exceptionLogPoinCut()",throwing = "e")         //AfterReturning
    public void saveExceptionOperation(JoinPoint joinPoint,Throwable e) {
     // 获取RequestAttributes
         RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
         // 从获取RequestAttributes中获取HttpServletRequest的信息
         HttpServletRequest request = (HttpServletRequest) requestAttributes
                 .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        ExcepMyLog excepLog = new ExcepMyLog();
        try {

             // 从切面织入点处通过反射机制获取织入点处的方法
             MethodSignature signature = (MethodSignature) joinPoint.getSignature();
             // 获取切入点所在的方法
             Method method = signature.getMethod();
             // 获取请求的类名
            MyLog myLog = method.getAnnotation(MyLog.class);
            if (myLog != null) {
                //保存操作事件
                String operation = myLog.operation();
                excepLog.setOperation(operation);

                //保存日志类型
//            int type = myLog.type();
//            adminLog.setType(type);

//            log.info("operation="+operation+",type="+type);
            }

            String className = joinPoint.getTarget().getClass().getName();
             // 获取请求的方法名
            String methodName = method.getName();
             methodName = className + "." + methodName;
            // 请求的参数
             Map<String, String> rtnMap = converMap(request.getParameterMap());
             // 将参数所在的数组转换成json
             String params = JSON.toJSONString(rtnMap);
             excepLog.setParams(params); // 请求参数
             excepLog.setMethodName(methodName); // 请求方法名
            excepLog.setEname(e.getClass().getName()); // 异常名称
             excepLog.setEmessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
//                获取用户的信息  用户名 用户ID name
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userId = sysUserMapper.getUserIdByUserName(userDetails.getUsername());
            String name1 = sysUserMapper.getNameByUserName(userDetails.getUsername());
             excepLog.setUserId(userId); // 操作员ID
             excepLog.setUserName(userDetails.getUsername()); // 操作员名称
            excepLog.setName(name1);
             excepLog.setUrl(request.getRequestURI()); // 操作URI
             excepLog.setLoginip(IPUtils.getCliectIp(request)); // 操作员Ip
             excepMyLogMapper.insert(excepLog);
        }catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
     public Map<String, String> converMap(Map<String, String[]> paramMap) {
                 Map<String, String> rtnMap = new HashMap<String, String>();
                 for (String key : paramMap.keySet()) {
                         rtnMap.put(key, paramMap.get(key)[0]);
                     }
                 return rtnMap;
             }


    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    private String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
                 for (StackTraceElement stet : elements) {
                         strbuff.append(stet + "\n");
                     }
                 String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
                 return message;
    }
}

