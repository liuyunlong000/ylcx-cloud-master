package com.lcxbs.wz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcxbs.validate.UpdateGroup;
import com.lcxbs.wz.entity.WzComplanyOrgEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Date;

@ApiModel("组织机构")
public class WzComplanyOrg extends WzComplanyOrgEntity {


	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增ID不能空")
    @ApiModelProperty(value = "自增ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "标题长度不能超过 {max}")
    @ApiModelProperty(value = "机构名称",hidden=false,required=false,example = "")
    public String getName(){return super.getName();}

    @Override
    @NotNull(groups = {UpdateGroup.class},message = "父ID不能空")
    @ApiModelProperty(value = "父ID",hidden=false,required=true,example = "")
    public Long getParentId(){return super.getParentId();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "排序编号应该在{min}-{max}之间")
    @ApiModelProperty(value = "排序编号",hidden=false,required=false,example = "")
    public Long getSortNum(){return super.getSortNum();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否启用标识;字典：1启用，0禁用应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否启用标识;字典：1启用，0禁用",hidden=false,required=false,example = "")
    public Long getDisableFlag(){return super.getDisableFlag();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否删除标识;字典：1删除，0未删除应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否删除标识;字典：1删除，0未删除",hidden=false,required=false,example = "")
    public Long getDeleteFlag(){return super.getDeleteFlag();}

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreatedTime(){return super.getCreatedTime();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "更新人长度不能超过 {max}")
    @ApiModelProperty(value = "更新人",hidden=false,required=false,example = "")
    public String getUpdatedBy(){return super.getUpdatedBy();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "更新时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "更新时间",hidden=false,required=false,example = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdatedTime(){return super.getUpdatedTime();}

	//endregion

	//region 构造方法
	/**无参构造函数*/
    public WzComplanyOrg(){super();}
    /**带主键构造函数*/
    public WzComplanyOrg(Long id){super(id);}
	//endregion
}
