package com.lcxbs.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单元信息
 * @author fanlianwei
 */
public class MetaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单、面包屑、多标签页显示的名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    /**
     * 超链接菜单地址(为空则不为超链接菜单，)
     */
    private String isLink="";

    /**
     * 是否隐藏此路由（默认值：false）
     */
    private boolean isHide=false;

    /**
     * 是否缓存组件状态（默认值：true）
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isKeepAlive =true;

    /**
     * 是否固定在 tagsView 栏上(默认：false)
     */
    private boolean isAffix=false;

    /**
     * 是否内嵌窗口（默认值：true）
     */
    private boolean isIframe=true;

    /**
     * 是否需要授权验证（默认值：true）
     */
    private boolean auth=true;

    /**
     * 是否继承主框架（默认值：true）
     */
    private boolean inherit=true;

    /**
     * 当前路由权限标识，取角色管理。控制路由显示、隐藏
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> roles;

    /**
     * 图标样式名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icon;


    /**
     * 动态传参路由是否新开标签页，默认false
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean dynamicNewTab=false;

    /**
     * 路由参数
     */
    private String params;

    /**
     * 菜单ID
     */
    private String menuId;



    /**
     * @see MetaVo#params
     */
    public String getParams() {
        return params;
    }

    /**
     * @see MetaVo#params
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * @see MetaVo#menuId
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @see MetaVo#menuId
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * @see MetaVo#isHide
     * @return
     */
    public boolean getIsHide() {
        return isHide;
    }

    /**
     * @see MetaVo#isHide
     * @return
     */
    public void setIsHide(boolean hide) {
        this.isHide = hide;
    }

    /**
     * @see MetaVo#title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * @see MetaVo#title
     * @return
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @see MetaVo#roles
     * @return
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * @see MetaVo#roles
     * @return
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * @see MetaVo#icon
     * @return
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @see MetaVo#icon
     * @return
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }


    /**
     * @see MetaVo#isKeepAlive
     * @return
     */
    public boolean getIsKeepAlive() {
        return isKeepAlive;
    }

    /**
     * @see MetaVo#isKeepAlive
     * @return
     */
    public void setIsKeepAlive(boolean keepAlive) {
        this.isKeepAlive = keepAlive;
    }


    /**
     * @see MetaVo#dynamicNewTab
     * @return
     */
    public boolean getDynamicNewTab() {
        return dynamicNewTab;
    }

    /**
     * @see MetaVo#dynamicNewTab
     * @return
     */
    public void setDynamicNewTab(boolean dynamicNewTab) {
        this.dynamicNewTab = dynamicNewTab;
    }

    public String getIsLink() {
        return isLink;
    }

    public void setIsLink(String link) {
        isLink = link;
    }

    public boolean getIsAffix() {
        return isAffix;
    }

    public void setIsAffix(boolean affix) {
        isAffix = affix;
    }

    public boolean getIsIframe() {
        return isIframe;
    }

    public void setIsIframe(boolean iframe) {
        isIframe = iframe;
    }


    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isInherit() {
        return inherit;
    }

    public void setInherit(boolean inherit) {
        this.inherit = inherit;
    }
}
