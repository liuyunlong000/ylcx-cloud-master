package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysCommonXzqhEntity.java</p>
 * <p>Description:行政区划实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:15:33
 * @version V1.0
 */
public class SysCommonXzqhEntity extends AbstractBaseObject{

	/** 自增ID */
    private Long nid;
	/** 名称 */
    private String xzqhName;
	/** 编码 */
    private String xzqhCode;
	/** 上级行政区划编码 */
    private String parentCode;
	/** 拼音全拼 */
    private String fullSpell;
	/** 音拼简拼 */
    private String shortSpell;
	/** 名称+编码+全拼+简拼 */
    private String searchString;
	/** 默认经度 */
    private Double defaultLongitude;
	/** 默认纬度 */
    private Double defaultLatitude;
	/** 默认缩放等级 */
    private Long defaultScale;
	/** 是否启用标识;字典：1启用，0禁用 */
    private Long disableFlag;
	/** 是否删除标识;字典：1删除，0未删除 */
    private Long deleteFlag;
	/** 排序编号 */
    private Long sortNum;
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
    public SysCommonXzqhEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysCommonXzqhEntity(Long id){
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
    * 获取名称
    * @return 
    */
    public String getXzqhName(){
        return this.xzqhName;
    }
    
    /**
    * 设置名称
    * @param xzqhName
    */
    public void setXzqhName(String xzqhName){
        this.xzqhName=(xzqhName == null ? null : xzqhName.trim());
    }
    /**
    * 获取编码
    * @return 
    */
    public String getXzqhCode(){
        return this.xzqhCode;
    }
    
    /**
    * 设置编码
    * @param xzqhCode
    */
    public void setXzqhCode(String xzqhCode){
        this.xzqhCode=(xzqhCode == null ? null : xzqhCode.trim());
    }
    /**
    * 获取上级行政区划编码
    * @return 
    */
    public String getParentCode(){
        return this.parentCode;
    }
    
    /**
    * 设置上级行政区划编码
    * @param parentCode
    */
    public void setParentCode(String parentCode){
        this.parentCode=(parentCode == null ? null : parentCode.trim());
    }
    /**
    * 获取拼音全拼
    * @return 
    */
    public String getFullSpell(){
        return this.fullSpell;
    }
    
    /**
    * 设置拼音全拼
    * @param fullSpell
    */
    public void setFullSpell(String fullSpell){
        this.fullSpell=(fullSpell == null ? null : fullSpell.trim());
    }
    /**
    * 获取音拼简拼
    * @return 
    */
    public String getShortSpell(){
        return this.shortSpell;
    }
    
    /**
    * 设置音拼简拼
    * @param shortSpell
    */
    public void setShortSpell(String shortSpell){
        this.shortSpell=(shortSpell == null ? null : shortSpell.trim());
    }
    /**
    * 获取名称+编码+全拼+简拼
    * @return 
    */
    public String getSearchString(){
        return this.searchString;
    }
    
    /**
    * 设置名称+编码+全拼+简拼
    * @param searchString
    */
    public void setSearchString(String searchString){
        this.searchString=(searchString == null ? null : searchString.trim());
    }
    /**
    * 获取默认经度
    * @return 
    */
    public Double getDefaultLongitude(){
        return this.defaultLongitude;
    }
    
    /**
    * 设置默认经度
    * @param defaultLongitude
    */
    public void setDefaultLongitude(Double defaultLongitude){
        this.defaultLongitude=defaultLongitude;
    }
    /**
    * 获取默认纬度
    * @return 
    */
    public Double getDefaultLatitude(){
        return this.defaultLatitude;
    }
    
    /**
    * 设置默认纬度
    * @param defaultLatitude
    */
    public void setDefaultLatitude(Double defaultLatitude){
        this.defaultLatitude=defaultLatitude;
    }
    /**
    * 获取默认缩放等级
    * @return 
    */
    public Long getDefaultScale(){
        return this.defaultScale;
    }
    
    /**
    * 设置默认缩放等级
    * @param defaultScale
    */
    public void setDefaultScale(Long defaultScale){
        this.defaultScale=defaultScale;
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
