package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysUserEntity.java</p>
 * <p>Description:用户信息实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:43
 * @version V1.0
 */
public class SysUserEntity extends AbstractBaseObject{

	/** 自增ID */
    private Long nid;
	/** 用户ID */
    private String userId;
	/** 真实姓名 */
    private String userName;
	/** 用户登录名 */
    private String userLogin;
	/** 密码 */
    private String userPassword;
	/** 手机号码 */
    private Long userPhone;
	/** 用户类型;字典：1系统，2老师，3家长 */
    private Long userType;
	/** 用户类型关联ID */
    private String userRelId;
    /** 证件类型 */
    private Long certificateType;
    /** 证件号码 */
    private String certificateId;
    /** 所属部门ID */
    private String deptId;
	/** 邮箱 */
    private String userEmail;
	/** 性别 */
    private String userGender;
	/** 头像 */
    private String avatar;
	/** 主要岗位 */
    private String mainPost;
	/** 政治面貌 */
    private String politicsStatus;
	/** 学历 */
    private String qualifications;
	/** 职称 */
    private String professionalTitle;
	/** 工作类型 */
    private String workType;
	/** 工作职责 */
    private String workDuty;
	/** 排序 */
    private Long sortNum;
	/** 是否启用标识;字典：1启用，0禁用 */
    private Long disableFlag;
	/** 是否删除标识;字典：1删除，0未删除 */
    private Long deleteFlag;
	/** 乐观锁 */
    private String revision;
	/** 创建人 */
    private String createdBy;
	/** 创建时间 */
    private Long createdTime;
	/** 更新人 */
    private String updatedBy;
	/** 更新时间 */
    private Long updatedTime;
	/** 租户号 */
    private String tenantId;

    /**
    *无参构造函数
    */
    public SysUserEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysUserEntity(Long id){
        super();
        this.nid=id;
    }

    /**
    * 获取自增ID
    * @return 
    */
    public Long getNid(){
        return this.nid;
    }
    
    /**
    * 设置自增ID
    * @param nid
    */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
    * 获取用户ID
    * @return 
    */
    public String getUserId(){
        return this.userId;
    }
    
    /**
    * 设置用户ID
    * @param userId
    */
    public void setUserId(String userId){
        this.userId=(userId == null ? null : userId.trim());
    }
    /**
    * 获取真实姓名
    * @return 
    */
    public String getUserName(){
        return this.userName;
    }
    
    /**
    * 设置真实姓名
    * @param userName
    */
    public void setUserName(String userName){
        this.userName=(userName == null ? null : userName.trim());
    }
    /**
    * 获取用户登录名
    * @return 
    */
    public String getUserLogin(){
        return this.userLogin;
    }
    
    /**
    * 设置用户登录名
    * @param userLogin
    */
    public void setUserLogin(String userLogin){
        this.userLogin=(userLogin == null ? null : userLogin.trim());
    }
    /**
    * 获取密码
    * @return 
    */
    public String getUserPassword(){
        return this.userPassword;
    }
    
    /**
    * 设置密码
    * @param userPassword
    */
    public void setUserPassword(String userPassword){
        this.userPassword=(userPassword == null ? null : userPassword.trim());
    }
    /**
    * 获取手机号码
    * @return 
    */
    public Long getUserPhone(){
        return this.userPhone;
    }
    
    /**
    * 设置手机号码
    * @param userPhone
    */
    public void setUserPhone(Long userPhone){
        this.userPhone=userPhone;
    }
    /**
    * 获取用户类型;字典：1系统，2老师，3家长
    * @return 
    */
    public Long getUserType(){
        return this.userType;
    }
    
    /**
    * 设置用户类型;字典：1系统，2老师，3家长
    * @param userType
    */
    public void setUserType(Long userType){
        this.userType=userType;
    }
    /**
    * 获取用户类型关联ID
    * @return 
    */
    public String getUserRelId(){
        return this.userRelId;
    }
    
    /**
    * 设置用户类型关联ID
    * @param userRelId
    */
    public void setUserRelId(String userRelId){
        this.userRelId=(userRelId == null ? null : userRelId.trim());
    }

    /**
     * 获取证件类型
     * @return
     */
    public Long getCertificateType(){
        return this.certificateType;
    }

    /**
     * 设置证件类型
     * @param certificateType
     */
    public void setCertificateType(Long certificateType){
        this.certificateType=certificateType;
    }
    /**
     * 获取证件号码
     * @return
     */
    public String getCertificateId(){
        return this.certificateId;
    }

    /**
     * 设置证件号码
     * @param certificateId
     */
    public void setCertificateId(String certificateId){
        this.certificateId=(certificateId == null ? null : certificateId.trim());
    }

    /**
     * 获取所属部门ID
     * @return
     */
    public String getDeptId(){
        return this.deptId;
    }

    /**
     * 设置所属部门ID
     * @param deptId
     */
    public void setDeptId(String deptId){
        this.deptId=(deptId == null ? null : deptId.trim());
    }
    /**
    * 获取邮箱
    * @return 
    */
    public String getUserEmail(){
        return this.userEmail;
    }
    
