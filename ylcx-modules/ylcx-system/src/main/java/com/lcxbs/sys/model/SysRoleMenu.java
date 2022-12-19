package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysRoleMenuEntity;
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
 * <p>Title: SysRoleMenu.java</p>
 * <p>Description:角色-菜单实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:39
 * @version V1.0
 */
@ApiModel("角色-菜单")
public class SysRoleMenu extends SysRoleMenuEntity{

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "ID不能空")
    @ApiModelProperty(value = "ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "角色ID长度不能超过 {max}")
    @ApiModelProperty(value = "角色ID",hidden=false,required=false,example = "")
    public String getRoleId(){return super.getRoleId();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "菜单ID长度不能超过 {max}")
    @ApiModelProperty(value = "菜单ID",hidden=false,required=false,example = "")
    public String getMenuId(){return super.getMenuId();}
    
	//endregion
	
	//region 构造方法
	/**无参构造函数*/
    public SysRoleMenu(){super();}
    /**带主键构造函数*/
    public SysRoleMenu(Long id){super(id);}
	//endregion
}
