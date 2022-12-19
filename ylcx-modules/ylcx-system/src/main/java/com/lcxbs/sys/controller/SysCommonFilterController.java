package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysCommonFilter;
import com.lcxbs.sys.service.SysCommonFilterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.json.annotation.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.servlet.http.HttpServletRequest;
import com.lcxbs.utils.TreeUtil;

/**
 * <p>Title: SysCommonFilterController.java</p>
 * <p>Description:操作日志控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-8 22:27:27
 * @version V1.0
 */
@RestController
@RequestMapping("/sysCommonFilter")
@Api(value = "/sysCommonFilter", tags = "操作日志")
public class SysCommonFilterController extends BaseController {
    @Resource
    private SysCommonFilterService sysCommonFilterService;


    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysCommonFilter:delete',true,#request)")
    @ApiOperation("删除操作日志")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysCommonFilterService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("批量删除操作日志")
    @PreAuthorize("@ps.hasAuthority('sysCommonFilter:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysCommonFilterService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("查看操作日志")
    @PreAuthorize("@ps.hasAuthority('sysCommonFilter:find_id',true,#request)")
    @JSON(type = SysCommonFilter.class,include = "nid,moduleName,moduleCode,requestMethod,requestStatus,consumeTime,errorMsg,infoId,logIp,callUrl,callParams,createdBy,createdTime,tenantId,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysCommonFilterService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("操作日志列表")
    @PreAuthorize("@ps.hasAuthority('sysCommonFilter:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysCommonFilter.nid","sysCommonFilter.moduleName","sysCommonFilter.moduleCode","sysCommonFilter.requestMethod","sysCommonFilter.requestStatus","sysCommonFilter.consumeTime","sysCommonFilter.errorMsg","sysCommonFilter.infoId","sysCommonFilter.logIp","sysCommonFilter.callUrl","sysCommonFilter.callParams","sysCommonFilter.createdBy","sysCommonFilter.createdTime","sysCommonFilter.tenantId"})
    @JSON(type = SysCommonFilter.class,include = "nid,moduleName,moduleCode,requestMethod,requestStatus,consumeTime,errorMsg,infoId,logIp,callUrl,callParams,createdBy,createdTime,tenantId,children")
    public RespMsgBean findList(@RequestBody(required = false) SysCommonFilter sysCommonFilter,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysCommonFilter.getSortField())) {
            sysCommonFilter.setSortField("createdTime");
        }
        if(StringUtils.isEmpty(sysCommonFilter.getSortOrder())) {
            sysCommonFilter.setSortOrder("desc");
        }
        List<SysCommonFilter> list=this.sysCommonFilterService.getList(sysCommonFilter);
        List<SysCommonFilter> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysCommonFilter:find_list',true,#request)")
    @ApiOperation("操作日志列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysCommonFilter.nid","sysCommonFilter.moduleName","sysCommonFilter.moduleCode","sysCommonFilter.requestMethod","sysCommonFilter.requestStatus","sysCommonFilter.consumeTime","sysCommonFilter.errorMsg","sysCommonFilter.infoId","sysCommonFilter.logIp","sysCommonFilter.callUrl","sysCommonFilter.callParams","sysCommonFilter.createdBy","sysCommonFilter.createdTime","sysCommonFilter.tenantId"})
    @JSON(type = SysCommonFilter.class,include = "nid,moduleName,moduleCode,requestMethod,requestStatus,consumeTime,errorMsg,infoId,logIp,callUrl,callParams,createdBy,createdTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysCommonFilter sysCommonFilter,HttpServletRequest request) throws  Exception {
        PageHelper.startPage(sysCommonFilter.getPageNum(), sysCommonFilter.getPageSize());
        if (StringUtils.isEmpty(sysCommonFilter.getSortField())) {
            sysCommonFilter.setSortField("createdTime");
        }
        if (StringUtils.isEmpty(sysCommonFilter.getSortOrder())) {
            sysCommonFilter.setSortOrder("desc");
        }
        sysCommonFilterService.setupOrderByAndGroupBy(sysCommonFilter);
        PageInfo<SysCommonFilter> list = sysCommonFilterService.getListByPage(sysCommonFilter);
        List<SysCommonFilter> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS, list);
    }


}
