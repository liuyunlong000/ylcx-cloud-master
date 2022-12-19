package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysUserRoleEntity;
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
 * <p>Title: SysUserRole.java</p>
 * <p>Description:用户-角色关联实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:58
 * @version V1.0
 */
@ApiModel("用户-角色关联")
public class SysUserRole extends SysUserRoleEntity{

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增编号不能空")
    @ApiModelProperty(value = "自增编号",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "用户ID长度不能超过 {max}")
    @ApiModelProperty(value = "用户ID",hidden=false,required=false,example = "")
    public String getUserId(){return super.getUserId();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "角色ID长度不能超过 {max}")
    @ApiModelProperty(value = "角色ID",hidden=false,required=false,example = "")
    public String getRoleId(){return super.getRoleId();}
    
	//endregion
	
	//region 构造方法
	/**无参构造函数*/
    public SysUserRole(){super();}
    /**带主键构造函数*/
    public SysUserRole(Long id){super(id);}
	//endregion
}
