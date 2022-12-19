package com.lcxbs.auth.controller;

import com.lcxbs.auth2.enums.AuthErrorEnum;
import com.lcxbs.auth2.exception.PasswordBlankException;
import com.lcxbs.auth2.exception.PasswordErrorException;
import com.lcxbs.auth2.exception.UserNameBlankException;
import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespCodeEnum;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysCommonLog;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.sys.service.SysCommonLogService;
import com.lcxbs.sys.service.SysUserService;
import com.lcxbs.utils.OSUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
@Api(value = "/oauth", tags = "认证、回收Token、Token验证")
public class OAuthController extends BaseController{

    private Logger logger= LoggerFactory.getLogger(OAuthController.class);
    @Resource
    private TokenEndpoint tokenEndpoint;
    @Resource
    private DefaultTokenServices defaultTokenServices;
    @Resource
    private ResourceServerTokenServices resourceServerTokenServices;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysCommonLogService sysCommonLogService;

    @Resource
    RedisTemplate redisTemplate;


    private AccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();



    /**
     * 退出系统，清楚会话
     * @return
     */
    @GetMapping("/revoke")
    @ApiOperation("回收Token")
    public RespMsgBean revokeToken(HttpServletRequest request) {
        String access_token=this.extractToken(request);
        try {
            OAuth2AccessToken token = resourceServerTokenServices.readAccessToken(access_token);
            if (token != null) {
                this.insertLogoutLog(token.getValue(),request);
            }
        }catch (Exception ex){
            logger.error("",ex);
        }
        boolean result=defaultTokenServices.revokeToken(access_token);
        if(result) {
            return new RespMsgBean().success();
        }else {
            return new RespMsgBean().failure();
        }
    }

