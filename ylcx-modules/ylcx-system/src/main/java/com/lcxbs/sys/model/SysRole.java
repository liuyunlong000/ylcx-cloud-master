package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysRoleEntity;
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
 * <p>Title: SysRole.java</p>
 * <p>Description:角色信息实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-6 22:59:59
 * @version V1.0
 */
@ApiModel("角色信息")
public class SysRole extends SysRoleEntity{

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增不能空")
    @ApiModelProperty(value = "自增",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "角色ID长度不能超过 {max}")
    @ApiModelProperty(value = "角色ID",hidden=false,required=false,example = "")
    public String getRoleId(){return super.getRoleId();}
    
    @Override
    @Length(min=2,max = 20,groups ={Default.class,UpdateGroup.class}, message = "角色名称长度应为{min}-{max}字符")
    @ApiModelProperty(value = "角色名称",hidden=false,required=false,example = "")
    public String getRoleName(){return super.getRoleName();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "角色描述长度不能超过 {max}")
    @ApiModelProperty(value = "角色描述",hidden=false,required=false,example = "")
    public String getRoleRemark(){return super.getRoleRemark();}
    
    @Override
    @Length(max = 300,groups ={Default.class,UpdateGroup.class}, message = "头像长度不能超过 {max}")
    @ApiModelProperty(value = "头像",hidden=false,required=false,example = "")
    public String getRoleAvatar(){return super.getRoleAvatar();}
    
    @Override
    @Length(min=4,max = 20,groups ={Default.class,UpdateGroup.class}, message = "角色编码长度应为{min}-{max}字符")
    @ApiModelProperty(value = "角色编码",hidden=false,required=false,example = "")
    public String getRoleCode(){return super.getRoleCode();}
    
    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "是否为默认角色;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否为默认角色;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getDefaultFlag(){return super.getDefaultFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "排序应该在{min}-{max}之间")
    @ApiModelProperty(value = "排序",hidden=false,required=false,example = "")
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
    public SysRole(){super();}
    /**带主键构造函数*/
    public SysRole(Long id){super(id);}
	//endregion
}
