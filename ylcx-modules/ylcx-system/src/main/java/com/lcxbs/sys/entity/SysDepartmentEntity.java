package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysDepartmentEntity.java</p>
 * <p>Description:部门信息实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:30
 * @version V1.0
 */
public class SysDepartmentEntity extends AbstractBaseObject{

	/** 主键ID */
    private Long nid;
	/** 部门编码 */
    private String deptId;
	/** 部门父编码 */
    private String parentId;
	/** 部门名称 */
    private String deptName;
	/** 部门简称 */
    private String shortName;
	/** 部门负责人 */
    private String deptPrincipal;
	/** 查询字符串 */
    private String searchStr;
	/** 描述 */
    private String remark;
	/** 等级 */
    private Long levelNum;
	/** 排序编号 */
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
    public SysDepartmentEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysDepartmentEntity(Long id){
        super();
        this.nid=id;
    }

    /**
    * 获取主键ID
    * @return 
    */
    public Long getNid(){
        return this.nid;
    }
    
    /**
    * 设置主键ID
    * @param nid
    */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
    * 获取部门编码
    * @return 
    */
    public String getDeptId(){
        return this.deptId;
    }
    
    /**
    * 设置部门编码
    * @param deptId
    */
    public void setDeptId(String deptId){
        this.deptId=(deptId == null ? null : deptId.trim());
    }
    /**
    * 获取部门父编码
    * @return 
    */
    public String getParentId(){
        return this.parentId;
    }
    
    /**
    * 设置部门父编码
    * @param parentId
    */
    public void setParentId(String parentId){
        this.parentId=(parentId == null ? null : parentId.trim());
    }
    /**
    * 获取部门名称
    * @return 
    */
    public String getDeptName(){
        return this.deptName;
    }
    
    /**
    * 设置部门名称
    * @param deptName
    */
    public void setDeptName(String deptName){
        this.deptName=(deptName == null ? null : deptName.trim());
    }
    /**
    * 获取部门简称
    * @return 
    */
    public String getShortName(){
        return this.shortName;
    }
    
    /**
    * 设置部门简称
    * @param shortName
    */
    public void setShortName(String shortName){
        this.shortName=(shortName == null ? null : shortName.trim());
    }
    /**
    * 获取部门负责人
    * @return 
    */
    public String getDeptPrincipal(){
        return this.deptPrincipal;
    }
    
    /**
    * 设置部门负责人
    * @param deptPrincipal
    */
    public void setDeptPrincipal(String deptPrincipal){
        this.deptPrincipal=(deptPrincipal == null ? null : deptPrincipal.trim());
    }
    /**
    * 获取查询字符串
    * @return 
    */
    public String getSearchStr(){
        return this.searchStr;
    }
    
    /**
    * 设置查询字符串
    * @param searchStr
    */
    public void setSearchStr(String searchStr){
        this.searchStr=(searchStr == null ? null : searchStr.trim());
    }
    /**
    * 获取描述
    * @return 
    */
    public String getRemark(){
        return this.remark;
    }
    
    /**
    * 设置描述
    * @param remark
    */
    public void setRemark(String remark){
        this.remark=(remark == null ? null : remark.trim());
    }
    /**
    * 获取等级
    * @return 
    */
    public Long getLevelNum(){
        return this.levelNum;
    }
    
    /**
    * 设置等级
    * @param levelNum
    */
    public void setLevelNum(Long levelNum){
        this.levelNum=levelNum;
    }
    /**
    * 获取排序编号
    * @return 
    */
    public Long getSortNum(){
        return this.sortNum;
    }
    
    /**
    * 设置排序编号
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
