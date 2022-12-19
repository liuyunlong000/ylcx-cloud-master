package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.exception.CommonException;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.*;
import com.lcxbs.sys.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
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

/**
 * <p>Title: SysRoleController.java</p>
 * <p>Description:角色信息控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-2-26 22:48:47
 * @version V1.0
 */
@RestController
@RequestMapping("/sysRole")
@Api(value = "/sysRole", tags = "角色信息")
public class SysRoleController extends BaseController {
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysRole:save',true,#request)")
    @ApiOperation("添加角色信息")
    @ApiOperationSupport(includeParameters = {"sysRole.nid","sysRole.roleId","sysRole.roleName","sysRole.roleRemak","sysRole.roleAvatar","sysRole.roleCode","sysRole.defaultFlag","sysRole.sortNum","sysRole.disableFlag","sysRole.deleteFlag","sysRole.revision","sysRole.createdBy","sysRole.createdTime","sysRole.updatedBy","sysRole.updatedTime","sysRole.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysRole sysRole,HttpServletRequest request) {
        sysRole.setRoleId(UUIDGenerator.generate());
        int result = sysRoleService.insert(sysRole);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysRole.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysRole:update',true,#request)")
    @ApiOperation("编辑角色信息")
    @ApiOperationSupport(includeParameters = {"sysRole.nid","sysRole.roleId","sysRole.roleName","sysRole.roleRemak","sysRole.roleAvatar","sysRole.roleCode","sysRole.defaultFlag","sysRole.sortNum","sysRole.disableFlag","sysRole.deleteFlag","sysRole.revision","sysRole.createdBy","sysRole.createdTime","sysRole.updatedBy","sysRole.updatedTime","sysRole.tenantId"})
    @JSON(type = SysRole.class,include = "nid,roleId,roleName,roleRemak,roleAvatar,roleCode,defaultFlag,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,map")
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysRole sysRole,HttpServletRequest request) {
        return success(UPDATE_SUCCESS, sysRoleService.updateSelective(sysRole));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysRole:delete',true,#request)")
    @ApiOperation("按id删除角色信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysRoleService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除角色信息")
    @PreAuthorize("@ps.hasAuthority('sysRole:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysRoleService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询角色信息")
    @PreAuthorize("@ps.hasAuthority('sysRole:find_id',true,#request)")
    @JSON(type = SysRole.class,include = "nid,roleId,roleName,roleRemak,roleAvatar,roleCode,defaultFlag,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysRoleService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取角色信息列表")
    @PreAuthorize("@ps.hasAuthority('sysRole:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysRole.nid","sysRole.roleId","sysRole.roleName","sysRole.roleRemak","sysRole.roleAvatar","sysRole.roleCode","sysRole.defaultFlag","sysRole.sortNum","sysRole.disableFlag","sysRole.deleteFlag","sysRole.revision","sysRole.createdBy","sysRole.createdTime","sysRole.updatedBy","sysRole.updatedTime","sysRole.tenantId"})
    @JSON(type = SysRole.class,include = "nid,roleId,roleName,roleRemak,roleAvatar,roleCode,defaultFlag,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children")
    public RespMsgBean findList(@RequestBody(required = false) SysRole sysRole,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysRole.getSortField())) {
            sysRole.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysRole.getSortOrder())) {
            sysRole.setSortOrder("asc");
        }
        sysRoleService.setupOrderByAndGroupBy(sysRole);
        sysRole.setDeleteFlag(0L);
        List<SysRole> list=this.sysRoleService.getList(sysRole);
        List<SysRole> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysRole:find_list',true,#request)")
    @ApiOperation("获取角色信息列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysRole.nid","sysRole.roleId","sysRole.roleName","sysRole.roleRemak","sysRole.roleAvatar","sysRole.roleCode","sysRole.defaultFlag","sysRole.sortNum","sysRole.disableFlag","sysRole.deleteFlag","sysRole.revision","sysRole.createdBy","sysRole.createdTime","sysRole.updatedBy","sysRole.updatedTime","sysRole.tenantId"})
    @JSON(type = SysRole.class,include = "nid,roleId,roleName,roleRemak,roleAvatar,roleCode,defaultFlag,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysRole sysRole,HttpServletRequest request) throws  Exception {
        PageHelper.startPage(sysRole.getPageNum(), sysRole.getPageSize());
        if (StringUtils.isEmpty(sysRole.getSortField())) {
            sysRole.setSortField("sortNum");
        }
        if (StringUtils.isEmpty(sysRole.getSortOrder())) {
            sysRole.setSortOrder("asc");
        }
        sysRoleService.setupOrderByAndGroupBy(sysRole);
        sysRole.setDeleteFlag(0L);
        PageInfo<SysRole> list = sysRoleService.getListByPage(sysRole);
        List<SysRole> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS, list);
    }
    
    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysRole:disable',true,#request)")
    @ApiOperation("按id启用或禁用角色信息")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysRole model=new SysRole(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysRoleService.updateSelective(model));
    }

    @PostMapping("/role_menu_tree_select")
    @ApiOperation("菜单授权——菜单树形列表")
    @PreAuthorize("@ps.hasAuthority('sysRole:menu_grant',true,#request)")
    @JSON(type = SysMenu.class,include = "nid,menuId,parentId,menuName,menuType,sortNum,disableFlag,children")
    @JSON(type = SysRoleMenu.class, include = "nid,roleId,menuId")
    public RespMsgBean roleMenuTreeSelect(@RequestParam(value = "roleId", required = false) String roleId,
                                          @RequestParam(value = "menuTypes[]", defaultValue = "1,2,3") Integer[] menuTypes,
                                          @RequestParam(value = "rootMenuFlag", defaultValue = "1") Long rootMenuFlag,
                                          HttpServletRequest request) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDeleteFlag(0L);
        sysMenu.setDisableFlag(1L);
        sysMenu.getMap().put("menuTypes", menuTypes);//只获取目录、菜单
        sysMenu.getMap().put("orderBy", "order by A.SORT_NUM asc");
        List<SysMenu> sysMenuList = this.sysMenuService.getList(sysMenu);
        if (rootMenuFlag == 1) {
            SysMenu rootMenu = new SysMenu();
            rootMenu.setMenuName("根节点");
            rootMenu.setMenuId("0");
            rootMenu.setParentId("-1");
            sysMenuList.add(rootMenu);
        }
        List<SysMenu> menuList = TreeUtil.buildTree(sysMenuList);
        Map map = new HashMap();
        map.put("menuList", menuList);
        if (StringUtils.isNotBlank(roleId)) {//获取角色已经授权的菜单
            SysRoleMenu roleResource = new SysRoleMenu();
            roleResource.setRoleId(roleId);
            List<SysRoleMenu> roleMenuList = this.sysRoleMenuService.getList(roleResource);
            map.put("checkedKeys", SysRoleUtil.getCheckedKeys(roleMenuList));
        } else {
            map.put("checkedKeys", new ArrayList<>());
        }
        return success(FIND_SUCCESS, map);
    }

    @PostMapping("/update_menu_grant")
    @ApiOperation("更新角色的菜单授权")
    @PreAuthorize("@ps.hasAuthority('sysRole:menu_grant',true,#request)")
    public RespMsgBean updateMenuGrant(@RequestParam(value = "roleId") String roleId,
                                       @RequestParam(value = "menuIds") String menuIds,
                                       HttpServletRequest request) {
        if (StringUtils.isBlank(roleId)) {
            throw new CommonException(0, "缺少角色ID。");
        }
        String[] objIds = StringUtils.split(menuIds, ",");// 选中的菜单ID
        SysRoleMenu relateForm = new SysRoleMenu();
        relateForm.setRoleId(roleId);
        List<SysRoleMenu> oldList = this.sysRoleMenuService.getList(relateForm);// 获取原来关联的关系
        for (int i = 0; i < objIds.length; i++) {
            boolean needAdd=true;
            String objId = objIds[i];// 获取新的菜单ID
            for(SysRoleMenu roleResource:oldList){
                if(roleResource.getMenuId().equals(objId)){
                    oldList.remove(roleResource);
                    needAdd=false;//不需要添加，已经存在
                    break;
                }
            }
            if(needAdd) {
                SysRoleMenu newRelate = new SysRoleMenu();
                newRelate.setRoleId(roleId);
                newRelate.setMenuId(objId);
                this.sysRoleMenuService.insert(newRelate);
            }
        }
        for (int i = 0; i < oldList.size(); ++i) {
            SysRoleMenu oldRelate = oldList.get(i);
            this.sysRoleMenuService.delete(oldRelate.getNid());
        }
        return success(UPDATE_SUCCESS);
    }

    @PostMapping("/user_list")
    @PreAuthorize("@ps.hasAuthority('sysRole:user_grant',true,#request)")
    @ApiOperation("获取用户信息列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysUser.nid","sysUser.userId","sysUser.userName","sysUser.userLogin","sysUser.userPhone","sysUser.userType","sysUser.userRelId","sysUser.userEmail","sysUser.userGender","sysUser.avatar","sysUser.mainPost","sysUser.politicsStatus","sysUser.qualifications","sysUser.professionalTitle","sysUser.workType","sysUser.workDuty","sysUser.sortNum","sysUser.disableFlag","sysUser.createdTime","sysUser.updatedTime"})
    @JSON(type = SysUser.class,include = "nid,userId,userName,userLogin,userPhone,userType,userRelId,userEmail,userGender,avatar,mainPost,politicsStatus,qualifications,professionalTitle,workType,workDuty,sortNum,disableFlag,createdTime,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean userList(@RequestBody(required = false) SysUser sysUser, HttpServletRequest request) throws Exception {
        Object roleId = sysUser.getMap().get("roleId");
        if (roleId == null || StringUtils.isBlank(roleId.toString())) {
            throw new CommonException(0, "缺少roleId");
        }
        PageHelper.startPage(sysUser.getPageNum(), sysUser.getPageSize());
        if (StringUtils.isEmpty(sysUser.getSortField())) {
            sysUser.setSortField("sortNum");
        }
        if (StringUtils.isEmpty(sysUser.getSortOrder())) {
            sysUser.setSortOrder("asc");
        }
        this.sysUserService.setupOrderByAndGroupBy(sysUser);
        PageInfo<SysUser> list = sysUserService.getListByPage(sysUser);
        return success(FIND_SUCCESS, list);
    }

    @PostMapping("/add_user_grant")
    @ApiOperation("添加用户授权")
    @PreAuthorize("@ps.hasAuthority('sysRole:user_grant',true,#request)")
    public RespMsgBean addUserGrant(@RequestParam(value = "roleId") String roleId,
                                    @RequestParam(value = "userIds") String userIds,
                                    HttpServletRequest request) {
        if (StringUtils.isBlank(roleId)) {
            throw new CommonException(0, "缺少角色ID。");
        }
        String[] objIds = StringUtils.split(userIds, ",");// 选中的用户ID
        SysUserRole existUserRole = new SysUserRole();
        existUserRole.setRoleId(roleId);
        List<SysUserRole> existList = this.sysUserRoleService.getList(existUserRole);
        List<String> existUserId = existList.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
        int result = 0;
        for (int i = 0; i < objIds.length; ++i) {
            String objId = objIds[i];// 获取新的用户ID
            if (!existUserId.contains(objId)) {
                SysUserRole newUserRole = new SysUserRole();
                newUserRole.setRoleId(roleId);
                newUserRole.setUserId(objId);
                result = result + this.sysUserRoleService.insert(newUserRole);
            }
        }
        return success(SUCCESS, result);
    }

    @PostMapping("/remove_user_grant")
    @ApiOperation("删除用户授权")
    @PreAuthorize("@ps.hasAuthority('sysRole:user_grant',true,#request)")
    public RespMsgBean removeUserGrant(@RequestParam(value = "roleId") String roleId,
                                       @RequestParam(value = "userIds") String userIds,
                                       HttpServletRequest request) {
        if (StringUtils.isBlank(roleId)) {
            throw new CommonException(0, "缺少角色ID。");
        }
        String[] objIds = StringUtils.split(userIds, ",");// 选中的用户ID
        int result = 0;
        for (int i = 0; i < objIds.length; ++i) {
            String objId = objIds[i];
            SysUserRole model = new SysUserRole();
            model.setRoleId(roleId);
            model.setUserId(objId);
            result = result + this.sysUserRoleService.deleteBy(model);
        }
        return success(SUCCESS, result);
    }
}
