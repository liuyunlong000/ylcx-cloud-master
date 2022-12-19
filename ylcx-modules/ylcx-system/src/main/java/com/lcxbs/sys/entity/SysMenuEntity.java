package com.lcxbs.sys.entity;
import java.sql.Blob;
import java.util.Date;
import com.lcxbs.core.AbstractBaseObject;

/**
 * <p>Title: SysMenuEntity.java</p>
 * <p>Description:菜单信息实体</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-28 21:16:30
 * @version V1.0
 */
public class SysMenuEntity extends AbstractBaseObject{

    /** 自增ID */
    private Long nid;
    /** 菜单ID */
    private String menuId;
    /** 父节点ID */
    private String parentId;
    /** 菜单名称 */
    private String menuName;
    /** 菜单类型;字典：1目录，2菜单，3操作 */
    private Long menuType;
    /** 路由URL */
    private String menuUrl;
    /** 组件名称 */
    private String componentName;
    /** 组件路径 */
    private String componentPath;
    /** 是否内嵌窗口;字典：1是，0否 */
    private Long isIframe;
    /** 外部链接地址 */
    private String isLink;
    /** 是否隐藏菜单;字典：1是，0否 */
    private Long isHide;
    /** 是否缓存组件状态;字典：1是，0否 */
    private Long isKeepAlive;
    /** 是否固定在标签栏上;字典：1是，0否 */
    private Long isAffix;
    /** 是否需要鉴权 */
    private Long isAuth;
    /** 是否继承框架 */
    private Long isInherit;
    /** 菜单图标 */
    private String menuIcon;
    /** 菜单参数 */
    private String menuParams;
    /** 权限编码 */
    private String permCode;
    /** 菜单等级 */
    private Long menuLevel;
    /** 排序 */
    private Long sortNum;
    /** 是否启用标识;字典：1启用，0禁用 */
    private Long disableFlag;
    /** 是否删除标识;字典：1删除，0未删除 */
    private Long deleteFlag;
    /** 乐观锁 */
    private String revision;
    /** 创建人 */
    private String createdBy;
    /** 创建时间 */
    private Long createdTime;
    /** 更新人 */
    private String updatedBy;
    /** 更新时间 */
    private Long updatedTime;
    /** 租户号 */
    private String tenantId;

    /**
     *无参构造函数
     */
    public SysMenuEntity(){
        super();
    }

