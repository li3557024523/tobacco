package com.xr.base.Aspect;


import com.alibaba.fastjson.JSON;
import com.xr.base.entity.SysLog;
import com.xr.base.service.SysLogService;
import com.xr.base.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.spring.web.json.Json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private SysLogService sysLogService;
    /**
     * ..表示包及子包 该方法代表controller层的所有方法  TODO 路径需要根据自己项目定义
     */
    @Pointcut("execution(public * com.xr.base.controller.*.*(..)) && !execution(* com.xr.base.controller.SysEmpController.logout(..))")
    public void controllerMethod() {
        log.info("切了");
    }


    /**
     * 方法执行前
     *
     * @param joinPoint
     * @throws Exception
     */
    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) throws Exception {
        SysLog sysLog = new SysLog();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuilder requestLog = new StringBuilder();
        Signature signature = joinPoint.getSignature();
        //TODO 获取当前访问用户因为结合了shiro所以直接从session取
//        Session session = SecurityUtils.getSubject().getSession();
//        SysStaff userInfo = (SysStaff) session.getAttribute(Constants.SESSION_USER_INFO);
//        sysLog.setSid(userInfo.getSid());
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
//        HttpServletRequest httpServletRequest = sra.getRequest();
        System.out.println("请求接口：" + request.getRequestURI());
        sysLog.setUrl(request.getRequestURI());
        log.info("请求方式：" + request.getMethod());
        sysLog.setOperation(request.getMethod());
        log.info("请求ip：" + IpUtil.getIpAddr(request) + "----------------" + request.getRemoteAddr());
        sysLog.setIp(IpUtil.getIpAddr(request)+""+request.getRemoteAddr());
        // 构造参数组集合
        List<Object> argList = new ArrayList<>();
        for (Object arg : joinPoint.getArgs()) {
            // request/response无法使用toJSON
            if (arg instanceof HttpServletRequest) {
                argList.add("request");
            } else if (arg instanceof HttpServletResponse) {
                argList.add("response");
            } else {
                argList.add(JSON.toJSON(arg));
            }
        }
        System.out.println("用户信息"+argList.toString());
        sysLog.setUsername( argList.toString());
        LiteDeviceResolver deviceResolver = new LiteDeviceResolver();
        Device device = deviceResolver.resolveDevice(request);
        if (device.isMobile()) {
            System.out.println("========请求来源设备是手机！========");
        } else if (device.isTablet()) {
            System.out.println("========请求来源设备是平板！========");
        } else if (device.isNormal()) {
            System.out.println("========请求来源设备是PC！========");
        } else {
            System.out.println("========请求来源设备是其它！========");
        }
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
        //系统名称
        String system = os.getName();
        log.info("系统名称："+system);
        //浏览器名称
        String browserName = browser.getName();
        log.info("浏览器名称："+browserName);
        sysLog.setBrowser(browserName);
        // 处理请求参数
//        String[] paramNames = ((MethodSignature) signature).getParameterNames();
//        Object[] paramValues = joinPoint.getArgs();
//        int paramLength = null == paramNames ? 0 : paramNames.length;
//        if (paramLength == 0) {
//            requestLog.append("请求参数 = {} ");
//        } else {
//            requestLog.append("请求参数 = [");
//            for (int i = 0; i < paramLength - 1; i++) {
//                requestLog.append(paramNames[i]).append("=").append(JSONObject.toJSONString(paramValues[i])).append(",");
//            }
//            requestLog.append(paramNames[paramLength - 1]).append("=").append(JSONObject.toJSONString(paramValues[paramLength - 1])).append("]");
//        }

        log.info(requestLog.toString());
        sysLogService.add(sysLog);
    }


    /**
     * 方法执行后
     *
     * @param resultVO
     * @throws Exception
     */
//    @AfterReturning(returning = "resultVO", pointcut = "controllerMethod()")
//    public void logResultVOInfo(ResultVO resultVO) throws Exception {
//        log.info("请求结果：" + resultVO.getCode() + "\t" + resultVO.getMsg());
//    }

}
