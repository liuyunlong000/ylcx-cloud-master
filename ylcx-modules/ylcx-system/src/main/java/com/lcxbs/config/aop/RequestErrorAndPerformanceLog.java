package com.lcxbs.config.aop;

import com.alibaba.fastjson.JSONObject;
import com.lcxbs.core.AbstractBaseObject;
import com.lcxbs.exception.CommonException;
import com.lcxbs.json.StringUtils;
import com.lcxbs.protocol.RespMsgBean;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.lcxbs.sys.model.SysCommonFilter;
import com.lcxbs.sys.service.SysCommonFilterService;
import com.lcxbs.utils.IllegalStrFilterUtil;
import com.lcxbs.utils.OSUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 全局controller异常统一处理以及调用接口打印性能日志
 */
@Component
@Aspect
public class RequestErrorAndPerformanceLog {

    public static final Logger logger = LoggerFactory.getLogger(RequestErrorAndPerformanceLog.class);

    @Resource
    private SysCommonFilterService sysCommonFilterService;

    @Pointcut("execution(* com.lcxbs.*.controller.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        RespMsgBean respMsgBean;
        //调用时间
        Long consumeTime=0L;
        //是否访问成功
        boolean invokeSuccess=false;
        //错误信息
        String errorMsg=null;
        try {
            //执行具体的调用方法
            Object obj = pjp.proceed(pjp.getArgs());
            if (obj instanceof RespMsgBean) {
                respMsgBean = (RespMsgBean) obj;
                invokeSuccess=true;
            } else {
                invokeSuccess=true;
                return obj;
            }
        } catch (CommonException e) {
            respMsgBean = new RespMsgBean().failure(e.getCode(), e.getMessage());
            invokeSuccess=false;
            errorMsg=e.getMessage();
        } catch (Exception e) {
            logger.error("",e);
            Map<String, String> map = new HashMap<>();
            map.put("",e.getMessage());
            respMsgBean = new RespMsgBean().failure(map);
            invokeSuccess=false;
            errorMsg=e.getMessage();
        }finally {
            //计算访问耗时
            consumeTime = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
            //记录访问日志
            recordLog(pjp, invokeSuccess, consumeTime,errorMsg);
        }
        return respMsgBean;
    }


    /**
     * 处理入参特殊字符和sql注入攻击
     */
    private void checkRequestParam(ProceedingJoinPoint pjp){
        String str = String.valueOf(pjp.getArgs());
        if (!IllegalStrFilterUtil.sqlStrFilter(str)) {
            logger.info("访问接口：" + pjp.getSignature() + "，输入参数存在SQL注入风险！参数为：" + Lists.newArrayList(pjp.getArgs()).toString());
        }
        if (!IllegalStrFilterUtil.isIllegalStr(str)) {
            logger.info("访问接口：" + pjp.getSignature() + ",输入参数含有非法字符!，参数为：" + Lists.newArrayList(pjp.getArgs()).toString());
        }
    }


    /**
     * 记录访问日志
     * @param pjp
     * @param requestStatus 是否请求成功
     * @param errorMsg 错误信息
     * @return
     */
    private boolean recordLog(ProceedingJoinPoint pjp,boolean requestStatus,Long consumeTime,String errorMsg){
        try {
            //调用的方法名
            String methodName = pjp.getSignature().getName();
            Class<?> classTarget = pjp.getTarget().getClass();
            Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
            Method objMethod = classTarget.getMethod(methodName, par);

            boolean needSaveLog=false;
            String moduleCode=null;
            String moduleName=null;
            Annotation[] annotations = objMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof PreAuthorize) {
                    PreAuthorize preAuthorize = (PreAuthorize) annotation;
                    String value = preAuthorize.value();
                    if (StringUtils.isNotBlank(value) && value.contains(".hasAuthority")) {
                        String recordLog = getPatternValue(value, "(\\,)(.*?)(\\,)");//取两个逗号之间
                        //判断是否需要保存日志
                        if (StringUtils.isBlank(recordLog) || recordLog.equals("false")) {
                            return false;
                        }
                        //需要保存日志
                        needSaveLog=true;
                        moduleCode = getPatternValue(value, "(\\')(.*?)(\\')");//取两个单引号之间
                    }
                } else if (annotation instanceof ApiOperation) {
                    ApiOperation apiOperation = (ApiOperation) annotation;
                    moduleName = apiOperation.value();
                }
            }
            if(needSaveLog){
                RequestAttributes ra = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes sra = (ServletRequestAttributes) ra;
                HttpServletRequest request = sra.getRequest();
                SysCommonFilter commonFilter=new SysCommonFilter();
                commonFilter.setModuleName(moduleName);
                commonFilter.setModuleCode(moduleCode);
                commonFilter.setRequestMethod(methodName);
                commonFilter.setRequestStatus(requestStatus?1L:0L);
                commonFilter.setConsumeTime(consumeTime);
                commonFilter.setErrorMsg(errorMsg);
                commonFilter.setCallUrl(request.getRequestURI());
                commonFilter.setLogIp(OSUtil.getIpAddr(request));
                String contentType=request.getContentType();
                if(contentType!=null&&contentType.contains("application/json")) {
                    StringBuilder sbValue=new StringBuilder();
                    Map<String,Object> map=getNameAndValue(pjp);
                    map.keySet().forEach(key->{
                        if(!key.equalsIgnoreCase("request")&&!key.equalsIgnoreCase("response")){
                            Object objValue=map.get(key);
                            String paramValue=JSONObject.toJSONString(objValue);
                            sbValue.append(paramValue);
                        }
                    });
                    commonFilter.setCallParams(sbValue.toString());
                }else {
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    commonFilter.setCallParams(JSONObject.toJSONString(parameterMap));
                }
                this.sysCommonFilterService.insert(commonFilter);
                return true;
            }
        }catch (Exception ex){
            logger.error("记录日志失败",ex);
        }
        return false;
    }

    /**
     * 通过正则获取数值
     * @param value 源数值
     * @param pattern 正则
     * @return
     */
    private static String getPatternValue(String value,String pattern) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        while (m.find()) {
            return m.group(2);
        }
        return null;
    }

    /**
     * 获取参数Map集合
     * @param joinPoint
     * @return
     */
    Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }
}