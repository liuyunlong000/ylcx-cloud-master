package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysCommonFilterEntity.java</p>
 * <p>Description:操作日志实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-8 22:28:11
 * @version V1.0
 */
public class SysCommonFilterEntity extends AbstractBaseObject{

    /** 主键 */
    private Long nid;
    /** 系统模块 */
    private String moduleName;
    /** 模块编码 */
    private String moduleCode;
    /** 请求方法 */
    private String requestMethod;
    /** 操作状态 */
    private Long requestStatus;
    /** 消耗时间 */
    private Long consumeTime;
    /** 错误信息 */
    private String errorMsg;
    /** 被操作信息ID */
    private String infoId;
    /** 操作IP */
    private String logIp;
    /** 请求地址 */
    private String callUrl;
    /** 请求参数信息 */
    private String callParams;
    /** 操作人 */
    private String createdBy;
    /** 操作时间 */
    private Long createdTime;
    /** 租户号 */
    private String tenantId;

    /**
     *无参构造函数
     */
    public SysCommonFilterEntity(){
        super();
    }

    /**
     * 带主键构造函数
     * @param id
     */
    public SysCommonFilterEntity(Long id){
        super();
        this.nid=id;
    }

    /**
     * 获取主键
     * @return
     */
    public Long getNid(){
        return this.nid;
    }

    /**
     * 设置主键
     * @param nid
     */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
     * 获取系统模块
     * @return
     */
    public String getModuleName(){
        return this.moduleName;
    }

    /**
     * 设置系统模块
     * @param moduleName
     */
    public void setModuleName(String moduleName){
        this.moduleName=(moduleName == null ? null : moduleName.trim());
    }
    /**
     * 获取模块编码
     * @return
     */
    public String getModuleCode(){
        return this.moduleCode;
    }

    /**
     * 设置模块编码
     * @param moduleCode
     */
    public void setModuleCode(String moduleCode){
        this.moduleCode=(moduleCode == null ? null : moduleCode.trim());
    }
    /**
     * 获取请求方法
     * @return
     */
    public String getRequestMethod(){
        return this.requestMethod;
    }

    /**
     * 设置请求方法
     * @param requestMethod
     */
    public void setRequestMethod(String requestMethod){
        this.requestMethod=(requestMethod == null ? null : requestMethod.trim());
    }
    /**
     * 获取操作状态
     * @return
     */
    public Long getRequestStatus(){
        return this.requestStatus;
    }

    /**
     * 设置操作状态
     * @param requestStatus
     */
    public void setRequestStatus(Long requestStatus){
        this.requestStatus=requestStatus;
    }
    /**
     * 获取消耗时间
     * @return
     */
    public Long getConsumeTime(){
        return this.consumeTime;
    }

    /**
     * 设置消耗时间
     * @param consumeTime
     */
    public void setConsumeTime(Long consumeTime){
        this.consumeTime=consumeTime;
    }
    /**
     * 获取错误信息
     * @return
     */
    public String getErrorMsg(){
        return this.errorMsg;
    }

    /**
     * 设置错误信息
     * @param errorMsg
     */
    public void setErrorMsg(String errorMsg){
        this.errorMsg=(errorMsg == null ? null : errorMsg.trim());
    }
    /**
     * 获取被操作信息ID
     * @return
     */
    public String getInfoId(){
        return this.infoId;
    }

    /**
     * 设置被操作信息ID
     * @param infoId
     */
    public void setInfoId(String infoId){
        this.infoId=(infoId == null ? null : infoId.trim());
    }
    /**
     * 获取操作IP
     * @return
     */
    public String getLogIp(){
        return this.logIp;
    }

    /**
     * 设置操作IP
     * @param logIp
     */
    public void setLogIp(String logIp){
        this.logIp=(logIp == null ? null : logIp.trim());
    }
    /**
     * 获取请求地址
     * @return
     */
    public String getCallUrl(){
        return this.callUrl;
    }

    /**
     * 设置请求地址
     * @param callUrl
     */
    public void setCallUrl(String callUrl){
        this.callUrl=(callUrl == null ? null : callUrl.trim());
    }
    /**
     * 获取请求参数信息
     * @return
     */
    public String getCallParams(){
        return this.callParams;
    }

    /**
     * 设置请求参数信息
     * @param callParams
     */
    public void setCallParams(String callParams){
        this.callParams=(callParams == null ? null : callParams.trim());
    }
    /**
     * 获取操作人
     * @return
     */
    public String getCreatedBy(){
        return this.createdBy;
    }

    /**
     * 设置操作人
     * @param createdBy
     */
    public void setCreatedBy(String createdBy){
        this.createdBy=(createdBy == null ? null : createdBy.trim());
    }
    /**
     * 获取操作时间
     * @return
     */
    public Long getCreatedTime(){
        return this.createdTime;
    }

    /**
     * 设置操作时间
     * @param createdTime
     */
    public void setCreatedTime(Long createdTime){
        this.createdTime=createdTime;
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
