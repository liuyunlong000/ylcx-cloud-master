package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysCommonIconEntity.java</p>
 * <p>Description:图标信息实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:37
 * @version V1.0
 */
public class SysCommonIconEntity extends AbstractBaseObject{

	/** 主键 */
    private Long nid;
	/** 图标标签 */
    private String iconLabel;
	/** 图标名称 */
    private String iconName;
	/** 分类 */
    private String sourceType;
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
    public SysCommonIconEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysCommonIconEntity(Long id){
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
    * 获取图标标签
    * @return 
    */
    public String getIconLabel(){
        return this.iconLabel;
    }
    
    /**
    * 设置图标标签
    * @param iconLabel
    */
    public void setIconLabel(String iconLabel){
        this.iconLabel=(iconLabel == null ? null : iconLabel.trim());
    }
    /**
    * 获取图标名称
    * @return 
    */
    public String getIconName(){
        return this.iconName;
    }
    
    /**
    * 设置图标名称
    * @param iconName
    */
    public void setIconName(String iconName){
        this.iconName=(iconName == null ? null : iconName.trim());
    }
    /**
    * 获取分类
    * @return 
    */
    public String getSourceType(){
        return this.sourceType;
    }
    
    /**
    * 设置分类
    * @param sourceType
    */
    public void setSourceType(String sourceType){
        this.sourceType=(sourceType == null ? null : sourceType.trim());
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
