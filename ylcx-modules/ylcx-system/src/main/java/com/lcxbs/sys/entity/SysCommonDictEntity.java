package com.lcxbs.sys.entity;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysCommonDictEntity.java</p>
 * <p>Description:数据字典实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:09:43
 * @version V1.0
 */
public class SysCommonDictEntity extends AbstractBaseObject{

	/** 字典ID */
    private Long nid;
	/** 字典名称 */
    private String dictName;
	/** 字典编码 */
    private String dictCode;
	/** 字典编码值 */
    private String dictValue;
	/** 父字典ID */
    private Long parentId;
	/** 是否子叶节点;字典：1是，0否 */
    private Long leafFlag;
	/** 层级 */
    private Long dictLevel;
	/** 字典类型;字典：1目录，2字典，3字典项 */
    private Long dictType;
	/** 字典文件ID */
    private Long fileId;
	/** 字典JSON */
    private String dictJson;
	/** 备注 */
    private String dictRemark;
	/** 样式属性;其他样式扩展 */
    private String cssClass;
	/** 表格回显样式 */
    private String listClass;
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
    public SysCommonDictEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysCommonDictEntity(Long id){
        super();
        this.nid=id;
    }

    /**
    * 获取字典ID
    * @return 
    */
    public Long getNid(){
        return this.nid;
    }
    
    /**
    * 设置字典ID
    * @param nid
    */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
    * 获取字典名称
    * @return 
    */
    public String getDictName(){
        return this.dictName;
    }
    
    /**
    * 设置字典名称
    * @param dictName
    */
    public void setDictName(String dictName){
        this.dictName=(dictName == null ? null : dictName.trim());
    }
    /**
    * 获取字典编码
    * @return 
    */
    public String getDictCode(){
        return this.dictCode;
    }
    
    /**
    * 设置字典编码
    * @param dictCode
    */
    public void setDictCode(String dictCode){
        this.dictCode=(dictCode == null ? null : dictCode.trim());
    }
    /**
    * 获取字典编码值
    * @return 
    */
    public String getDictValue(){
        return this.dictValue;
    }
    
    /**
    * 设置字典编码值
    * @param dictValue
    */
    public void setDictValue(String dictValue){
        this.dictValue=(dictValue == null ? null : dictValue.trim());
    }
    /**
    * 获取父字典ID
    * @return 
    */
    public Long getParentId(){
        return this.parentId;
    }
    
    /**
    * 设置父字典ID
    * @param parentId
    */
    public void setParentId(Long parentId){
        this.parentId=parentId;
    }
    /**
    * 获取是否子叶节点;字典：1是，0否
    * @return 
    */
    public Long getLeafFlag(){
        return this.leafFlag;
    }
    
    /**
    * 设置是否子叶节点;字典：1是，0否
    * @param leafFlag
    */
    public void setLeafFlag(Long leafFlag){
        this.leafFlag=leafFlag;
    }
    /**
    * 获取层级
    * @return 
    */
    public Long getDictLevel(){
        return this.dictLevel;
    }
    
    /**
    * 设置层级
    * @param dictLevel
    */
    public void setDictLevel(Long dictLevel){
        this.dictLevel=dictLevel;
    }
    /**
    * 获取字典类型;字典：1目录，2字典，3字典项
    * @return 
    */
    public Long getDictType(){
        return this.dictType;
    }
    
    /**
    * 设置字典类型;字典：1目录，2字典，3字典项
    * @param dictType
    */
    public void setDictType(Long dictType){
        this.dictType=dictType;
    }
    /**
    * 获取字典文件ID
    * @return 
    */
    public Long getFileId(){
        return this.fileId;
    }
    
    /**
    * 设置字典文件ID
    * @param fileId
    */
    public void setFileId(Long fileId){
        this.fileId=fileId;
    }
    /**
    * 获取字典JSON
    * @return 
    */
    public String getDictJson(){
        return this.dictJson;
    }
    
    /**
    * 设置字典JSON
    * @param dictJson
    */
    public void setDictJson(String dictJson){
        this.dictJson=(dictJson == null ? null : dictJson.trim());
    }
    /**
    * 获取备注
    * @return 
    */
    public String getDictRemark(){
        return this.dictRemark;
    }
    
    /**
    * 设置备注
    * @param dictRemark
    */
    public void setDictRemark(String dictRemark){
        this.dictRemark=(dictRemark == null ? null : dictRemark.trim());
    }
    /**
    * 获取样式属性;其他样式扩展
    * @return 
    */
    public String getCssClass(){
        return this.cssClass;
    }
    
    /**
    * 设置样式属性;其他样式扩展
    * @param cssClass
    */
    public void setCssClass(String cssClass){
        this.cssClass=(cssClass == null ? null : cssClass.trim());
    }
    /**
    * 获取表格回显样式
    * @return 
    */
    public String getListClass(){
        return this.listClass;
    }
    
    /**
    * 设置表格回显样式
    * @param listClass
    */
    public void setListClass(String listClass){
        this.listClass=(listClass == null ? null : listClass.trim());
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
