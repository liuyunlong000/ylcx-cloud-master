package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysUserRoleEntity.java</p>
 * <p>Description:用户-角色关联实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:57
 * @version V1.0
 */
public class SysUserRoleEntity extends AbstractBaseObject{

	/** 自增编号 */
    private Long nid;
	/** 用户ID */
    private String userId;
	/** 角色ID */
    private String roleId;

    /**
    *无参构造函数
    */
    public SysUserRoleEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysUserRoleEntity(Long id){
        super();
        this.nid=id;
    }

    /**
    * 获取自增编号
    * @return 
    */
    public Long getNid(){
        return this.nid;
    }
    
    /**
    * 设置自增编号
    * @param nid
    */
    public void setNid(Long nid){
        this.nid=nid;
    }
    /**
    * 获取用户ID
    * @return 
    */
    public String getUserId(){
        return this.userId;
    }
    
    /**
    * 设置用户ID
    * @param userId
    */
    public void setUserId(String userId){
        this.userId=(userId == null ? null : userId.trim());
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

}
