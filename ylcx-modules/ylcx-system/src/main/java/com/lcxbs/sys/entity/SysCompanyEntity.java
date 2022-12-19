package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysCompanyEntity.java</p>
 * <p>Description:公司信息实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:45
 * @version V1.0
 */
public class SysCompanyEntity extends AbstractBaseObject{

	/** 自增ID */
    private Long nid;
	/** 公司ID */
    private String cmpyId;
	/** 上级公司ID */
    private String parentId;
	/** 公司简称 */
    private String cmpyName;
	/** 公司全称 */
    private String cmpyLongName;
	/** 公司类型 */
    private Long cmpyType;
	/** 公司地址 */
    private String cmpyAddress;
	/** 所属行政区划 */
    private String zoneCode;
	/** 公司描述 */
    private String cmpyDescription;
	/** 是否已初始化;字典：1是，0否 */
    private Long initFlag;
	/** 是否默认公司;字典：1是，0否 */
    private Long defaultFlag;
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
    public SysCompanyEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysCompanyEntity(Long id){
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
    * 获取公司ID
    * @return 
    */
    public String getCmpyId(){
        return this.cmpyId;
    }
    
    /**
    * 设置公司ID
    * @param cmpyId
    */
    public void setCmpyId(String cmpyId){
        this.cmpyId=(cmpyId == null ? null : cmpyId.trim());
    }
    /**
    * 获取上级公司ID
    * @return 
    */
    public String getParentId(){
        return this.parentId;
    }
    
    /**
    * 设置上级公司ID
    * @param parentId
    */
    public void setParentId(String parentId){
        this.parentId=(parentId == null ? null : parentId.trim());
    }
    /**
    * 获取公司简称
    * @return 
    */
    public String getCmpyName(){
        return this.cmpyName;
    }
    
    /**
    * 设置公司简称
    * @param cmpyName
    */
    public void setCmpyName(String cmpyName){
        this.cmpyName=(cmpyName == null ? null : cmpyName.trim());
    }
    /**
    * 获取公司全称
    * @return 
    */
    public String getCmpyLongName(){
        return this.cmpyLongName;
    }
    
    /**
    * 设置公司全称
    * @param cmpyLongName
    */
    public void setCmpyLongName(String cmpyLongName){
        this.cmpyLongName=(cmpyLongName == null ? null : cmpyLongName.trim());
    }
    /**
    * 获取公司类型
    * @return 
    */
    public Long getCmpyType(){
        return this.cmpyType;
    }
    
    /**
    * 设置公司类型
    * @param cmpyType
    */
    public void setCmpyType(Long cmpyType){
        this.cmpyType=cmpyType;
    }
    /**
    * 获取公司地址
    * @return 
    */
    public String getCmpyAddress(){
        return this.cmpyAddress;
    }
    
    /**
    * 设置公司地址
    * @param cmpyAddress
    */
    public void setCmpyAddress(String cmpyAddress){
        this.cmpyAddress=(cmpyAddress == null ? null : cmpyAddress.trim());
    }
    /**
    * 获取所属行政区划
    * @return 
    */
    public String getZoneCode(){
        return this.zoneCode;
    }
    
    /**
    * 设置所属行政区划
    * @param zoneCode
    */
    public void setZoneCode(String zoneCode){
        this.zoneCode=(zoneCode == null ? null : zoneCode.trim());
    }
    /**
    * 获取公司描述
    * @return 
    */
    public String getCmpyDescription(){
        return this.cmpyDescription;
    }
    
    /**
    * 设置公司描述
    * @param cmpyDescription
    */
    public void setCmpyDescription(String cmpyDescription){
        this.cmpyDescription=(cmpyDescription == null ? null : cmpyDescription.trim());
    }
    /**
    * 获取是否已初始化;字典：1是，0否
    * @return 
    */
    public Long getInitFlag(){
        return this.initFlag;
    }
    
    /**
    * 设置是否已初始化;字典：1是，0否
    * @param initFlag
    */
    public void setInitFlag(Long initFlag){
        this.initFlag=initFlag;
    }
    /**
    * 获取是否默认公司;字典：1是，0否
    * @return 
    */
    public Long getDefaultFlag(){
        return this.defaultFlag;
    }
    
    /**
    * 设置是否默认公司;字典：1是，0否
    * @param defaultFlag
    */
    public void setDefaultFlag(Long defaultFlag){
        this.defaultFlag=defaultFlag;
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
