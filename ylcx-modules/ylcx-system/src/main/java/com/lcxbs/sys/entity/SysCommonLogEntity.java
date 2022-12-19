package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysCommonLogEntity.java</p>
 * <p>Description:登录日志实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 14:32:27
 * @version V1.0
 */
public class SysCommonLogEntity extends AbstractBaseObject{

    /** 自增ID; */
    private Long nid;
    /** 登录用户名 */
    private String userLogin;
    /** IP地址 */
    private String ipAddress;
    /** 登录位置 */
    private String loginLocation;
    /** 浏览器 */
    private String browser;
    /** 操作系统 */
    private String osType;
    /** 登录状态;字典：1登录成功，0登录失败 */
    private Long loginState;
    /** 操作信息 */
    private String logMsg;
    /** 登录平台 */
    private String platform;
    /** 登录时间 */
    private Long loginTime;
    /** 退出时间 */
    private Long logoutTime;
    /** 在线时长 */
    private Long onlineDuration;
    /** 会话ID */
    private String sessionId;
    /** 租户号 */
    private String tenantId;

    /**
     *无参构造函数
     */
    public SysCommonLogEntity(){
        super();
    }

    /**
     * 带主键构造函数
     * @param id
     */
    public SysCommonLogEntity(Long id){
        super();
        this.nid=id;
    }

    /**
     * 获取自增ID;
     * @return
     */
    public Long getNid(){
        return this.nid;
    }

    /**
     * 设置自增ID;
     * @param nid
     */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
     * 获取登录用户名
     * @return
     */
    public String getUserLogin(){
        return this.userLogin;
    }

    /**
     * 设置登录用户名
     * @param userLogin
     */
    public void setUserLogin(String userLogin){
        this.userLogin=(userLogin == null ? null : userLogin.trim());
    }
    /**
     * 获取IP地址
     * @return
     */
    public String getIpAddress(){
        return this.ipAddress;
    }

    /**
     * 设置IP地址
     * @param ipAddress
     */
    public void setIpAddress(String ipAddress){
        this.ipAddress=(ipAddress == null ? null : ipAddress.trim());
    }
    /**
     * 获取登录位置
     * @return
     */
    public String getLoginLocation(){
        return this.loginLocation;
    }

    /**
     * 设置登录位置
     * @param loginLocation
     */
    public void setLoginLocation(String loginLocation){
        this.loginLocation=(loginLocation == null ? null : loginLocation.trim());
    }
    /**
     * 获取浏览器
     * @return
     */
    public String getBrowser(){
        return this.browser;
    }

    /**
     * 设置浏览器
     * @param browser
     */
    public void setBrowser(String browser){
        this.browser=(browser == null ? null : browser.trim());
    }
    /**
     * 获取操作系统
     * @return
     */
    public String getOsType(){
        return this.osType;
    }

    /**
     * 设置操作系统
     * @param osType
     */
    public void setOsType(String osType){
        this.osType=(osType == null ? null : osType.trim());
    }
    /**
     * 获取登录状态;字典：1登录成功，0登录失败
     * @return
     */
    public Long getLoginState(){
        return this.loginState;
    }

    /**
     * 设置登录状态;字典：1登录成功，0登录失败
     * @param loginState
     */
    public void setLoginState(Long loginState){
        this.loginState=loginState;
    }
    /**
     * 获取操作信息
     * @return
     */
    public String getLogMsg(){
        return this.logMsg;
    }

    /**
     * 设置操作信息
     * @param logMsg
     */
    public void setLogMsg(String logMsg){
        this.logMsg=(logMsg == null ? null : logMsg.trim());
    }
    /**
     * 获取登录平台
     * @return
     */
    public String getPlatform(){
        return this.platform;
    }

    /**
     * 设置登录平台
     * @param platform
     */
    public void setPlatform(String platform){
        this.platform=(platform == null ? null : platform.trim());
    }
    /**
     * 获取登录时间
     * @return
     */
    public Long getLoginTime(){
        return this.loginTime;
    }

    /**
     * 设置登录时间
     * @param loginTime
     */
    public void setLoginTime(Long loginTime){
        this.loginTime=loginTime;
    }
    /**
     * 获取退出时间
     * @return
     */
    public Long getLogoutTime(){
        return this.logoutTime;
    }

    /**
     * 设置退出时间
     * @param logoutTime
     */
    public void setLogoutTime(Long logoutTime){
        this.logoutTime=logoutTime;
    }
    /**
     * 获取在线时长
     * @return
     */
    public Long getOnlineDuration(){
        return this.onlineDuration;
    }

    /**
     * 设置在线时长
     * @param onlineDuration
     */
    public void setOnlineDuration(Long onlineDuration){
        this.onlineDuration=onlineDuration;
    }
    /**
     * 获取会话ID
     * @return
     */
    public String getSessionId(){
        return this.sessionId;
    }

    /**
     * 设置会话ID
     * @param sessionId
     */
    public void setSessionId(String sessionId){
        this.sessionId=(sessionId == null ? null : sessionId.trim());
    }
    /**
     * 获取租户号
     * @return
     */
    public String getTenantId(){
        return this.tenantId;
    }

    /**
     * 设置租户号
     * @param tenantId
     */
    public void setTenantId(String tenantId){
        this.tenantId=(tenantId == null ? null : tenantId.trim());
    }

}
