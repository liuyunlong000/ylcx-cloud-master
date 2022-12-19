package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysUserCompanyEntity.java</p>
 * <p>Description:用户-公司关联实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:51
 * @version V1.0
 */
public class SysUserCompanyEntity extends AbstractBaseObject{

	/** 自增ID */
    private Long nid;
	/** 用户ID */
    private String userId;
	/** 公司编码 */
    private String companyId;
	/** 是否为主公司;字典：1是，0否 */
    private Long mainFlag;

    /**
    *无参构造函数
    */
    public SysUserCompanyEntity(){
        super();
    }

    /**
    * 带主键构造函数
    * @param id
    */
    public SysUserCompanyEntity(Long id){
        super();
        this.nid=id;
    }

    /**
    * 获取自增ID
    * @return 
    */
    public Long getNid(){
        return this.nid;
    }
    
    /**
    * 设置自增ID
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
    * 获取公司编码
    * @return 
    */
    public String getCompanyId(){
        return this.companyId;
    }
    
    /**
    * 设置公司编码
    * @param companyId
    */
    public void setCompanyId(String companyId){
        this.companyId=(companyId == null ? null : companyId.trim());
    }
    /**
    * 获取是否为主公司;字典：1是，0否
    * @return 
    */
    public Long getMainFlag(){
        return this.mainFlag;
    }
    
    /**
    * 设置是否为主公司;字典：1是，0否
    * @param mainFlag
    */
    public void setMainFlag(Long mainFlag){
        this.mainFlag=mainFlag;
    }

}
