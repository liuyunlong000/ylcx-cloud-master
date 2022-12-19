package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysRoleEntity.java</p>
 * <p>Description:角色信息实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-6 22:59:59
 * @version V1.0
 */
public class SysRoleEntity extends AbstractBaseObject{

	/** 自增 */
    private Long nid;
	/** 角色ID */
    private String roleId;
	/** 角色名称 */
    private String roleName;
	/** 角色描述 */
    private String roleRemark;
	/** 头像 */
    private String roleAvatar;
	/** 角色编码 */
    private String roleCode;
	/** 是否为默认角色;字典：1是，0否 */
    private Long defaultFlag;
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
    public SysRoleEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysRoleEntity(Long id){
        super();
        this.nid=id;
    }

    /**
    * 获取自增
    * @return 
    */
    public Long getNid(){
        return this.nid;
    }
    
    /**
    * 设置自增
    * @param nid
    */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
    * 获取角色ID
    * @return 
    */
    public String getRoleId(){
        return this.roleId;
    }
    
    /**
    * 设置角色ID
    * @param roleId
    */
    public void setRoleId(String roleId){
        this.roleId=(roleId == null ? null : roleId.trim());
    }
    /**
    * 获取角色名称
    * @return 
    */
    public String getRoleName(){
        return this.roleName;
    }
    
    /**
    * 设置角色名称
    * @param roleName
    */
    public void setRoleName(String roleName){
        this.roleName=(roleName == null ? null : roleName.trim());
    }
    /**
    * 获取角色描述
    * @return 
    */
    public String getRoleRemark(){
        return this.roleRemark;
    }
    
    /**
    * 设置角色描述
    * @param roleRemark
    */
    public void setRoleRemark(String roleRemark){
        this.roleRemark=(roleRemark == null ? null : roleRemark.trim());
    }
    /**
    * 获取头像
    * @return 
    */
    public String getRoleAvatar(){
        return this.roleAvatar;
    }
    
    /**
    * 设置头像
    * @param roleAvatar
    */
    public void setRoleAvatar(String roleAvatar){
        this.roleAvatar=(roleAvatar == null ? null : roleAvatar.trim());
    }
    /**
    * 获取角色编码
    * @return 
    */
    public String getRoleCode(){
        return this.roleCode;
    }
    
    /**
    * 设置角色编码
    * @param roleCode
    */
    public void setRoleCode(String roleCode){
        this.roleCode=(roleCode == null ? null : roleCode.trim());
    }
    /**
    * 获取是否为默认角色;字典：1是，0否
    * @return 
    */
    public Long getDefaultFlag(){
        return this.defaultFlag;
    }
    
    /**
    * 设置是否为默认角色;字典：1是，0否
    * @param defaultFlag
    */
    public void setDefaultFlag(Long defaultFlag){
        this.defaultFlag=defaultFlag;
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
