package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysUserDeptEntity;
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
 * <p>Title: SysUserDept.java</p>
 * <p>Description:用户-部门关联信息实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:55
 * @version V1.0
 */
@ApiModel("用户-部门关联信息")
public class SysUserDept extends SysUserDeptEntity{

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增ID不能空")
    @ApiModelProperty(value = "自增ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "用户ID长度不能超过 {max}")
    @ApiModelProperty(value = "用户ID",hidden=false,required=false,example = "")
    public String getUserId(){return super.getUserId();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "部门编码长度不能超过 {max}")
    @ApiModelProperty(value = "部门编码",hidden=false,required=false,example = "")
    public String getDeptId(){return super.getDeptId();}
    
    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "是否是主部门;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否是主部门;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getMainFlag(){return super.getMainFlag();}
    
	//endregion
	
	//region 构造方法
	/**无参构造函数*/
    public SysUserDept(){super();}
    /**带主键构造函数*/
    public SysUserDept(Long id){super(id);}
	//endregion
}
