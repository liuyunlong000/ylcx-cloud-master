package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysUserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.lcxbs.validate.UpdateGroup;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Title: SysUser.java</p>
 * <p>Description:用户信息实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:43
 * @version V1.0
 */
@ApiModel("用户信息")
public class SysUser extends SysUserEntity{

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增ID不能空")
    @ApiModelProperty(value = "自增ID",hidden=false,required=false,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "用户ID长度不能超过 {max}")
    @ApiModelProperty(value = "用户ID",hidden=false,required=false,example = "")
    public String getUserId(){return super.getUserId();}
    
    @Override
    @Length(min = 2,max = 100,groups ={Default.class,UpdateGroup.class}, message = "姓名长度应为{min}-{max}位")
    @ApiModelProperty(value = "真实姓名",hidden=false,required=true,example = "")
    public String getUserName(){return super.getUserName();}
    
    @Override
    @Length(min = 5,max = 100,groups ={Default.class,UpdateGroup.class}, message = "登录名长度应为{min}-{max}位")
    @ApiModelProperty(value = "登录名",hidden=false,required=true,example = "")
    public String getUserLogin(){return super.getUserLogin();}
    
    @Override
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,16}$",groups ={Default.class}, message = "密码必须是6-16位字母、数字或字符,至少包含两种")
    @ApiModelProperty(value = "密码",hidden=false,required=true,example = "")
    public String getUserPassword(){return super.getUserPassword();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "手机号码应该在{min}-{max}之间")
    @ApiModelProperty(value = "手机号码",hidden=false,required=false,example = "")
    public Long getUserPhone(){return super.getUserPhone();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "用户类型为必填项")
    @ApiModelProperty(value = "用户类型;字典：1系统，2老师，3家长",hidden=false,required=true,example = "")
    public Long getUserType(){return super.getUserType();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "用户类型关联ID长度不能超过 {max}")
    @ApiModelProperty(value = "用户类型关联ID",hidden=false,required=false,example = "")
    public String getUserRelId(){return super.getUserRelId();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "证件类型应该在{min}-{max}之间")
    @ApiModelProperty(value = "证件类型",hidden=false,required=false,example = "")
    public Long getCertificateType(){return super.getCertificateType();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "证件号码长度不能超过 {max}")
    @ApiModelProperty(value = "证件号码",hidden=false,required=false,example = "")
    public String getCertificateId(){return super.getCertificateId();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "所属部门ID长度不能超过 {max}")
    @ApiModelProperty(value = "所属部门ID",hidden=false,required=false,example = "")
    public String getDeptId(){return super.getDeptId();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "邮箱长度不能超过 {max}")
    @ApiModelProperty(value = "邮箱",hidden=false,required=false,example = "")
    public String getUserEmail(){return super.getUserEmail();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "性别长度不能超过 {max}")
    @ApiModelProperty(value = "性别",hidden=false,required=false,example = "")
    public String getUserGender(){return super.getUserGender();}
    
    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "头像长度不能超过 {max}")
    @ApiModelProperty(value = "头像",hidden=false,required=false,example = "")
    public String getAvatar(){return super.getAvatar();}
    
    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "主要岗位长度不能超过 {max}")
    @ApiModelProperty(value = "主要岗位",hidden=false,required=false,example = "")
    public String getMainPost(){return super.getMainPost();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "政治面貌长度不能超过 {max}")
    @ApiModelProperty(value = "政治面貌",hidden=false,required=false,example = "")
    public String getPoliticsStatus(){return super.getPoliticsStatus();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "学历长度不能超过 {max}")
    @ApiModelProperty(value = "学历",hidden=false,required=false,example = "")
    public String getQualifications(){return super.getQualifications();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "职称长度不能超过 {max}")
    @ApiModelProperty(value = "职称",hidden=false,required=false,example = "")
    public String getProfessionalTitle(){return super.getProfessionalTitle();}
    
    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "工作类型长度不能超过 {max}")
    @ApiModelProperty(value = "工作类型",hidden=false,required=false,example = "")
    public String getWorkType(){return super.getWorkType();}
    
    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "工作职责长度不能超过 {max}")
    @ApiModelProperty(value = "工作职责",hidden=false,required=false,example = "")
    public String getWorkDuty(){return super.getWorkDuty();}
    
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
    public SysUser(){super();}
    /**带主键构造函数*/
    public SysUser(Long id){super(id);}
	//endregion

    /**所属部门名称**/
    private String deptName;

    /**
     * @see SysUser#deptName
     * @return
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @see SysUser#deptName
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
