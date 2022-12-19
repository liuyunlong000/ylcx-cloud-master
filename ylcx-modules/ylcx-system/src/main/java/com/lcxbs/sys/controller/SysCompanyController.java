package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysCompany;
import com.lcxbs.sys.service.SysCompanyService;
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
 * <p>Title: SysCompanyController.java</p>
 * <p>Description:公司信息控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:45
 * @version V1.0
 */
@RestController
@RequestMapping("/sysCompany")
@Api(value = "/sysCompany", tags = "公司信息")
public class SysCompanyController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysCompanyService sysCompanyService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysCompany:save',true,#request)")
    @ApiOperation("添加公司信息")
    @ApiOperationSupport(includeParameters = {"sysCompany.nid","sysCompany.cmpyId","sysCompany.parentId","sysCompany.cmpyName","sysCompany.cmpyLongName","sysCompany.cmpyType","sysCompany.cmpyAddress","sysCompany.zoneCode","sysCompany.cmpyDescription","sysCompany.initFlag","sysCompany.defaultFlag","sysCompany.sortNum","sysCompany.disableFlag","sysCompany.deleteFlag","sysCompany.revision","sysCompany.createdBy","sysCompany.createdTime","sysCompany.updatedBy","sysCompany.updatedTime","sysCompany.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysCompany sysCompany,HttpServletRequest request) {
        int result = sysCompanyService.insert(sysCompany);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysCompany.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysCompany:update',true,#request)")
    @ApiOperation("编辑公司信息")
    @ApiOperationSupport(includeParameters = {"sysCompany.nid","sysCompany.cmpyId","sysCompany.parentId","sysCompany.cmpyName","sysCompany.cmpyLongName","sysCompany.cmpyType","sysCompany.cmpyAddress","sysCompany.zoneCode","sysCompany.cmpyDescription","sysCompany.initFlag","sysCompany.defaultFlag","sysCompany.sortNum","sysCompany.disableFlag","sysCompany.deleteFlag","sysCompany.revision","sysCompany.createdBy","sysCompany.createdTime","sysCompany.updatedBy","sysCompany.updatedTime","sysCompany.tenantId"})
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysCompany sysCompany,HttpServletRequest request) {
        SysCompany model=new SysCompany();
	    model.setNid(sysCompany.getNid());
	    model.setCmpyId(sysCompany.getCmpyId());
	    model.setParentId(sysCompany.getParentId());
	    model.setCmpyName(sysCompany.getCmpyName());
	    model.setCmpyLongName(sysCompany.getCmpyLongName());
	    model.setCmpyType(sysCompany.getCmpyType());
	    model.setCmpyAddress(sysCompany.getCmpyAddress());
	    model.setZoneCode(sysCompany.getZoneCode());
	    model.setCmpyDescription(sysCompany.getCmpyDescription());
	    model.setInitFlag(sysCompany.getInitFlag());
	    model.setDefaultFlag(sysCompany.getDefaultFlag());
	    model.setSortNum(sysCompany.getSortNum());
	    model.setDisableFlag(sysCompany.getDisableFlag());
	    model.setDeleteFlag(sysCompany.getDeleteFlag());
	    model.setRevision(sysCompany.getRevision());
	    model.setCreatedBy(sysCompany.getCreatedBy());
	    model.setCreatedTime(sysCompany.getCreatedTime());
	    model.setUpdatedBy(sysCompany.getUpdatedBy());
	    model.setUpdatedTime(sysCompany.getUpdatedTime());
	    model.setTenantId(sysCompany.getTenantId());
        return success(UPDATE_SUCCESS, sysCompanyService.updateSelective(model));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysCompany:delete',true,#request)")
    @ApiOperation("按id删除公司信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysCompanyService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除公司信息")
    @PreAuthorize("@ps.hasAuthority('sysCompany:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysCompanyService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询公司信息")
    @PreAuthorize("@ps.hasAuthority('sysCompany:find_id',true,#request)")
    @JSON(type = SysCompany.class,include = "nid,cmpyId,parentId,cmpyName,cmpyLongName,cmpyType,cmpyAddress,zoneCode,cmpyDescription,initFlag,defaultFlag,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysCompanyService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取公司信息列表")
    @PreAuthorize("@ps.hasAuthority('sysCompany:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysCompany.nid","sysCompany.cmpyId","sysCompany.parentId","sysCompany.cmpyName","sysCompany.cmpyLongName","sysCompany.cmpyType","sysCompany.cmpyAddress","sysCompany.zoneCode","sysCompany.cmpyDescription","sysCompany.initFlag","sysCompany.defaultFlag","sysCompany.sortNum","sysCompany.disableFlag","sysCompany.deleteFlag","sysCompany.revision","sysCompany.createdBy","sysCompany.createdTime","sysCompany.updatedBy","sysCompany.updatedTime","sysCompany.tenantId"})
    @JSON(type = SysCompany.class,include = "nid,cmpyId,parentId,cmpyName,cmpyLongName,cmpyType,cmpyAddress,zoneCode,cmpyDescription,initFlag,defaultFlag,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children")
    public RespMsgBean findList(@RequestBody(required = false) SysCompany sysCompany,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysCompany.getSortField())) {
            sysCompany.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCompany.getSortOrder())) {
            sysCompany.setSortOrder("asc");
        }
        sysCompanyService.setupOrderByAndGroupBy(sysCompany);
        sysCompany.setDeleteFlag(0L);//未删除
        List<SysCompany> list=this.sysCompanyService.getList(sysCompany);
        List<SysCompany> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysCompany:find_list',true,#request)")
    @ApiOperation("获取公司信息列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysCompany.nid","sysCompany.cmpyId","sysCompany.parentId","sysCompany.cmpyName","sysCompany.cmpyLongName","sysCompany.cmpyType","sysCompany.cmpyAddress","sysCompany.zoneCode","sysCompany.cmpyDescription","sysCompany.initFlag","sysCompany.defaultFlag","sysCompany.sortNum","sysCompany.disableFlag","sysCompany.deleteFlag","sysCompany.revision","sysCompany.createdBy","sysCompany.createdTime","sysCompany.updatedBy","sysCompany.updatedTime","sysCompany.tenantId"})
    @JSON(type = SysCompany.class,include = "nid,cmpyId,parentId,cmpyName,cmpyLongName,cmpyType,cmpyAddress,zoneCode,cmpyDescription,initFlag,defaultFlag,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysCompany sysCompany,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(sysCompany.getPageNum(), sysCompany.getPageSize());
        if(StringUtils.isEmpty(sysCompany.getSortField())) {
            sysCompany.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCompany.getSortOrder())) {
            sysCompany.setSortOrder("asc");
        }
        sysCompany.setDeleteFlag(0L);//未删除
	   sysCompanyService.setupOrderByAndGroupBy(sysCompany);
        PageInfo<SysCompany> list = sysCompanyService.getListByPage(sysCompany);
        List<SysCompany> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
    
    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysCompany:disable',true,#request)")
    @ApiOperation("按id启用或禁用公司信息")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysCompany model=new SysCompany(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysCompanyService.updateSelective(model));
    }
}