    /**
     * 带主键构造函数
     * @param id
     */
    public SysMenuEntity(Long id){
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
    /**
     * 获取父节点ID
     * @return
     */
    public String getParentId(){
        return this.parentId;
    }

    /**
     * 设置父节点ID
     * @param parentId
     */
    public void setParentId(String parentId){
        this.parentId=(parentId == null ? null : parentId.trim());
    }
    /**
     * 获取菜单名称
     * @return
     */
    public String getMenuName(){
        return this.menuName;
    }

    /**
     * 设置菜单名称
     * @param menuName
     */
    public void setMenuName(String menuName){
        this.menuName=(menuName == null ? null : menuName.trim());
    }
    /**
     * 获取菜单类型;字典：1目录，2菜单，3操作
     * @return
     */
    public Long getMenuType(){
        return this.menuType;
    }

    /**
     * 设置菜单类型;字典：1目录，2菜单，3操作
     * @param menuType
     */
    public void setMenuType(Long menuType){
        this.menuType=menuType;
    }
    /**
     * 获取菜单URL
     * @return
     */
    public String getMenuUrl(){
        return this.menuUrl;
    }

    /**
     * 设置菜单URL
     * @param menuUrl
     */
    public void setMenuUrl(String menuUrl){
        this.menuUrl=(menuUrl == null ? null : menuUrl.trim());
    }
    /**
     * 获取组件名称
     * @return
     */
    public String getComponentName(){
        return this.componentName;
    }

    /**
     * 设置组件名称
     * @param componentName
     */
    public void setComponentName(String componentName){
        this.componentName=(componentName == null ? null : componentName.trim());
    }
    /**
     * 获取组件路径
     * @return
     */
    public String getComponentPath(){
        return this.componentPath;
    }

    /**
     * 设置组件路径
     * @param componentPath
     */
    public void setComponentPath(String componentPath){
        this.componentPath=(componentPath == null ? null : componentPath.trim());
    }
    /**
     * 获取是否内嵌窗口;字典：1是，0否
     * @return
     */
    public Long getIsIframe(){
        return this.isIframe;
    }

    /**
     * 设置是否内嵌窗口;字典：1是，0否
     * @param isIframe
     */
    public void setIsIframe(Long isIframe){
        this.isIframe=isIframe;
    }
    /**
     * 获取外部链接地址
     * @return
     */
    public String getIsLink(){
        return this.isLink;
    }

    /**
     * 设置外部链接地址
     * @param isLink
     */
    public void setIsLink(String isLink){
        this.isLink=isLink;
    }
    /**
     * 获取是否隐藏菜单;字典：1是，0否
     * @return
     */
    public Long getIsHide(){
        return this.isHide;
    }

    /**
     * 设置是否隐藏菜单;字典：1是，0否
     * @param isHide
     */
    public void setIsHide(Long isHide){
        this.isHide=isHide;
    }
    /**
     * 获取是否缓存组件状态;字典：1是，0否
     * @return
     */
    public Long getIsKeepAlive(){
        return this.isKeepAlive;
    }

    /**
     * 设置是否缓存组件状态;字典：1是，0否
     * @param isKeepAlive
     */
    public void setIsKeepAlive(Long isKeepAlive){
        this.isKeepAlive=isKeepAlive;
    }
    /**
     * 获取是否固定在标签栏上;字典：1是，0否
     * @return
     */
    public Long getIsAffix(){
        return this.isAffix;
    }

    /**
     * 设置是否固定在标签栏上;字典：1是，0否
     * @param isAffix
     */
    public void setIsAffix(Long isAffix){
        this.isAffix=isAffix;
    }
    /**
     * 获取是否需要鉴权
     * @return
     */
    public Long getIsAuth(){
        return this.isAuth;
    }

    /**
     * 设置是否需要鉴权
     * @param isAuth
     */
    public void setIsAuth(Long isAuth){
        this.isAuth=isAuth;
    }
    /**
     * 获取是否继承框架
     * @return
     */
    public Long getIsInherit(){
        return this.isInherit;
    }

    /**
     * 设置是否继承框架
     * @param isInherit
     */
    public void setIsInherit(Long isInherit){
        this.isInherit=isInherit;
    }
    /**
     * 获取菜单图标
     * @return
     */
    public String getMenuIcon(){
        return this.menuIcon;
    }

    /**
     * 设置菜单图标
     * @param menuIcon
     */
    public void setMenuIcon(String menuIcon){
        this.menuIcon=(menuIcon == null ? null : menuIcon.trim());
    }
    /**
     * 获取菜单参数
     * @return
     */
    public String getMenuParams(){
        return this.menuParams;
    }

    /**
     * 设置菜单参数
     * @param menuParams
     */
    public void setMenuParams(String menuParams){
        this.menuParams=(menuParams == null ? null : menuParams.trim());
    }
    /**
     * 获取权限编码
     * @return
     */
    public String getPermCode(){
        return this.permCode;
    }

    /**
     * 设置权限编码
     * @param permCode
     */
    public void setPermCode(String permCode){
        this.permCode=(permCode == null ? null : permCode.trim());
    }
    /**
     * 获取菜单等级
     * @return
     */
    public Long getMenuLevel(){
        return this.menuLevel;
    }

    /**
     * 设置菜单等级
     * @param menuLevel
     */
    public void setMenuLevel(Long menuLevel){
        this.menuLevel=menuLevel;
    }
    /**
     * 获取排序
     * @return
     */
    public Long getSortNum(){
        return this.sortNum;
    }

    /**
     * 设置排序
     * @param sortNum
     */
    public void setSortNum(Long sortNum){
        this.sortNum=sortNum;
    }
    /**
     * 获取是否启用标识;字典：1启用，0禁用
     * @return
     */
    public Long getDisableFlag(){
        return this.disableFlag;
    }

    /**
     * 设置是否启用标识;字典：1启用，0禁用
     * @param disableFlag
     */
    public void setDisableFlag(Long disableFlag){
        this.disableFlag=disableFlag;
    }
    /**
     * 获取是否删除标识;字典：1删除，0未删除
     * @return
     */
    public Long getDeleteFlag(){
        return this.deleteFlag;
    }

    /**
     * 设置是否删除标识;字典：1删除，0未删除
     * @param deleteFlag
     */
    public void setDeleteFlag(Long deleteFlag){
        this.deleteFlag=deleteFlag;
    }
    /**
     * 获取乐观锁
     * @return
     */
    public String getRevision(){
        return this.revision;
    }

    /**
     * 设置乐观锁
     * @param revision
     */
    public void setRevision(String revision){
        this.revision=(revision == null ? null : revision.trim());
    }
    /**
     * 获取创建人
     * @return
     */
    public String getCreatedBy(){
        return this.createdBy;
    }

    /**
     * 设置创建人
     * @param createdBy
     */
    public void setCreatedBy(String createdBy){
        this.createdBy=(createdBy == null ? null : createdBy.trim());
    }
    /**
     * 获取创建时间
     * @return
     */
    public Long getCreatedTime(){
        return this.createdTime;
    }

    /**
     * 设置创建时间
     * @param createdTime
     */
    public void setCreatedTime(Long createdTime){
        this.createdTime=createdTime;
    }
    /**
     * 获取更新人
     * @return
     */
    public String getUpdatedBy(){
        return this.updatedBy;
    }

    /**
     * 设置更新人
     * @param updatedBy
     */
    public void setUpdatedBy(String updatedBy){
        this.updatedBy=(updatedBy == null ? null : updatedBy.trim());
    }
    /**
     * 获取更新时间
     * @return
     */
    public Long getUpdatedTime(){
        return this.updatedTime;
    }

    /**
     * 设置更新时间
     * @param updatedTime
     */
    public void setUpdatedTime(Long updatedTime){
        this.updatedTime=updatedTime;
    }
    /**
     * 获取租户号
     * @return
     */
    public String getTenantId(){
        return this.tenantId;
    }

    /**
     * 设置租户号
     * @param tenantId
     */
    public void setTenantId(String tenantId){
        this.tenantId=(tenantId == null ? null : tenantId.trim());
    }

}
