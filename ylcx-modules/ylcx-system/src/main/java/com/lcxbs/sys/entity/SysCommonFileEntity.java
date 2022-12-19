package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysCommonFileEntity.java</p>
 * <p>Description:文件信息实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:31
 * @version V1.0
 */
public class SysCommonFileEntity extends AbstractBaseObject{

	/** 自增主键ID */
    private Long nid;
	/** 文件编码 */
    private String fileCode;
	/** 文件名称 */
    private String fileName;
	/** 文件mime类型 */
    private String fileType;
	/** 附件后缀名 */
    private String fileSuffix;
	/** 文件大小;单位字节 */
    private Long fileSize;
	/** 附件存储模式;字典：1二进制存储，2本地文件存储，3网络地址存储 */
    private Long fjStoredMode;
	/** 文件地址 */
    private String fileAddress;
	/** 缩略图地址 */
    private String thumbUrl;
	/** 图片宽度 */
    private Long imageWidth;
	/** 图片高度 */
    private Long imageHeight;
	/** 文件时长(视频、语音) */
    private Long fileTime;
	/** 附件所属表 */
    private String ftable;
	/** 附件所属表记录ID */
    private String fid;
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
    public SysCommonFileEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysCommonFileEntity(Long id){
        super();
        this.nid=id;
    }

    /**
    * 获取自增主键ID
    * @return 
    */
    public Long getNid(){
        return this.nid;
    }
    
    /**
    * 设置自增主键ID
    * @param nid
    */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
    * 获取文件编码
    * @return 
    */
    public String getFileCode(){
        return this.fileCode;
    }
    
    /**
    * 设置文件编码
    * @param fileCode
    */
    public void setFileCode(String fileCode){
        this.fileCode=(fileCode == null ? null : fileCode.trim());
    }
    /**
    * 获取文件名称
    * @return 
    */
    public String getFileName(){
        return this.fileName;
    }
    
    /**
    * 设置文件名称
    * @param fileName
    */
    public void setFileName(String fileName){
        this.fileName=(fileName == null ? null : fileName.trim());
    }
    /**
    * 获取文件mime类型
    * @return 
    */
    public String getFileType(){
        return this.fileType;
    }
    
    /**
    * 设置文件mime类型
    * @param fileType
    */
    public void setFileType(String fileType){
        this.fileType=(fileType == null ? null : fileType.trim());
    }
    /**
    * 获取附件后缀名
    * @return 
    */
    public String getFileSuffix(){
        return this.fileSuffix;
    }
    
    /**
    * 设置附件后缀名
    * @param fileSuffix
    */
    public void setFileSuffix(String fileSuffix){
        this.fileSuffix=(fileSuffix == null ? null : fileSuffix.trim());
    }
    /**
    * 获取文件大小;单位字节
    * @return 
    */
    public Long getFileSize(){
        return this.fileSize;
    }
    
    /**
    * 设置文件大小;单位字节
    * @param fileSize
    */
    public void setFileSize(Long fileSize){
        this.fileSize=fileSize;
    }
    /**
    * 获取附件存储模式;字典：1二进制存储，2本地文件存储，3网络地址存储
    * @return 
    */
    public Long getFjStoredMode(){
        return this.fjStoredMode;
    }
    
    /**
    * 设置附件存储模式;字典：1二进制存储，2本地文件存储，3网络地址存储
    * @param fjStoredMode
    */
    public void setFjStoredMode(Long fjStoredMode){
        this.fjStoredMode=fjStoredMode;
    }
    /**
    * 获取文件地址
    * @return 
    */
    public String getFileAddress(){
        return this.fileAddress;
    }
    
    /**
    * 设置文件地址
    * @param fileAddress
    */
    public void setFileAddress(String fileAddress){
        this.fileAddress=(fileAddress == null ? null : fileAddress.trim());
    }
    /**
    * 获取缩略图地址
    * @return 
    */
    public String getThumbUrl(){
        return this.thumbUrl;
    }
    
    /**
    * 设置缩略图地址
    * @param thumbUrl
    */
    public void setThumbUrl(String thumbUrl){
        this.thumbUrl=(thumbUrl == null ? null : thumbUrl.trim());
    }
    /**
    * 获取图片宽度
    * @return 
    */
    public Long getImageWidth(){
        return this.imageWidth;
    }
    
    /**
    * 设置图片宽度
    * @param imageWidth
    */
    public void setImageWidth(Long imageWidth){
        this.imageWidth=imageWidth;
    }
    /**
    * 获取图片高度
    * @return 
    */
    public Long getImageHeight(){
        return this.imageHeight;
    }
    
    /**
    * 设置图片高度
    * @param imageHeight
    */
    public void setImageHeight(Long imageHeight){
        this.imageHeight=imageHeight;
    }
    /**
    * 获取文件时长(视频、语音)
    * @return 
    */
    public Long getFileTime(){
        return this.fileTime;
    }
    
    /**
    * 设置文件时长(视频、语音)
    * @param fileTime
    */
    public void setFileTime(Long fileTime){
        this.fileTime=fileTime;
    }
    /**
    * 获取附件所属表
    * @return 
    */
    public String getFtable(){
        return this.ftable;
    }
    
    /**
    * 设置附件所属表
    * @param ftable
    */
    public void setFtable(String ftable){
        this.ftable=(ftable == null ? null : ftable.trim());
    }
    /**
    * 获取附件所属表记录ID
    * @return 
    */
    public String getFid(){
        return this.fid;
    }
    
    /**
    * 设置附件所属表记录ID
    * @param fid
    */
    public void setFid(String fid){
        this.fid=(fid == null ? null : fid.trim());
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
