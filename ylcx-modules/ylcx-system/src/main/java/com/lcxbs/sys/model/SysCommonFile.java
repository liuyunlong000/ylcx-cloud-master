package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysCommonFileEntity;
import java.sql.Blob;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.lcxbs.validate.UpdateGroup;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Title: SysCommonFile.java</p>
 * <p>Description:文件信息实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:31
 * @version V1.0
 */
@ApiModel("文件信息")
public class SysCommonFile extends SysCommonFileEntity{

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增主键ID不能空")
    @ApiModelProperty(value = "自增主键ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "文件编码长度不能超过 {max}")
    @ApiModelProperty(value = "文件编码",hidden=false,required=false,example = "")
    public String getFileCode(){return super.getFileCode();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "文件名称长度不能超过 {max}")
    @ApiModelProperty(value = "文件名称",hidden=false,required=false,example = "")
    public String getFileName(){return super.getFileName();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "文件mime类型长度不能超过 {max}")
    @ApiModelProperty(value = "文件mime类型",hidden=false,required=false,example = "")
    public String getFileType(){return super.getFileType();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "附件后缀名长度不能超过 {max}")
    @ApiModelProperty(value = "附件后缀名",hidden=false,required=false,example = "")
    public String getFileSuffix(){return super.getFileSuffix();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "文件大小;单位字节应该在{min}-{max}之间")
    @ApiModelProperty(value = "文件大小;单位字节",hidden=false,required=false,example = "")
    public Long getFileSize(){return super.getFileSize();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "附件存储模式;字典：1二进制存储，2本地文件存储，3网络地址存储应该在{min}-{max}之间")
    @ApiModelProperty(value = "附件存储模式;字典：1二进制存储，2本地文件存储，3网络地址存储",hidden=false,required=false,example = "")
    public Long getFjStoredMode(){return super.getFjStoredMode();}
    
    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "文件地址长度不能超过 {max}")
    @ApiModelProperty(value = "文件地址",hidden=false,required=false,example = "")
    public String getFileAddress(){return super.getFileAddress();}
    
    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "缩略图地址长度不能超过 {max}")
    @ApiModelProperty(value = "缩略图地址",hidden=false,required=false,example = "")
    public String getThumbUrl(){return super.getThumbUrl();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "图片宽度应该在{min}-{max}之间")
    @ApiModelProperty(value = "图片宽度",hidden=false,required=false,example = "")
    public Long getImageWidth(){return super.getImageWidth();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "图片高度应该在{min}-{max}之间")
    @ApiModelProperty(value = "图片高度",hidden=false,required=false,example = "")
    public Long getImageHeight(){return super.getImageHeight();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "文件时长(视频、语音)应该在{min}-{max}之间")
    @ApiModelProperty(value = "文件时长(视频、语音)",hidden=false,required=false,example = "")
    public Long getFileTime(){return super.getFileTime();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "附件所属表长度不能超过 {max}")
    @ApiModelProperty(value = "附件所属表",hidden=false,required=false,example = "")
    public String getFtable(){return super.getFtable();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "附件所属表记录ID长度不能超过 {max}")
    @ApiModelProperty(value = "附件所属表记录ID",hidden=false,required=false,example = "")
    public String getFid(){return super.getFid();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否启用标识;字典：1启用，0禁用应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否启用标识;字典：1启用，0禁用",hidden=false,required=false,example = "")
    public Long getDisableFlag(){return super.getDisableFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否删除标识;字典：1删除，0未删除应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否删除标识;字典：1删除，0未删除",hidden=false,required=false,example = "")
    public Long getDeleteFlag(){return super.getDeleteFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "排序编号应该在{min}-{max}之间")
    @ApiModelProperty(value = "排序编号",hidden=false,required=false,example = "")
    public Long getSortNum(){return super.getSortNum();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "乐观锁长度不能超过 {max}")
    @ApiModelProperty(value = "乐观锁",hidden=false,required=false,example = "")
    public String getRevision(){return super.getRevision();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "创建人长度不能超过 {max}")
    @ApiModelProperty(value = "创建人",hidden=false,required=false,example = "")
    public String getCreatedBy(){return super.getCreatedBy();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "创建时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "创建时间",hidden=false,required=false,example = "")
    public Long getCreatedTime(){return super.getCreatedTime();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "更新人长度不能超过 {max}")
    @ApiModelProperty(value = "更新人",hidden=false,required=false,example = "")
    public String getUpdatedBy(){return super.getUpdatedBy();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "更新时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "更新时间",hidden=false,required=false,example = "")
    public Long getUpdatedTime(){return super.getUpdatedTime();}
    
    @Override
    @Length(max = 32,groups ={Default.class,UpdateGroup.class}, message = "租户号长度不能超过 {max}")
    @ApiModelProperty(value = "租户号",hidden=false,required=false,example = "")
    public String getTenantId(){return super.getTenantId();}
    
	//endregion
	
	//region 构造方法
	/**无参构造函数*/
    public SysCommonFile(){super();}
    /**带主键构造函数*/
    public SysCommonFile(Long id){super(id);}
	//endregion
}
