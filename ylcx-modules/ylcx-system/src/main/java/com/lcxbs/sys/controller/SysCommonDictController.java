package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysCommonDict;
import com.lcxbs.sys.service.SysCommonDictService;
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
 * <p>Title: SysCommonDictController.java</p>
 * <p>Description:数据字典控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:49:26
 * @version V1.0
 */
@RestController
@RequestMapping("/sysCommonDict")
@Api(value = "/sysCommonDict", tags = "数据字典")
public class SysCommonDictController extends BaseController {

    @Resource
    private SysCommonDictService sysCommonDictService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysCommonDict:save',true,#request)")
    @ApiOperation("添加数据字典")
    @ApiOperationSupport(includeParameters = {"sysCommonDict.nid","sysCommonDict.dictName","sysCommonDict.dictCode","sysCommonDict.dictValue","sysCommonDict.parentId","sysCommonDict.leafFlag","sysCommonDict.dictLevel","sysCommonDict.dictType","sysCommonDict.fileId","sysCommonDict.dictJson","sysCommonDict.dictRemark","sysCommonDict.cssClass","sysCommonDict.listClass","sysCommonDict.disableFlag","sysCommonDict.deleteFlag","sysCommonDict.sortNum","sysCommonDict.revision","sysCommonDict.createdBy","sysCommonDict.createdTime","sysCommonDict.updatedBy","sysCommonDict.updatedTime","sysCommonDict.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysCommonDict sysCommonDict,HttpServletRequest request) {
        sysCommonDict.setParentId(sysCommonDict.getParentId()==null?0L:sysCommonDict.getParentId());
        this.sysCommonDictService.dictCodeIsExist(sysCommonDict);
        int result = sysCommonDictService.insert(sysCommonDict);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysCommonDict.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysCommonDict:update',true,#request)")
    @ApiOperation("编辑数据字典")
    @ApiOperationSupport(includeParameters = {"sysCommonDict.nid","sysCommonDict.dictName","sysCommonDict.dictCode","sysCommonDict.dictValue","sysCommonDict.parentId","sysCommonDict.leafFlag","sysCommonDict.dictLevel","sysCommonDict.dictType","sysCommonDict.fileId","sysCommonDict.dictJson","sysCommonDict.dictRemark","sysCommonDict.cssClass","sysCommonDict.listClass","sysCommonDict.disableFlag"})
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysCommonDict sysCommonDict,HttpServletRequest request) {
        this.sysCommonDictService.dictCodeIsExist(sysCommonDict);
        SysCommonDict model=new SysCommonDict();
        model.setNid(sysCommonDict.getNid());
        model.setDictName(sysCommonDict.getDictName());
        model.setDictCode(sysCommonDict.getDictCode());
        model.setDictValue(sysCommonDict.getDictValue());
        model.setParentId(sysCommonDict.getParentId());
        model.setLeafFlag(sysCommonDict.getLeafFlag());
        model.setDictLevel(sysCommonDict.getDictLevel());
        model.setDictType(sysCommonDict.getDictType());
        model.setFileId(sysCommonDict.getFileId());
        model.setDictJson(sysCommonDict.getDictJson());
        model.setDictRemark(sysCommonDict.getDictRemark());
        model.setCssClass(sysCommonDict.getCssClass());
        model.setListClass(sysCommonDict.getListClass());
        model.setDisableFlag(sysCommonDict.getDisableFlag());
        model.setSortNum(sysCommonDict.getSortNum());
        return success(UPDATE_SUCCESS, sysCommonDictService.updateSelective(model));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysCommonDict:delete',true,#request)")
    @ApiOperation("按id删除数据字典")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysCommonDictService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除数据字典")
    @PreAuthorize("@ps.hasAuthority('sysCommonDict:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysCommonDictService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询数据字典")
    @PreAuthorize("@ps.hasAuthority('sysCommonDict:find_id',true,#request)")
    @JSON(type = SysCommonDict.class,include = "nid,dictName,dictCode,dictValue,parentId,leafFlag,dictLevel,dictType,fileId,dictJson,dictRemark,cssClass,listClass,disableFlag,deleteFlag,sortNum,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysCommonDictService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取数据字典列表")
    @PreAuthorize("@ps.hasAuthority('sysCommonDict:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysCommonDict.nid","sysCommonDict.dictName","sysCommonDict.dictCode","sysCommonDict.dictValue","sysCommonDict.parentId","sysCommonDict.leafFlag","sysCommonDict.dictLevel","sysCommonDict.dictType","sysCommonDict.fileId","sysCommonDict.dictJson","sysCommonDict.dictRemark","sysCommonDict.cssClass","sysCommonDict.listClass","sysCommonDict.disableFlag","sysCommonDict.deleteFlag","sysCommonDict.sortNum","sysCommonDict.revision","sysCommonDict.createdBy","sysCommonDict.createdTime","sysCommonDict.updatedBy","sysCommonDict.updatedTime","sysCommonDict.tenantId"})
    @JSON(type = SysCommonDict.class,include = "nid,dictName,dictCode,dictValue,parentId,leafFlag,dictLevel,dictType,fileId,dictJson,dictRemark,cssClass,listClass,disableFlag,deleteFlag,sortNum,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children")
    public RespMsgBean findList(@RequestBody(required = false) SysCommonDict sysCommonDict,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysCommonDict.getSortField())) {
            sysCommonDict.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysCommonDict.getSortOrder())) {
            sysCommonDict.setSortOrder("asc");
        }
        sysCommonDictService.setupOrderByAndGroupBy(sysCommonDict);
        sysCommonDict.setDeleteFlag(0L);
        List<SysCommonDict> list=this.sysCommonDictService.getList(sysCommonDict);
        List<SysCommonDict> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysCommonDict:find_list',true,#request)")
    @ApiOperation("获取数据字典列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysCommonDict.nid","sysCommonDict.dictName","sysCommonDict.dictCode","sysCommonDict.dictValue","sysCommonDict.parentId","sysCommonDict.leafFlag","sysCommonDict.dictLevel","sysCommonDict.dictType","sysCommonDict.fileId","sysCommonDict.dictJson","sysCommonDict.dictRemark","sysCommonDict.cssClass","sysCommonDict.listClass","sysCommonDict.disableFlag","sysCommonDict.sortNum","sysCommonDict.createdTime","sysCommonDict.dataCount"})
    @JSON(type = SysCommonDict.class,include = "nid,dictName,dictCode,dictValue,parentId,leafFlag,dictLevel,dictType,fileId,dictJson,dictRemark,cssClass,listClass,disableFlag,sortNum,createdTime,dataCount")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysCommonDict sysCommonDict,HttpServletRequest request) throws  Exception {
        PageHelper.startPage(sysCommonDict.getPageNum(), sysCommonDict.getPageSize());
        if (StringUtils.isEmpty(sysCommonDict.getSortField())) {
            sysCommonDict.setSortField("sortNum");
        }
        if (StringUtils.isEmpty(sysCommonDict.getSortOrder())) {
            sysCommonDict.setSortOrder("asc");
        }
        sysCommonDictService.setupOrderByAndGroupBy(sysCommonDict);
        sysCommonDict.setDeleteFlag(0L);
        PageInfo<SysCommonDict> list = sysCommonDictService.getListByPage(sysCommonDict);
        List<SysCommonDict> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS, list);
    }
    
    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysCommonDict:disable',true,#request)")
    @ApiOperation("按id启用或禁用数据字典")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysCommonDict model=new SysCommonDict(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysCommonDictService.updateSelective(model));
    }

    @PostMapping("/find_map")
    @ApiOperation("获取指定数据字典集合")
    @JSON(type = SysCommonDict.class,include = "nid,dictName,dictCode,dictValue,dictType,dictRemark,cssClass,listClass,sortNum")
    public RespMsgBean findMap(@ApiParam(value = "数据字典编码，多个以英文逗号分隔", required = true) @RequestParam(required = true) String dictCodes) {
        String[] dictCodeList = org.apache.commons.lang.StringUtils.split(dictCodes, ",");
        Map<String, List> map = new HashMap<>();
        for (String dictCode : dictCodeList) {
            SysCommonDict sysCommonDict = new SysCommonDict();
            sysCommonDict.setDictCode(dictCode);
            sysCommonDict.setDictType(3L);//字典项
            sysCommonDict.setDeleteFlag(0L);//未删除
            sysCommonDict.setDisableFlag(1L);//启用
            sysCommonDict.getMap().put("orderBy", " order by SORT_NUM asc");
            List<SysCommonDict> list = this.sysCommonDictService.getList(sysCommonDict);
            if (list != null) {
                map.put(dictCode, list);
            }
        }
        return success(FIND_SUCCESS, map);
    }
}
