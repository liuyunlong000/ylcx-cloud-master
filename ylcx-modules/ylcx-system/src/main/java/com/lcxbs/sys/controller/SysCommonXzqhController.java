package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysCommonXzqh;
import com.lcxbs.sys.service.SysCommonXzqhService;
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
 * <p>Title: SysCommonXzqhController.java</p>
 * <p>Description:行政区划控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:15:33
 * @version V1.0
 */
@RestController
@RequestMapping("/sysCommonXzqh")
@Api(value = "/sysCommonXzqh", tags = "行政区划")
public class SysCommonXzqhController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysCommonXzqhService sysCommonXzqhService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysCommonXzqh:save',true,#request)")
    @ApiOperation("添加行政区划")
    @ApiOperationSupport(includeParameters = {"sysCommonXzqh.nid","sysCommonXzqh.xzqhName","sysCommonXzqh.xzqhCode","sysCommonXzqh.parentCode","sysCommonXzqh.fullSpell","sysCommonXzqh.shortSpell","sysCommonXzqh.searchString","sysCommonXzqh.defaultLongitude","sysCommonXzqh.defaultLatitude","sysCommonXzqh.defaultScale","sysCommonXzqh.disableFlag","sysCommonXzqh.deleteFlag","sysCommonXzqh.sortNum","sysCommonXzqh.revision","sysCommonXzqh.createdBy","sysCommonXzqh.createdTime","sysCommonXzqh.updatedBy","sysCommonXzqh.updatedTime","sysCommonXzqh.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysCommonXzqh sysCommonXzqh,HttpServletRequest request) {
        int result = sysCommonXzqhService.insert(sysCommonXzqh);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysCommonXzqh.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysCommonXzqh:update',true,#request)")
    @ApiOperation("编辑行政区划")
    @ApiOperationSupport(includeParameters = {"sysCommonXzqh.nid","sysCommonXzqh.xzqhName","sysCommonXzqh.xzqhCode","sysCommonXzqh.parentCode","sysCommonXzqh.fullSpell","sysCommonXzqh.shortSpell","sysCommonXzqh.searchString","sysCommonXzqh.defaultLongitude","sysCommonXzqh.defaultLatitude","sysCommonXzqh.defaultScale","sysCommonXzqh.disableFlag","sysCommonXzqh.deleteFlag","sysCommonXzqh.sortNum","sysCommonXzqh.revision","sysCommonXzqh.createdBy","sysCommonXzqh.createdTime","sysCommonXzqh.updatedBy","sysCommonXzqh.updatedTime","sysCommonXzqh.tenantId"})
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysCommonXzqh sysCommonXzqh,HttpServletRequest request) {
        SysCommonXzqh model=new SysCommonXzqh();
	    model.setNid(sysCommonXzqh.getNid());
	    model.setXzqhName(sysCommonXzqh.getXzqhName());
	    model.setXzqhCode(sysCommonXzqh.getXzqhCode());
	    model.setParentCode(sysCommonXzqh.getParentCode());
	    model.setFullSpell(sysCommonXzqh.getFullSpell());
	    model.setShortSpell(sysCommonXzqh.getShortSpell());
	    model.setSearchString(sysCommonXzqh.getSearchString());
	    model.setDefaultLongitude(sysCommonXzqh.getDefaultLongitude());
	    model.setDefaultLatitude(sysCommonXzqh.getDefaultLatitude());
	    model.setDefaultScale(sysCommonXzqh.getDefaultScale());
	    model.setDisableFlag(sysCommonXzqh.getDisableFlag());
	    model.setDeleteFlag(sysCommonXzqh.getDeleteFlag());
	    model.setSortNum(sysCommonXzqh.getSortNum());
	    model.setRevision(sysCommonXzqh.getRevision());
	    model.setCreatedBy(sysCommonXzqh.getCreatedBy());
	    model.setCreatedTime(sysCommonXzqh.getCreatedTime());
	    model.setUpdatedBy(sysCommonXzqh.getUpdatedBy());
	    model.setUpdatedTime(sysCommonXzqh.getUpdatedTime());
	    model.setTenantId(sysCommonXzqh.getTenantId());
        return success(UPDATE_SUCCESS, sysCommonXzqhService.updateSelective(model));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysCommonXzqh:delete',true,#request)")
    @ApiOperation("按id删除行政区划")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysCommonXzqhService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除行政区划")
    @PreAuthorize("@ps.hasAuthority('sysCommonXzqh:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysCommonXzqhService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询行政区划")
    @PreAuthorize("@ps.hasAuthority('sysCommonXzqh:find_id',true,#request)")
    @JSON(type = SysCommonXzqh.class,include = "nid,xzqhName,xzqhCode,parentCode,fullSpell,shortSpell,searchString,defaultLongitude,defaultLatitude,defaultScale,disableFlag,deleteFlag,sortNum,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysCommonXzqhService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取行政区划列表")
    @PreAuthorize("@ps.hasAuthority('sysCommonXzqh:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysCommonXzqh.nid","sysCommonXzqh.xzqhName","sysCommonXzqh.xzqhCode","sysCommonXzqh.parentCode","sysCommonXzqh.fullSpell","sysCommonXzqh.shortSpell","sysCommonXzqh.searchString","sysCommonXzqh.defaultLongitude","sysCommonXzqh.defaultLatitude","sysCommonXzqh.defaultScale","sysCommonXzqh.disableFlag","sysCommonXzqh.deleteFlag","sysCommonXzqh.sortNum","sysCommonXzqh.revision","sysCommonXzqh.createdBy","sysCommonXzqh.createdTime","sysCommonXzqh.updatedBy","sysCommonXzqh.updatedTime","sysCommonXzqh.tenantId"})
    @JSON(type = SysCommonXzqh.class,include = "nid,xzqhName,xzqhCode,parentCode,fullSpell,shortSpell,searchString,defaultLongitude,defaultLatitude,defaultScale,disableFlag,deleteFlag,sortNum,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children")
    public RespMsgBean findList(@RequestBody(required = false) SysCommonXzqh sysCommonXzqh,HttpServletRequest request) throws Exception{
        sysCommonXzqh.setDeleteFlag(0L);//未删除
        if(StringUtils.isEmpty(sysCommonXzqh.getSortField())) {
            sysCommonXzqh.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCommonXzqh.getSortOrder())) {
            sysCommonXzqh.setSortOrder("asc");
        }
        sysCommonXzqhService.setupOrderByAndGroupBy(sysCommonXzqh);
        List<SysCommonXzqh> list=this.sysCommonXzqhService.getList(sysCommonXzqh);
        List<SysCommonXzqh> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysCommonXzqh:find_list',true,#request)")
    @ApiOperation("获取行政区划列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysCommonXzqh.nid","sysCommonXzqh.xzqhName","sysCommonXzqh.xzqhCode","sysCommonXzqh.parentCode","sysCommonXzqh.fullSpell","sysCommonXzqh.shortSpell","sysCommonXzqh.searchString","sysCommonXzqh.defaultLongitude","sysCommonXzqh.defaultLatitude","sysCommonXzqh.defaultScale","sysCommonXzqh.disableFlag","sysCommonXzqh.deleteFlag","sysCommonXzqh.sortNum","sysCommonXzqh.revision","sysCommonXzqh.createdBy","sysCommonXzqh.createdTime","sysCommonXzqh.updatedBy","sysCommonXzqh.updatedTime","sysCommonXzqh.tenantId"})
    @JSON(type = SysCommonXzqh.class,include = "nid,xzqhName,xzqhCode,parentCode,fullSpell,shortSpell,searchString,defaultLongitude,defaultLatitude,defaultScale,disableFlag,deleteFlag,sortNum,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysCommonXzqh sysCommonXzqh,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(sysCommonXzqh.getPageNum(), sysCommonXzqh.getPageSize());
        sysCommonXzqh.setDeleteFlag(0L);//未删除
        if(StringUtils.isEmpty(sysCommonXzqh.getSortField())) {
            sysCommonXzqh.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCommonXzqh.getSortOrder())) {
            sysCommonXzqh.setSortOrder("asc");
        }
	   sysCommonXzqhService.setupOrderByAndGroupBy(sysCommonXzqh);
        PageInfo<SysCommonXzqh> list = sysCommonXzqhService.getListByPage(sysCommonXzqh);
        List<SysCommonXzqh> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
    
    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysCommonXzqh:disable',true,#request)")
    @ApiOperation("按id启用或禁用行政区划")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysCommonXzqh model=new SysCommonXzqh(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysCommonXzqhService.updateSelective(model));
    }
}
