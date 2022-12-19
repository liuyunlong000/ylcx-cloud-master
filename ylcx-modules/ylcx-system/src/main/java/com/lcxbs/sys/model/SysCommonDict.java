package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysCommonDictEntity;
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
 * <p>Title: SysCommonDict.java</p>
 * <p>Description:数据字典实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:09:44
 * @version V1.0
 */
@ApiModel("数据字典")
public class SysCommonDict extends SysCommonDictEntity{

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "字典ID不能空")
    @ApiModelProperty(value = "字典ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "字典名称长度不能超过 {max}")
    @ApiModelProperty(value = "字典名称",hidden=false,required=false,example = "")
    public String getDictName(){return super.getDictName();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "字典编码长度不能超过 {max}")
    @ApiModelProperty(value = "字典编码",hidden=false,required=false,example = "")
    public String getDictCode(){return super.getDictCode();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "字典编码值长度不能超过 {max}")
    @ApiModelProperty(value = "字典编码值",hidden=false,required=false,example = "")
    public String getDictValue(){return super.getDictValue();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "父字典ID应该在{min}-{max}之间")
    @ApiModelProperty(value = "父字典ID",hidden=false,required=false,example = "")
    public Long getParentId(){return super.getParentId();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否子叶节点;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否子叶节点;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getLeafFlag(){return super.getLeafFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "层级应该在{min}-{max}之间")
    @ApiModelProperty(value = "层级",hidden=false,required=false,example = "")
    public Long getDictLevel(){return super.getDictLevel();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "字典类型;字典：1目录，2字典，3字典项应该在{min}-{max}之间")
    @ApiModelProperty(value = "字典类型;字典：1目录，2字典，3字典项",hidden=false,required=false,example = "")
    public Long getDictType(){return super.getDictType();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "字典文件ID应该在{min}-{max}之间")
    @ApiModelProperty(value = "字典文件ID",hidden=false,required=false,example = "")
    public Long getFileId(){return super.getFileId();}
    
    @Override
    @ApiModelProperty(value = "字典JSON",hidden=false,required=false,example = "")
    public String getDictJson(){return super.getDictJson();}
    
    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "备注长度不能超过 {max}")
    @ApiModelProperty(value = "备注",hidden=false,required=false,example = "")
    public String getDictRemark(){return super.getDictRemark();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "样式属性;其他样式扩展长度不能超过 {max}")
    @ApiModelProperty(value = "样式属性;其他样式扩展",hidden=false,required=false,example = "")
    public String getCssClass(){return super.getCssClass();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "表格回显样式长度不能超过 {max}")
    @ApiModelProperty(value = "表格回显样式",hidden=false,required=false,example = "")
    public String getListClass(){return super.getListClass();}
    
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
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "租户号长度不能超过 {max}")
    @ApiModelProperty(value = "租户号",hidden=false,required=false,example = "")
    public String getTenantId(){return super.getTenantId();}
    
	//endregion
	
	//region 构造方法
	/**无参构造函数*/
    public SysCommonDict(){super();}
    /**带主键构造函数*/
    public SysCommonDict(Long id){super(id);}
	//endregion

    /**
     * 字典项数量
     */
    private int dataCount = 0;

    /**
     * 字典项数量
     *
     * @return
     */
    public int getDataCount() {
        return dataCount;
    }

    /**
     * 字典项数量
     *
     * @param dataCount
     */
    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
}