    /**
     * 如果其他资源服务器需要验证Token 则需要远程调用授权服务暴露的验证Token的API接口
     * @param principal
     * @return
     */
    @GetMapping("/current")
    @ApiOperation("获取当前用户信息")
    public RespMsgBean getCurrentUser(Principal principal) {
        SysUser sysUser = this.sysUserService.getByUserLogin(principal.getName());
        if (sysUser != null) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("userId", sysUser.getUserId());
            map.put("userLogin", sysUser.getUserLogin());
            map.put("userName", sysUser.getUserName());
            map.put("userEmail", sysUser.getUserEmail());
            map.put("userPhone", sysUser.getUserPhone() + "");
            return new RespMsgBean().success(map);
        }
        return new RespMsgBean().success();
    }


    @GetMapping("/token")
    @ApiOperation("认证")
    public RespMsgBean getAccessToken(Principal principal, @RequestParam Map<String, String> parameters,HttpServletRequest request) {
        return custom(principal, parameters,request);
    }

    @PostMapping("/token")
    @ApiOperation("认证")
    public RespMsgBean postAccessToken(Principal principal, @RequestParam Map<String, String> parameters,HttpServletRequest request) {
        return custom(principal, parameters,request);
    }

    @GetMapping("/check_token")
    @ApiOperation("Token验证")
    public Map<String, ?> getCheckToken(@RequestParam("token") String value)  {
        return custom(value);
    }

    @PostMapping("/check_token")
    @ApiOperation("Token验证")
    public Map<String, ?> postCheckToken(@RequestParam("token") String value) {
        return custom(value);
    }

    /**
     * 自定义处理check_toke
     * @param value
     * @return
     */
    private Map<String, ?> custom(String value){
        Map<String, Object> response=new HashMap<>();
        int code= RespCodeEnum.SUCCESS.getCode();
        String msg=RespCodeEnum.SUCCESS.getName();
        try {
            OAuth2AccessToken token = resourceServerTokenServices.readAccessToken(value);
            if (token == null) {
                throw new InvalidTokenException("Token was not recognised");
            }
            if (token.isExpired()) {
                throw new InvalidTokenException("Token has expired");
            }
            OAuth2Authentication authentication = resourceServerTokenServices.loadAuthentication(token.getValue());
            response = (Map<String, Object>) accessTokenConverter.convertAccessToken(token, authentication);
        }catch (InvalidTokenException ex){
            if(ex.getMessage().equals("Token was not recognised")) {
                code = AuthErrorEnum.TOKEN_INVALID.getCode();
                msg = AuthErrorEnum.TOKEN_INVALID.getName();
            }else  if(ex.getMessage().equals("Token has expired")){
                code = AuthErrorEnum.TOKEN_EXPIRED.getCode();
                msg = AuthErrorEnum.TOKEN_EXPIRED.getName();
            }else{
                code = AuthErrorEnum.TOKEN_INVALID.getCode();
                msg = AuthErrorEnum.TOKEN_INVALID.getName();
            }
        }catch (Exception ex){
            code= RespCodeEnum.SYSTEM_ERROR.getCode();
            msg=RespCodeEnum.SYSTEM_ERROR.getName();
            logger.error("",ex);
        }
        return response;
    }



    /**
     * 定制申请返回实体
     * @return
     */
    private RespMsgBean custom(Principal principal,Map<String, String> parameters,HttpServletRequest request) {
        OAuth2AccessToken accessToken=null;
        int code= RespCodeEnum.SUCCESS.getCode();
        String msg=RespCodeEnum.SUCCESS.getName();
        String username=parameters.get("username");
        try {
//            String password = parameters.get("password");
//            if(Objects.isNull(password)){
//                throw new PasswordBlankException("密码为空！");
//            } else if(!(password.length() % RSAUtils.RSAConstant.ENCRYPTION_LENGTH == 0)){
//                throw  new PasswordErrorException("密码错误！");
//            }
//            //TODO:前端传过来的密文+号被替换成空格
//            password=password.replaceAll(" ","+");
//            //构建密钥工具
//            RSAUtils rsaUtils = RSAUtils.getInstance(redisTemplate);
//            //对密文解密
//            String privateKey = rsaUtils.getPrivateKey();
//            password = rsaUtils.decrypt(password, rsaUtils.getPrivateKey(privateKey));
//            parameters.put("password",password);
            accessToken = tokenEndpoint.getAccessToken(principal, parameters).getBody();
            msg="登录成功！";
            this.insertLoginLog(username,1L,accessToken.getValue(),request);
        }catch (UnsupportedGrantTypeException ex){//授权类型不支持
            code= AuthErrorEnum.UNSUPPORTED_GRANT_TYPE.getCode();
            msg=AuthErrorEnum.UNSUPPORTED_GRANT_TYPE.getName();
        }catch (UserNameBlankException ex){//用户名为空
            code= AuthErrorEnum.USERNAME_BLANK.getCode();
            msg=AuthErrorEnum.USERNAME_BLANK.getName();
        }catch (PasswordBlankException ex){//密码为空
            code= AuthErrorEnum.PASSWORD_BLANK.getCode();
            msg=AuthErrorEnum.PASSWORD_BLANK.getName();
        }catch (UsernameNotFoundException ex){//用户不存在
            code= AuthErrorEnum.USERNAME_NOT_FOUND.getCode();
            msg=AuthErrorEnum.USERNAME_NOT_FOUND.getName();
        }catch (PasswordErrorException ex){//密码错误
            code= AuthErrorEnum.ACCOUNT_PASSWORD_ERROR.getCode();
            msg=AuthErrorEnum.ACCOUNT_PASSWORD_ERROR.getName();
            this.insertLoginLog(username,0L,"",request);
        }catch (InvalidRequestException ex){//无效的请求
            code= AuthErrorEnum.INVALID_REQUEST.getCode();
            msg=AuthErrorEnum.INVALID_REQUEST.getName();
        }catch (InvalidGrantException ex){
            code= RespCodeEnum.FAILURE.getCode();
            msg=RespCodeEnum.FAILURE.getName();
        }catch (Exception ex){
            code= RespCodeEnum.SYSTEM_ERROR.getCode();
            msg=RespCodeEnum.SYSTEM_ERROR.getName();
            logger.error("",ex);
        }
        return new RespMsgBean().success(code,msg, accessToken);
    }

    /**
     * 记录登录日志
     * @param userLogin 登录名
     * @param loginState 登录状态（1登录成功；0登录失败）
     * @param sessionId 会话ID
     * @param request
     */
    private void insertLoginLog(String userLogin, Long loginState, String sessionId, HttpServletRequest request) {
        try {
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            //获取浏览器对象
            Browser browser = userAgent.getBrowser();
            //获取操作系统对象
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            String ipAddress = OSUtil.getIpAddr(request);
            SysCommonLog commonLog = new SysCommonLog();
            commonLog.setUserLogin(userLogin);
            commonLog.setLoginState(loginState);
            commonLog.setLoginTime(System.currentTimeMillis());
            commonLog.setIpAddress(ipAddress);
            commonLog.setOsType(operatingSystem.getName());
            commonLog.setBrowser(browser.getGroup().toString().toLowerCase() + " " + userAgent.getBrowserVersion());
            commonLog.setPlatform(operatingSystem.getDeviceType().getName());
            commonLog.setSessionId(sessionId);
            sysCommonLogService.insert(commonLog);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * 记录退出日志
     * @param sessionId 会话ID
     * @param request
     */
    private void insertLogoutLog(String sessionId, HttpServletRequest request) {
        try {
            SysCommonLog commonLog = new SysCommonLog();
            commonLog.setSessionId(sessionId);
            commonLog.setLogoutTime(System.currentTimeMillis());
            sysCommonLogService.updateBySessionId(commonLog);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

}
