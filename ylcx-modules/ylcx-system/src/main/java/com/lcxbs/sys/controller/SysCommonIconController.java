package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysCommonIcon;
import com.lcxbs.sys.service.SysCommonIconService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.json.annotation.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import com.lcxbs.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import com.lcxbs.validate.UpdateGroup;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.validation.groups.Default;
import javax.servlet.http.HttpServletRequest;
import com.lcxbs.utils.TreeUtil;

/**
 * <p>Title: SysCommonIconController.java</p>
 * <p>Description:图标信息控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:37
 * @version V1.0
 */
@RestController
@RequestMapping("/sysCommonIcon")
@Api(value = "/sysCommonIcon", tags = "图标信息")
public class SysCommonIconController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysCommonIconService sysCommonIconService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysCommonIcon:save',true,#request)")
    @ApiOperation("添加图标信息")
    @ApiOperationSupport(includeParameters = {"sysCommonIcon.nid","sysCommonIcon.iconLabel","sysCommonIcon.iconName","sysCommonIcon.sourceType","sysCommonIcon.sortNum","sysCommonIcon.disableFlag","sysCommonIcon.deleteFlag","sysCommonIcon.revision","sysCommonIcon.createdBy","sysCommonIcon.createdTime","sysCommonIcon.updatedBy","sysCommonIcon.updatedTime","sysCommonIcon.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysCommonIcon sysCommonIcon,HttpServletRequest request) {
        int result = sysCommonIconService.insert(sysCommonIcon);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysCommonIcon.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysCommonIcon:update',true,#request)")
    @ApiOperation("编辑图标信息")
    @ApiOperationSupport(includeParameters = {"sysCommonIcon.nid","sysCommonIcon.iconLabel","sysCommonIcon.iconName","sysCommonIcon.sourceType","sysCommonIcon.sortNum","sysCommonIcon.disableFlag","sysCommonIcon.deleteFlag","sysCommonIcon.revision","sysCommonIcon.createdBy","sysCommonIcon.createdTime","sysCommonIcon.updatedBy","sysCommonIcon.updatedTime","sysCommonIcon.tenantId"})
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysCommonIcon sysCommonIcon,HttpServletRequest request) {
        SysCommonIcon model=new SysCommonIcon();
	    model.setNid(sysCommonIcon.getNid());
	    model.setIconLabel(sysCommonIcon.getIconLabel());
	    model.setIconName(sysCommonIcon.getIconName());
	    model.setSourceType(sysCommonIcon.getSourceType());
	    model.setSortNum(sysCommonIcon.getSortNum());
	    model.setDisableFlag(sysCommonIcon.getDisableFlag());
	    model.setDeleteFlag(sysCommonIcon.getDeleteFlag());
	    model.setRevision(sysCommonIcon.getRevision());
	    model.setCreatedBy(sysCommonIcon.getCreatedBy());
	    model.setCreatedTime(sysCommonIcon.getCreatedTime());
	    model.setUpdatedBy(sysCommonIcon.getUpdatedBy());
	    model.setUpdatedTime(sysCommonIcon.getUpdatedTime());
	    model.setTenantId(sysCommonIcon.getTenantId());
        return success(UPDATE_SUCCESS, sysCommonIconService.updateSelective(model));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysCommonIcon:delete',true,#request)")
    @ApiOperation("按id删除图标信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysCommonIconService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除图标信息")
    @PreAuthorize("@ps.hasAuthority('sysCommonIcon:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysCommonIconService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询图标信息")
    @PreAuthorize("@ps.hasAuthority('sysCommonIcon:find_id',true,#request)")
    @JSON(type = SysCommonIcon.class,include = "nid,iconLabel,iconName,sourceType,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysCommonIconService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取图标信息列表")
    @PreAuthorize("@ps.hasAuthority('sysCommonIcon:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysCommonIcon.nid","sysCommonIcon.iconLabel","sysCommonIcon.iconName","sysCommonIcon.sourceType","sysCommonIcon.sortNum","sysCommonIcon.disableFlag","sysCommonIcon.deleteFlag","sysCommonIcon.revision","sysCommonIcon.createdBy","sysCommonIcon.createdTime","sysCommonIcon.updatedBy","sysCommonIcon.updatedTime","sysCommonIcon.tenantId"})
    @JSON(type = SysCommonIcon.class,include = "nid,iconLabel,iconName,sourceType,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children")
    public RespMsgBean findList(@RequestBody(required = false) SysCommonIcon sysCommonIcon,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysCommonIcon.getSortField())) {
            sysCommonIcon.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCommonIcon.getSortOrder())) {
            sysCommonIcon.setSortOrder("asc");
        }
        sysCommonIconService.setupOrderByAndGroupBy(sysCommonIcon);
        sysCommonIcon.setDeleteFlag(0L);//未删除
        List<SysCommonIcon> list=this.sysCommonIconService.getList(sysCommonIcon);
        List<SysCommonIcon> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysCommonIcon:find_list',true,#request)")
    @ApiOperation("获取图标信息列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysCommonIcon.nid","sysCommonIcon.iconLabel","sysCommonIcon.iconName","sysCommonIcon.sourceType","sysCommonIcon.sortNum","sysCommonIcon.disableFlag","sysCommonIcon.deleteFlag","sysCommonIcon.revision","sysCommonIcon.createdBy","sysCommonIcon.createdTime","sysCommonIcon.updatedBy","sysCommonIcon.updatedTime","sysCommonIcon.tenantId"})
    @JSON(type = SysCommonIcon.class,include = "nid,iconLabel,iconName,sourceType,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysCommonIcon sysCommonIcon,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(sysCommonIcon.getPageNum(), sysCommonIcon.getPageSize());
        if(StringUtils.isEmpty(sysCommonIcon.getSortField())) {
            sysCommonIcon.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCommonIcon.getSortOrder())) {
            sysCommonIcon.setSortOrder("asc");
        }
        sysCommonIcon.setDeleteFlag(0L);//未删除
	   sysCommonIconService.setupOrderByAndGroupBy(sysCommonIcon);
        PageInfo<SysCommonIcon> list = sysCommonIconService.getListByPage(sysCommonIcon);
        List<SysCommonIcon> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
    
    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysCommonIcon:disable',true,#request)")
    @ApiOperation("按id启用或禁用图标信息")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysCommonIcon model=new SysCommonIcon(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysCommonIconService.updateSelective(model));
    }
}