    /**
    * 设置邮箱
    * @param userEmail
    */
    public void setUserEmail(String userEmail){
        this.userEmail=(userEmail == null ? null : userEmail.trim());
    }
    /**
    * 获取性别
    * @return 
    */
    public String getUserGender(){
        return this.userGender;
    }
    
    /**
    * 设置性别
    * @param userGender
    */
    public void setUserGender(String userGender){
        this.userGender=(userGender == null ? null : userGender.trim());
    }
    /**
    * 获取头像
    * @return 
    */
    public String getAvatar(){
        return this.avatar;
    }
    
    /**
    * 设置头像
    * @param avatar
    */
    public void setAvatar(String avatar){
        this.avatar=(avatar == null ? null : avatar.trim());
    }
    /**
    * 获取主要岗位
    * @return 
    */
    public String getMainPost(){
        return this.mainPost;
    }
    
    /**
    * 设置主要岗位
    * @param mainPost
    */
    public void setMainPost(String mainPost){
        this.mainPost=(mainPost == null ? null : mainPost.trim());
    }
    /**
    * 获取政治面貌
    * @return 
    */
    public String getPoliticsStatus(){
        return this.politicsStatus;
    }
    
    /**
    * 设置政治面貌
    * @param politicsStatus
    */
    public void setPoliticsStatus(String politicsStatus){
        this.politicsStatus=(politicsStatus == null ? null : politicsStatus.trim());
    }
    /**
    * 获取学历
    * @return 
    */
    public String getQualifications(){
        return this.qualifications;
    }
    
    /**
    * 设置学历
    * @param qualifications
    */
    public void setQualifications(String qualifications){
        this.qualifications=(qualifications == null ? null : qualifications.trim());
    }
    /**
    * 获取职称
    * @return 
    */
    public String getProfessionalTitle(){
        return this.professionalTitle;
    }
    
    /**
    * 设置职称
    * @param professionalTitle
    */
    public void setProfessionalTitle(String professionalTitle){
        this.professionalTitle=(professionalTitle == null ? null : professionalTitle.trim());
    }
    /**
    * 获取工作类型
    * @return 
    */
    public String getWorkType(){
        return this.workType;
    }
    
    /**
    * 设置工作类型
    * @param workType
    */
    public void setWorkType(String workType){
        this.workType=(workType == null ? null : workType.trim());
    }
    /**
    * 获取工作职责
    * @return 
    */
    public String getWorkDuty(){
        return this.workDuty;
    }
    
    /**
    * 设置工作职责
    * @param workDuty
    */
    public void setWorkDuty(String workDuty){
        this.workDuty=(workDuty == null ? null : workDuty.trim());
    }
    /**
    * 获取排序
    * @return 
    */
    public Long getSortNum(){
        return this.sortNum;
    }
    
    /**
    * 设置排序
    * @param sortNum
    */
    public void setSortNum(Long sortNum){
        this.sortNum=sortNum;
    }
    /**
    * 获取是否启用标识;字典：1启用，0禁用
    * @return 
    */
    public Long getDisableFlag(){
        return this.disableFlag;
    }
    
    /**
    * 设置是否启用标识;字典：1启用，0禁用
    * @param disableFlag
    */
    public void setDisableFlag(Long disableFlag){
        this.disableFlag=disableFlag;
    }
    /**
    * 获取是否删除标识;字典：1删除，0未删除
    * @return 
    */
    public Long getDeleteFlag(){
        return this.deleteFlag;
    }
    
    /**
    * 设置是否删除标识;字典：1删除，0未删除
    * @param deleteFlag
    */
    public void setDeleteFlag(Long deleteFlag){
        this.deleteFlag=deleteFlag;
    }
    /**
    * 获取乐观锁
    * @return 
    */
    public String getRevision(){
        return this.revision;
    }
    
    /**
    * 设置乐观锁
    * @param revision
    */
    public void setRevision(String revision){
        this.revision=(revision == null ? null : revision.trim());
    }
    /**
    * 获取创建人
    * @return 
    */
    public String getCreatedBy(){
        return this.createdBy;
    }
    
    /**
    * 设置创建人
    * @param createdBy
    */
    public void setCreatedBy(String createdBy){
        this.createdBy=(createdBy == null ? null : createdBy.trim());
    }
    /**
    * 获取创建时间
    * @return 
    */
    public Long getCreatedTime(){
        return this.createdTime;
    }
    
    /**
    * 设置创建时间
    * @param createdTime
    */
    public void setCreatedTime(Long createdTime){
        this.createdTime=createdTime;
    }
    /**
    * 获取更新人
    * @return 
    */
    public String getUpdatedBy(){
        return this.updatedBy;
    }
    
    /**
    * 设置更新人
    * @param updatedBy
    */
    public void setUpdatedBy(String updatedBy){
        this.updatedBy=(updatedBy == null ? null : updatedBy.trim());
    }
    /**
    * 获取更新时间
    * @return 
    */
    public Long getUpdatedTime(){
        return this.updatedTime;
    }
    
    /**
    * 设置更新时间
    * @param updatedTime
    */
    public void setUpdatedTime(Long updatedTime){
        this.updatedTime=updatedTime;
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
