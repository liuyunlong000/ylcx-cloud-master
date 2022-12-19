package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysDepartment;
import com.lcxbs.sys.service.SysDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.utils.UUIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
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
 * <p>Title: SysDepartmentController.java</p>
 * <p>Description:部门信息控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-2-26 22:48:44
 * @version V1.0
 */
@RestController
@RequestMapping("/sysDepartment")
@Api(value = "/sysDepartment", tags = "部门信息")
public class SysDepartmentController extends BaseController {

    @Resource
    private SysDepartmentService sysDepartmentService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysDepartment:save',true,#request)")
    @ApiOperation("添加部门信息")
    @ApiOperationSupport(includeParameters = {"sysDepartment.nid","sysDepartment.deptId","sysDepartment.parentId","sysDepartment.deptName","sysDepartment.shortName","sysDepartment.deptPrincipal","sysDepartment.searchStr","sysDepartment.remark","sysDepartment.levelNum","sysDepartment.sortNum","sysDepartment.disableFlag","sysDepartment.deleteFlag","sysDepartment.revision","sysDepartment.createdBy","sysDepartment.createdTime","sysDepartment.updatedBy","sysDepartment.updatedTime","sysDepartment.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysDepartment sysDepartment,HttpServletRequest request) {
        sysDepartment.setParentId(StringUtils.isBlank(sysDepartment.getParentId())?"0":sysDepartment.getParentId());
        sysDepartment.setDeptId(UUIDGenerator.generate());
        int result = sysDepartmentService.insert(sysDepartment);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysDepartment.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysDepartment:update',true,#request)")
    @ApiOperation("编辑部门信息")
    @ApiOperationSupport(includeParameters = {"sysDepartment.nid","sysDepartment.parentId","sysDepartment.deptName","sysDepartment.shortName","sysDepartment.deptPrincipal","sysDepartment.remark","sysDepartment.sortNum","sysDepartment.disableFlag"})
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysDepartment sysDepartment,HttpServletRequest request) {
        SysDepartment model=new SysDepartment();
        model.setNid(sysDepartment.getNid());
        model.setDeptId(sysDepartment.getDeptId());
        model.setParentId(StringUtils.isBlank(sysDepartment.getParentId())?"0":sysDepartment.getParentId());
        model.setDeptName(sysDepartment.getDeptName());
        model.setShortName(sysDepartment.getShortName());
        model.setDeptPrincipal(sysDepartment.getDeptPrincipal());
        model.setRemark(sysDepartment.getRemark());
        model.setSortNum(sysDepartment.getSortNum());
        model.setDisableFlag(sysDepartment.getDisableFlag());
        return success(UPDATE_SUCCESS, sysDepartmentService.updateSelective(model));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysDepartment:delete',true,#request)")
    @ApiOperation("按id删除部门信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysDepartmentService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除部门信息")
    @PreAuthorize("@ps.hasAuthority('sysDepartment:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysDepartmentService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询部门信息")
    @PreAuthorize("@ps.hasAuthority('sysDepartment:find_id',true,#request)")
    @JSON(type = SysDepartment.class,include = "nid,deptId,parentId,deptName,shortName,deptPrincipal,remark,sortNum,disableFlag")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysDepartmentService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取部门信息列表")
    @PreAuthorize("@ps.hasAuthority('sysDepartment:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysDepartment.nid","sysDepartment.deptId","sysDepartment.parentId","sysDepartment.deptName","sysDepartment.shortName","sysDepartment.deptPrincipal","sysDepartment.searchStr","sysDepartment.remark","sysDepartment.levelNum","sysDepartment.sortNum","sysDepartment.disableFlag","sysDepartment.revision","sysDepartment.createdBy","sysDepartment.createdTime","sysDepartment.updatedBy","sysDepartment.updatedTime","sysDepartment.tenantId"})
    @JSON(type = SysDepartment.class,include = "nid,deptId,parentId,deptName,shortName,deptPrincipal,searchStr,remark,levelNum,sortNum,disableFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children")
    public RespMsgBean findList(@RequestBody(required = false) SysDepartment sysDepartment,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysDepartment.getSortField())) {
            sysDepartment.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysDepartment.getSortOrder())) {
            sysDepartment.setSortOrder("asc");
        }
        sysDepartmentService.setupOrderByAndGroupBy(sysDepartment);
        sysDepartment.setDeleteFlag(0L);
        List<SysDepartment> list=this.sysDepartmentService.getList(sysDepartment);
        List<SysDepartment> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysDepartment:find_list',true,#request)")
    @ApiOperation("获取部门信息列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysDepartment.nid","sysDepartment.deptId","sysDepartment.parentId","sysDepartment.deptName","sysDepartment.shortName","sysDepartment.deptPrincipal","sysDepartment.searchStr","sysDepartment.remark","sysDepartment.levelNum","sysDepartment.sortNum","sysDepartment.disableFlag","sysDepartment.deleteFlag","sysDepartment.revision","sysDepartment.createdBy","sysDepartment.createdTime","sysDepartment.updatedBy","sysDepartment.updatedTime","sysDepartment.tenantId"})
    @JSON(type = SysDepartment.class,include = "nid,deptId,parentId,deptName,shortName,deptPrincipal,searchStr,remark,levelNum,sortNum,disableFlag,deleteFlag,revision,createdBy,createdTime,updatedBy,updatedTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysDepartment sysDepartment,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(sysDepartment.getPageNum(), sysDepartment.getPageSize());
        if(StringUtils.isEmpty(sysDepartment.getSortField())) {
            sysDepartment.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysDepartment.getSortOrder())) {
            sysDepartment.setSortOrder("asc");
        }
	   sysDepartmentService.setupOrderByAndGroupBy(sysDepartment);
        sysDepartment.setDeleteFlag(0L);
        PageInfo<SysDepartment> list = sysDepartmentService.getListByPage(sysDepartment);
        List<SysDepartment> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
    
    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysDepartment:disable',true,#request)")
    @ApiOperation("按id启用或禁用部门信息")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysDepartment model=new SysDepartment(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysDepartmentService.updateSelective(model));
    }
}
