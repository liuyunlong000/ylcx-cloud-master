package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysCommonLog;
import com.lcxbs.sys.service.SysCommonLogService;
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
 * <p>Title: SysCommonLogController.java</p>
 * <p>Description:登录日志控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:40
 * @version V1.0
 */
@RestController
@RequestMapping("/sysCommonLog")
@Api(value = "/sysCommonLog", tags = "登录日志")
public class SysCommonLogController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysCommonLogService sysCommonLogService;

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysCommonLog:delete',true,#request)")
    @ApiOperation("按id删除登录日志")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysCommonLogService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除登录日志")
    @PreAuthorize("@ps.hasAuthority('sysCommonLog:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysCommonLogService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询登录日志")
    @PreAuthorize("@ps.hasAuthority('sysCommonLog:find_id',true,#request)")
    @JSON(type = SysCommonLog.class,include = "nid,userLogin,ipAddress,loginLocation,browser,osType,loginState,logMsg,platform,loginTime,logoutTime,onlineDuration,tenantId,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysCommonLogService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取登录日志列表")
    @PreAuthorize("@ps.hasAuthority('sysCommonLog:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysCommonLog.nid","sysCommonLog.userLogin","sysCommonLog.ipAddress","sysCommonLog.loginLocation","sysCommonLog.browser","sysCommonLog.osType","sysCommonLog.loginState","sysCommonLog.logMsg","sysCommonLog.platform","sysCommonLog.loginTime","sysCommonLog.logoutTime","sysCommonLog.onlineDuration","sysCommonLog.tenantId"})
    @JSON(type = SysCommonLog.class,include = "nid,userLogin,ipAddress,loginLocation,browser,osType,loginState,logMsg,platform,loginTime,logoutTime,onlineDuration,tenantId")
    public RespMsgBean findList(@RequestBody(required = false) SysCommonLog sysCommonLog,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysCommonLog.getSortField())) {
            sysCommonLog.setSortField("loginTime");
        }
        if(StringUtils.isEmpty(sysCommonLog.getSortOrder())) {
            sysCommonLog.setSortOrder("desc");
        }
        List<SysCommonLog> list=this.sysCommonLogService.getList(sysCommonLog);
        List<SysCommonLog> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysCommonLog:find_list',true,#request)")
    @ApiOperation("获取登录日志列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysCommonLog.nid","sysCommonLog.userLogin","sysCommonLog.ipAddress","sysCommonLog.loginLocation","sysCommonLog.browser","sysCommonLog.osType","sysCommonLog.loginState","sysCommonLog.logMsg","sysCommonLog.platform","sysCommonLog.loginTime","sysCommonLog.logoutTime","sysCommonLog.onlineDuration","sysCommonLog.tenantId"})
    @JSON(type = SysCommonLog.class,include = "nid,userLogin,ipAddress,loginLocation,browser,osType,loginState,logMsg,platform,loginTime,logoutTime,onlineDuration,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysCommonLog sysCommonLog,HttpServletRequest request) throws  Exception {
        PageHelper.startPage(sysCommonLog.getPageNum(), sysCommonLog.getPageSize());
        if(StringUtils.isEmpty(sysCommonLog.getSortField())) {
            sysCommonLog.setSortField("loginTime");
        }
        if(StringUtils.isEmpty(sysCommonLog.getSortOrder())) {
            sysCommonLog.setSortOrder("desc");
        }
        sysCommonLogService.setupOrderByAndGroupBy(sysCommonLog);
        PageInfo<SysCommonLog> list = sysCommonLogService.getListByPage(sysCommonLog);
        List<SysCommonLog> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS, list);
    }
}
