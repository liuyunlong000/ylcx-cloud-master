package com.lcxbs.wz.entity;
import com.lcxbs.core.AbstractBaseObject;

import java.util.Date;


public class WzHomeEntity extends AbstractBaseObject{

	/** 自增ID */
    private Long nid;
	/** 标题 */
    private String title;
	/** 图片url */
    private String imgUrl;
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
    private Date createdTime;
	/** 更新人 */
    private String updatedBy;
	/** 更新时间 */
    private Date updatedTime;

    /**
    *无参构造函数
    */
    public WzHomeEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public WzHomeEntity(Long id){
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
    public Date getCreatedTime(){
        return this.createdTime;
    }

    /**
    * 设置创建时间
    * @param createdTime
    */
    public void setCreatedTime(Date createdTime){
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
    public Date getUpdatedTime(){
        return this.updatedTime;
    }

    /**
    * 设置更新时间
    * @param updatedTime
    */
    public void setUpdatedTime(Date updatedTime){
        this.updatedTime=updatedTime;
    }

}
