package com.lcxbs.sys.controller;

import com.lcxbs.auth2.enums.AuthErrorEnum;
import com.lcxbs.core.BaseController;
import com.lcxbs.exception.CommonException;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysMenu;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.sys.service.SysMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.utils.SysMenuUtil;
import com.lcxbs.utils.SysRoleUtil;
import com.lcxbs.utils.UUIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import com.lcxbs.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import com.lcxbs.validate.UpdateGroup;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.validation.groups.Default;
import javax.servlet.http.HttpServletRequest;
import com.lcxbs.utils.TreeUtil;

/**
 * <p>Title: SysMenuController.java</p>
 * <p>Description:菜单信息控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-2-26 22:48:45
 * @version V1.0
 */
@RestController
@RequestMapping("/sysMenu")
@Api(value = "/sysMenu", tags = "菜单信息")
public class SysMenuController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysMenuService sysMenuService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysMenu:save',true,#request)")
    @ApiOperation("添加菜单信息")
    @ApiOperationSupport(includeParameters = {"sysMenu.nid","sysMenu.menuId","sysMenu.parentId","sysMenu.menuName","sysMenu.menuType","sysMenu.menuUrl","sysMenu.componentName","sysMenu.componentPath","sysMenu.isIframe","sysMenu.isLink","sysMenu.isHide","sysMenu.isKeepAlive","sysMenu.isAffix","sysMenu.isAuth","sysMenu.isInherit","sysMenu.menuIcon","sysMenu.menuParams","sysMenu.permCode","sysMenu.menuLevel","sysMenu.sortNum","sysMenu.disableFlag","sysMenu.deleteFlag","sysMenu.revision","sysMenu.createdBy","sysMenu.createdTime","sysMenu.updatedBy","sysMenu.updatedTime","sysMenu.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysMenu sysMenu,HttpServletRequest request) {
        SysMenuUtil.saveOrUpdateHandle(sysMenu);
        int result = this.sysMenuService.insert(sysMenu);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysMenu.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysMenu:update',true,#request)")
    @ApiOperation("编辑菜单信息")
    @ApiOperationSupport(includeParameters = {"sysMenu.nid","sysMenu.menuId","sysMenu.parentId","sysMenu.menuName","sysMenu.menuType","sysMenu.menuUrl","sysMenu.componentName","sysMenu.componentPath","sysMenu.isIframe","sysMenu.isLink","sysMenu.isHide","sysMenu.isKeepAlive","sysMenu.isAffix","sysMenu.isAuth","sysMenu.isInherit","sysMenu.menuIcon","sysMenu.menuParams","sysMenu.permCode","sysMenu.menuLevel","sysMenu.sortNum","sysMenu.disableFlag"})
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysMenu sysMenu,HttpServletRequest request) {
        SysMenuUtil.saveOrUpdateHandle(sysMenu);
        SysMenu model=new SysMenu();
        model.setNid(sysMenu.getNid());
        model.setParentId(sysMenu.getParentId());
        model.setMenuName(sysMenu.getMenuName());
        model.setMenuType(sysMenu.getMenuType());
        model.setMenuUrl(sysMenu.getMenuUrl());
        model.setComponentName(sysMenu.getComponentName());
        model.setComponentPath(sysMenu.getComponentPath());
        model.setIsIframe(sysMenu.getIsIframe());
        model.setIsLink(sysMenu.getIsLink());
        model.setIsHide(sysMenu.getIsHide());
        model.setIsKeepAlive(sysMenu.getIsKeepAlive());
        model.setIsAffix(sysMenu.getIsAffix());
        model.setIsAuth(sysMenu.getIsAuth());
        model.setIsInherit(sysMenu.getIsInherit());
        model.setMenuIcon(sysMenu.getMenuIcon());
        model.setMenuParams(sysMenu.getMenuParams());
        model.setPermCode(sysMenu.getPermCode());
        model.setSortNum(sysMenu.getSortNum());
        model.setDisableFlag(sysMenu.getDisableFlag());
        return success(UPDATE_SUCCESS, sysMenuService.updateSelective(model));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysMenu:delete',true,#request)")
    @ApiOperation("按id删除菜单信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        SysMenu sysMenu = this.sysMenuService.getModelByKey(id);
        this.sysMenuService.existChild(sysMenu);
        return success(DELETE_SUCCESS, sysMenuService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除菜单信息")
    @PreAuthorize("@ps.hasAuthority('sysMenu:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        SysMenu sysResource = new SysMenu();
        sysResource.getMap().put("ids", ids);
        List<SysMenu> list = this.sysMenuService.getList(sysResource);
        for (SysMenu model : list) {
            this.sysMenuService.existChild(model);
        }
        return success(DELETE_SUCCESS, sysMenuService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询菜单信息")
    @PreAuthorize("@ps.hasAuthority('sysMenu:find_id',true,#request)")
    @JSON(type = SysMenu.class,include = "nid,menuId,parentId,menuName,menuType,menuUrl,componentName,componentPath,isIframe,isLink,isHide,isKeepAlive,isAffix,isAuth,isInherit,menuIcon,menuParams,permCode,menuLevel,sortNum,disableFlag")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysMenuService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取菜单信息列表")
    @PreAuthorize("@ps.hasAuthority('sysMenu:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysMenu.nid","sysMenu.menuId","sysMenu.parentId","sysMenu.menuName","sysMenu.menuType","sysMenu.menuUrl","sysMenu.componentName","sysMenu.componentPath","sysMenu.isIframe","sysMenu.isLink","sysMenu.isHide","sysMenu.isKeepAlive","sysMenu.isAffix","sysMenu.isAuth","sysMenu.isInherit","sysMenu.menuIcon","sysMenu.menuParams","sysMenu.permCode","sysMenu.menuLevel","sysMenu.sortNum","sysMenu.disableFlag","sysMenu.deleteFlag","sysMenu.revision","sysMenu.createdBy","sysMenu.createdTime","sysMenu.updatedBy","sysMenu.updatedTime","sysMenu.tenantId"})
    @JSON(type = SysMenu.class,include = "nid,menuId,parentId,menuName,menuType,menuUrl,componentName,componentPath,isIframe,isLink,isHide,isKeepAlive,isAffix,isAuth,isInherit,menuIcon,menuParams,permCode,menuLevel,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children")
    public RespMsgBean findList(@RequestBody SysMenu sysMenu,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysMenu.getSortField())) {
            sysMenu.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysMenu.getSortOrder())) {
            sysMenu.setSortOrder("asc");
        }
        sysMenuService.setupOrderByAndGroupBy(sysMenu);
        sysMenu.setDeleteFlag(0L);//未删除
        List<SysMenu> list=this.sysMenuService.getList(sysMenu);
        List<SysMenu> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysMenu:find_list',true,#request)")
    @ApiOperation("获取菜单信息列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysMenu.nid","sysMenu.menuId","sysMenu.parentId","sysMenu.menuName","sysMenu.menuType","sysMenu.menuUrl","sysMenu.componentName","sysMenu.componentPath","sysMenu.isIframe","sysMenu.isLink","sysMenu.isHide","sysMenu.isKeepAlive","sysMenu.isAffix","sysMenu.isAuth","sysMenu.isInherit","sysMenu.menuIcon","sysMenu.menuParams","sysMenu.permCode","sysMenu.menuLevel","sysMenu.sortNum","sysMenu.disableFlag","sysMenu.deleteFlag","sysMenu.revision","sysMenu.createdBy","sysMenu.createdTime","sysMenu.updatedBy","sysMenu.updatedTime","sysMenu.tenantId"})
    @JSON(type = SysMenu.class,include = "nid,menuId,parentId,menuName,menuType,menuUrl,componentName,componentPath,isIframe,isLink,isHide,isKeepAlive,isAffix,isAuth,isInherit,menuIcon,menuParams,permCode,menuLevel,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysMenu sysMenu,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(sysMenu.getPageNum(), sysMenu.getPageSize());
        if(StringUtils.isEmpty(sysMenu.getSortField())) {
            sysMenu.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysMenu.getSortOrder())) {
            sysMenu.setSortOrder("asc");
        }
        sysMenuService.setupOrderByAndGroupBy(sysMenu);
        sysMenu.setDeleteFlag(0L);//未删除
        PageInfo<SysMenu> list = sysMenuService.getListByPage(sysMenu);
        List<SysMenu> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }

    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysMenu:disable',true,#request)")
    @ApiOperation("按id启用或禁用菜单信息")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysMenu model=new SysMenu(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysMenuService.updateSelective(model));
    }

    @PostMapping("/routerList")
    @ApiOperation("获取当前用户的资源路由列表")
    public RespMsgBean routerList(HttpServletRequest request) {
        SysUser currentUser = this.sysUserService.getCurrentUser();
        if (currentUser == null) {
            throw new CommonException(AuthErrorEnum.UNAUTHORIZED.getCode(), AuthErrorEnum.UNAUTHORIZED.getName());
        }
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDeleteFlag(0L);//未删除
        sysMenu.setDisableFlag(1L);//启用
        sysMenu.getMap().put("menuTypes", new Integer[]{1, 2});//只获取目录、菜单
        sysMenu.getMap().put("orderBy", "order by A.SORT_NUM asc");
        List<SysMenu> sysMenuList=null;
        if(currentUser.getNid()==0){
            //管理员获取所有菜单
            sysMenuList = this.sysMenuService.getList(sysMenu);
        }else {
            sysMenuList = this.sysMenuService.getListByUserId(currentUser.getUserId(), sysMenu);
        }
        List<SysMenu> menuList = TreeUtil.buildTree(sysMenuList);
        List<SysMenu> routeList=sysMenuList.stream().filter(p->p.getMenuType()==2).collect(Collectors.toList());
        Map map = new HashMap();
        //首页地址
        map.put("firstPath", SysMenuUtil.getFirstPath(menuList));
        //构建前端路由
        map.put("routeList", SysMenuUtil.buildRouterList(routeList));
        //构建前端菜单树
        map.put("menuList", SysMenuUtil.buildMenuTree(menuList));
        return success(FIND_SUCCESS, map);
    }

    @PostMapping("/menu_tree_list")
    @ApiOperation("获取菜单树形列表")
    @PreAuthorize("@ps.hasAuthority('sysMenu:find_id',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysMenu.nid","sysMenu.menuId","sysMenu.parentId","sysMenu.menuName","sysMenu.menuType","sysMenu.menuUrl","sysMenu.componentName","sysMenu.componentPath","sysMenu.isIframe","sysMenu.isLink","sysMenu.isHide","sysMenu.isKeepAlive","sysMenu.isAffix","sysMenu.isAuth","sysMenu.isInherit","sysMenu.menuIcon","sysMenu.menuParams","sysMenu.permCode","sysMenu.menuLevel","sysMenu.sortNum","sysMenu.disableFlag"})
    @JSON(type = SysMenu.class,include = "nid,menuId,parentId,menuName,menuType,menuUrl,componentName,componentPath,isIframe,isLink,isHide,isKeepAlive,isAffix,isAuth,isInherit,menuIcon,menuParams,permCode,menuLevel,sortNum,disableFlag,children")
    public RespMsgBean menuTreeList(@RequestParam(value = "menuTypes", defaultValue = "1,2") Integer[] menuTypes,
                                    @RequestParam(value = "rootMenuFlag", defaultValue = "1") Long rootMenuFlag,
                                    HttpServletRequest request) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDeleteFlag(0L);
        sysMenu.getMap().put("menuTypes", menuTypes);//默认只获取目录、菜单
        sysMenu.getMap().put("orderBy", "order by A.SORT_NUM asc");
        List<SysMenu> sysMenuList = this.sysMenuService.getList(sysMenu);
        List<SysMenu> menuList = TreeUtil.buildTree(sysMenuList);
        return success(FIND_SUCCESS, menuList);
    }


}
