package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysCommonFile;
import com.lcxbs.sys.service.SysCommonFileService;
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
 * <p>Title: SysCommonFileController.java</p>
 * <p>Description:文件信息控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:31
 * @version V1.0
 */
@RestController
@RequestMapping("/sysCommonFile")
@Api(value = "/sysCommonFile", tags = "文件信息")
public class SysCommonFileController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysCommonFileService sysCommonFileService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysCommonFile:save',true,#request)")
    @ApiOperation("添加文件信息")
    @ApiOperationSupport(includeParameters = {"sysCommonFile.nid","sysCommonFile.fileCode","sysCommonFile.fileName","sysCommonFile.fileType","sysCommonFile.fileSuffix","sysCommonFile.fileSize","sysCommonFile.fjStoredMode","sysCommonFile.fileAddress","sysCommonFile.thumbUrl","sysCommonFile.imageWidth","sysCommonFile.imageHeight","sysCommonFile.fileTime","sysCommonFile.ftable","sysCommonFile.fid","sysCommonFile.disableFlag","sysCommonFile.deleteFlag","sysCommonFile.sortNum","sysCommonFile.revision","sysCommonFile.createdBy","sysCommonFile.createdTime","sysCommonFile.updatedBy","sysCommonFile.updatedTime","sysCommonFile.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysCommonFile sysCommonFile,HttpServletRequest request) {
        int result = sysCommonFileService.insert(sysCommonFile);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysCommonFile.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysCommonFile:update',true,#request)")
    @ApiOperation("编辑文件信息")
    @ApiOperationSupport(includeParameters = {"sysCommonFile.nid","sysCommonFile.fileCode","sysCommonFile.fileName","sysCommonFile.fileType","sysCommonFile.fileSuffix","sysCommonFile.fileSize","sysCommonFile.fjStoredMode","sysCommonFile.fileAddress","sysCommonFile.thumbUrl","sysCommonFile.imageWidth","sysCommonFile.imageHeight","sysCommonFile.fileTime","sysCommonFile.ftable","sysCommonFile.fid","sysCommonFile.disableFlag","sysCommonFile.deleteFlag","sysCommonFile.sortNum","sysCommonFile.revision","sysCommonFile.createdBy","sysCommonFile.createdTime","sysCommonFile.updatedBy","sysCommonFile.updatedTime","sysCommonFile.tenantId"})
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysCommonFile sysCommonFile,HttpServletRequest request) {
        SysCommonFile model=new SysCommonFile();
	    model.setNid(sysCommonFile.getNid());
	    model.setFileCode(sysCommonFile.getFileCode());
	    model.setFileName(sysCommonFile.getFileName());
	    model.setFileType(sysCommonFile.getFileType());
	    model.setFileSuffix(sysCommonFile.getFileSuffix());
	    model.setFileSize(sysCommonFile.getFileSize());
	    model.setFjStoredMode(sysCommonFile.getFjStoredMode());
	    model.setFileAddress(sysCommonFile.getFileAddress());
	    model.setThumbUrl(sysCommonFile.getThumbUrl());
	    model.setImageWidth(sysCommonFile.getImageWidth());
	    model.setImageHeight(sysCommonFile.getImageHeight());
	    model.setFileTime(sysCommonFile.getFileTime());
	    model.setFtable(sysCommonFile.getFtable());
	    model.setFid(sysCommonFile.getFid());
	    model.setDisableFlag(sysCommonFile.getDisableFlag());
	    model.setDeleteFlag(sysCommonFile.getDeleteFlag());
	    model.setSortNum(sysCommonFile.getSortNum());
	    model.setRevision(sysCommonFile.getRevision());
	    model.setCreatedBy(sysCommonFile.getCreatedBy());
	    model.setCreatedTime(sysCommonFile.getCreatedTime());
	    model.setUpdatedBy(sysCommonFile.getUpdatedBy());
	    model.setUpdatedTime(sysCommonFile.getUpdatedTime());
	    model.setTenantId(sysCommonFile.getTenantId());
        return success(UPDATE_SUCCESS, sysCommonFileService.updateSelective(model));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysCommonFile:delete',true,#request)")
    @ApiOperation("按id删除文件信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysCommonFileService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除文件信息")
    @PreAuthorize("@ps.hasAuthority('sysCommonFile:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysCommonFileService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询文件信息")
    @PreAuthorize("@ps.hasAuthority('sysCommonFile:find_id',true,#request)")
    @JSON(type = SysCommonFile.class,include = "nid,fileCode,fileName,fileType,fileSuffix,fileSize,fjStoredMode,fileAddress,thumbUrl,imageWidth,imageHeight,fileTime,ftable,fid,disableFlag,deleteFlag,sortNum,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysCommonFileService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取文件信息列表")
    @PreAuthorize("@ps.hasAuthority('sysCommonFile:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysCommonFile.nid","sysCommonFile.fileCode","sysCommonFile.fileName","sysCommonFile.fileType","sysCommonFile.fileSuffix","sysCommonFile.fileSize","sysCommonFile.fjStoredMode","sysCommonFile.fileAddress","sysCommonFile.thumbUrl","sysCommonFile.imageWidth","sysCommonFile.imageHeight","sysCommonFile.fileTime","sysCommonFile.ftable","sysCommonFile.fid","sysCommonFile.disableFlag","sysCommonFile.deleteFlag","sysCommonFile.sortNum","sysCommonFile.revision","sysCommonFile.createdBy","sysCommonFile.createdTime","sysCommonFile.updatedBy","sysCommonFile.updatedTime","sysCommonFile.tenantId"})
    @JSON(type = SysCommonFile.class,include = "nid,fileCode,fileName,fileType,fileSuffix,fileSize,fjStoredMode,fileAddress,thumbUrl,imageWidth,imageHeight,fileTime,ftable,fid,disableFlag,deleteFlag,sortNum,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children")
    public RespMsgBean findList(@RequestBody(required = false) SysCommonFile sysCommonFile,HttpServletRequest request) throws Exception{
        sysCommonFile.setDeleteFlag(0L);//未删除
        if(StringUtils.isEmpty(sysCommonFile.getSortField())) {
            sysCommonFile.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCommonFile.getSortOrder())) {
            sysCommonFile.setSortOrder("asc");
        }
        sysCommonFileService.setupOrderByAndGroupBy(sysCommonFile);
        List<SysCommonFile> list=this.sysCommonFileService.getList(sysCommonFile);
        List<SysCommonFile> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysCommonFile:find_list',true,#request)")
    @ApiOperation("获取文件信息列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysCommonFile.nid","sysCommonFile.fileCode","sysCommonFile.fileName","sysCommonFile.fileType","sysCommonFile.fileSuffix","sysCommonFile.fileSize","sysCommonFile.fjStoredMode","sysCommonFile.fileAddress","sysCommonFile.thumbUrl","sysCommonFile.imageWidth","sysCommonFile.imageHeight","sysCommonFile.fileTime","sysCommonFile.ftable","sysCommonFile.fid","sysCommonFile.disableFlag","sysCommonFile.deleteFlag","sysCommonFile.sortNum","sysCommonFile.revision","sysCommonFile.createdBy","sysCommonFile.createdTime","sysCommonFile.updatedBy","sysCommonFile.updatedTime","sysCommonFile.tenantId"})
    @JSON(type = SysCommonFile.class,include = "nid,fileCode,fileName,fileType,fileSuffix,fileSize,fjStoredMode,fileAddress,thumbUrl,imageWidth,imageHeight,fileTime,ftable,fid,disableFlag,deleteFlag,sortNum,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysCommonFile sysCommonFile,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(sysCommonFile.getPageNum(), sysCommonFile.getPageSize());
        sysCommonFile.setDeleteFlag(0L);//未删除
        if(StringUtils.isEmpty(sysCommonFile.getSortField())) {
            sysCommonFile.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCommonFile.getSortOrder())) {
            sysCommonFile.setSortOrder("asc");
        }
	   sysCommonFileService.setupOrderByAndGroupBy(sysCommonFile);
        PageInfo<SysCommonFile> list = sysCommonFileService.getListByPage(sysCommonFile);
        List<SysCommonFile> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
    
    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysCommonFile:disable',true,#request)")
    @ApiOperation("按id启用或禁用文件信息")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysCommonFile model=new SysCommonFile(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysCommonFileService.updateSelective(model));
    }
}
