package com.lcxbs.sys.model;
import com.lcxbs.core.ITree;
import com.lcxbs.model.MetaVo;
import com.lcxbs.sys.entity.SysMenuEntity;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.lcxbs.validate.UpdateGroup;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Title: SysMenu.java</p>
 * <p>Description:菜单信息实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:33
 * @version V1.0
 */
@ApiModel("菜单信息")
public class SysMenu extends SysMenuEntity implements ITree {


    //region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增ID不能空")
    @ApiModelProperty(value = "自增ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "菜单ID长度不能超过 {max}")
    @ApiModelProperty(value = "菜单ID",hidden=false,required=false,example = "")
    public String getMenuId(){return super.getMenuId();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "父节点ID长度不能超过 {max}")
    @ApiModelProperty(value = "父节点ID",hidden=false,required=false,example = "")
    public String getParentId(){return super.getParentId();}

    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "菜单名称长度不能超过 {max}")
    @ApiModelProperty(value = "菜单名称",hidden=false,required=false,example = "")
    public String getMenuName(){return super.getMenuName();}

    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "菜单类型;字典：1目录，2菜单，3操作应该在{min}-{max}之间")
    @ApiModelProperty(value = "菜单类型;字典：1目录，2菜单，3操作",hidden=false,required=false,example = "")
    public Long getMenuType(){return super.getMenuType();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "菜单URL长度不能超过 {max}")
    @ApiModelProperty(value = "路由URL",hidden=false,required=false,example = "")
    public String getMenuUrl(){return super.getMenuUrl();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "组件名称长度不能超过 {max}")
    @ApiModelProperty(value = "组件名称",hidden=false,required=false,example = "")
    public String getComponentName(){return super.getComponentName();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "组件路径长度不能超过 {max}")
    @ApiModelProperty(value = "组件路径",hidden=false,required=false,example = "")
    public String getComponentPath(){return super.getComponentPath();}

    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "是否内嵌窗口;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否内嵌窗口;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getIsIframe(){return super.getIsIframe();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "外部链接地址长度不能超过 {max}")
    @ApiModelProperty(value = "外部链接地址",hidden=false,required=false,example = "")
    public String getIsLink(){return super.getIsLink();}

    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "是否隐藏菜单;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否隐藏菜单;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getIsHide(){return super.getIsHide();}

    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "是否缓存组件状态;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否缓存组件状态;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getIsKeepAlive(){return super.getIsKeepAlive();}

    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "是否固定在标签栏上;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否固定在标签栏上;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getIsAffix(){return super.getIsAffix();}

    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "是否需要鉴权应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否需要鉴权",hidden=false,required=false,example = "")
    public Long getIsAuth(){return super.getIsAuth();}

    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "是否继承框架应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否继承框架",hidden=false,required=false,example = "")
    public Long getIsInherit(){return super.getIsInherit();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "菜单图标长度不能超过 {max}")
    @ApiModelProperty(value = "菜单图标",hidden=false,required=false,example = "")
    public String getMenuIcon(){return super.getMenuIcon();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "菜单参数长度不能超过 {max}")
    @ApiModelProperty(value = "菜单参数",hidden=false,required=false,example = "")
    public String getMenuParams(){return super.getMenuParams();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "权限编码长度不能超过 {max}")
    @ApiModelProperty(value = "权限编码",hidden=false,required=false,example = "")
    public String getPermCode(){return super.getPermCode();}

    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "菜单等级应该在{min}-{max}之间")
    @ApiModelProperty(value = "菜单等级",hidden=false,required=false,example = "")
    public Long getMenuLevel(){return super.getMenuLevel();}

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
    public SysMenu(){super();}
    /**带主键构造函数*/
    public SysMenu(Long id){super(id);}
	//endregion

    private MetaVo meta;

    public MetaVo getMeta() {
        return meta;
    }

    public void setMeta(MetaVo meta) {
        this.meta = meta;
    }

    private List children;

    @Override
    public String getTreeId() {
        return this.getMenuId();
    }

    @Override
    public String getParentTreeId() {
        return this.getParentId();
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }

    @Override
    public List getChildren() {
        return this.children;
    }
}
