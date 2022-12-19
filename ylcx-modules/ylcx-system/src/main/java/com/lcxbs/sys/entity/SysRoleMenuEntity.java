package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysRoleMenuEntity.java</p>
 * <p>Description:角色-菜单实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:39
 * @version V1.0
 */
public class SysRoleMenuEntity extends AbstractBaseObject{

	/** ID */
    private Long nid;
	/** 角色ID */
    private String roleId;
	/** 菜单ID */
    private String menuId;

    /**
    *无参构造函数
    */
    public SysRoleMenuEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysRoleMenuEntity(Long id){
        super();
        this.nid=id;
    }

    /**
    * 获取ID
    * @return 
    */
    public Long getNid(){
        return this.nid;
    }
    
    /**
    * 设置ID
    * @param nid
    */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
    * 获取角色ID
    * @return 
    */
    public String getRoleId(){
        return this.roleId;
    }
    
    /**
    * 设置角色ID
    * @param roleId
    */
    public void setRoleId(String roleId){
        this.roleId=(roleId == null ? null : roleId.trim());
    }
    /**
    * 获取菜单ID
    * @return 
    */
    public String getMenuId(){
        return this.menuId;
    }
    
    /**
    * 设置菜单ID
    * @param menuId
    */
    public void setMenuId(String menuId){
        this.menuId=(menuId == null ? null : menuId.trim());
    }

}
