package com.lcxbs.sys.controller;

import com.lcxbs.core.BaseController;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.sys.model.SysUserRole;
import com.lcxbs.sys.service.SysUserRoleService;
import com.lcxbs.sys.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.utils.UUIDGenerator;
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
 * <p>Title: SysUserController.java</p>
 * <p>Description:用户信息控制器</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:48:49
 * @version V1.0
 */
@RestController
@RequestMapping("/sysUser")
@Api(value = "/sysUser", tags = "用户信息")
public class SysUserController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('sysUser:save',true,#request)")
    @ApiOperation("添加用户信息")
    @ApiOperationSupport(includeParameters = {"sysUser.nid","sysUser.userId","sysUser.userName","sysUser.userLogin","sysUser.userPhone","sysUser.userType","sysUser.userRelId","sysUser.certificateType","sysUser.certificateId","sysUser.userEmail","sysUser.userGender","sysUser.avatar","sysUser.mainPost","sysUser.politicsStatus","sysUser.qualifications","sysUser.professionalTitle","sysUser.workType","sysUser.workDuty","sysUser.sortNum","sysUser.disableFlag","sysUser.revision","sysUser.createdTime","sysUser.updatedTime","sysUser.tenantId"})
    public RespMsgBean save(@Validated(value = Default.class) @RequestBody SysUser sysUser,HttpServletRequest request) {
        sysUser.setUserId(UUIDGenerator.generate());
        int result = sysUserService.insert(sysUser);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("nid", sysUser.getNid());
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('sysUser:update',true,#request)")
    @ApiOperation("编辑用户信息")
    @ApiOperationSupport(includeParameters = {"sysUser.nid","sysUser.userId","sysUser.userName","sysUser.userLogin","sysUser.userPhone","sysUser.userType","sysUser.userRelId","sysUser.certificateType","sysUser.certificateId","sysUser.userEmail","sysUser.userGender","sysUser.avatar","sysUser.mainPost","sysUser.politicsStatus","sysUser.qualifications","sysUser.professionalTitle","sysUser.workType","sysUser.workDuty","sysUser.sortNum","sysUser.disableFlag","sysUser.createdTime","sysUser.updatedTime","sysUser.tenantId"})
    public RespMsgBean update(@Validated(value = UpdateGroup.class) @RequestBody SysUser sysUser,HttpServletRequest request) {
        SysUser model=new SysUser();
        model.setNid(sysUser.getNid());
        model.setUserId(sysUser.getUserId());
        model.setUserName(sysUser.getUserName());
        model.setUserPhone(sysUser.getUserPhone());
        model.setUserType(sysUser.getUserType());
        model.setUserEmail(sysUser.getUserEmail());
        model.setUserGender(sysUser.getUserGender());
        model.setAvatar(sysUser.getAvatar());
        model.setMainPost(sysUser.getMainPost());
        model.setPoliticsStatus(sysUser.getPoliticsStatus());
        model.setQualifications(sysUser.getQualifications());
        model.setProfessionalTitle(sysUser.getProfessionalTitle());
        model.setWorkType(sysUser.getWorkType());
        model.setWorkDuty(sysUser.getWorkDuty());
        model.setSortNum(sysUser.getSortNum());
        model.setDisableFlag(sysUser.getDisableFlag());
        return success(UPDATE_SUCCESS, sysUserService.updateSelective(model));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('sysUser:delete',true,#request)")
    @ApiOperation("按id删除用户信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, sysUserService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除用户信息")
    @PreAuthorize("@ps.hasAuthority('sysUser:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids,HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, sysUserService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询用户信息")
    @PreAuthorize("@ps.hasAuthority('sysUser:find_id',true,#request)")
    @JSON(type = SysUser.class,include = "nid,userId,userName,userLogin,userPhone,userType,userRelId,userEmail,certificateType,certificateId,userGender,avatar,mainPost,politicsStatus,qualifications,professionalTitle,workType,workDuty,sortNum,disableFlag")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysUserService.getModelByKey(id));
    }

    @PostMapping("/find_list")
    @ApiOperation("获取用户信息列表")
    @PreAuthorize("@ps.hasAuthority('sysUser:find_list',true,#request)")
    @ApiOperationSupport(includeParameters = {"sysUser.nid","sysUser.userId","sysUser.userName","sysUser.userLogin","sysUser.userPhone","sysUser.userType","sysUser.userRelId","sysUser.certificateType","sysUser.certificateId","sysUser.userEmail","sysUser.userGender","sysUser.avatar","sysUser.mainPost","sysUser.politicsStatus","sysUser.qualifications","sysUser.professionalTitle","sysUser.workType","sysUser.workDuty","sysUser.sortNum","sysUser.disableFlag","sysUser.createdTime","sysUser.updatedTime"})
    @JSON(type = SysUser.class,include = "nid,userId,userName,userLogin,userPhone,userType,userRelId,certificateType,certificateId,userEmail,userGender,avatar,mainPost,politicsStatus,qualifications,professionalTitle,workType,workDuty,sortNum,disableFlag,createdTime,children")
    public RespMsgBean findList(@RequestBody(required = false) SysUser sysUser,HttpServletRequest request) throws Exception{
        if(StringUtils.isEmpty(sysUser.getSortField())) {
            sysUser.setSortField("sortNum");
        }
        if(StringUtils.isEmpty(sysUser.getSortOrder())) {
            sysUser.setSortOrder("asc");
        }
        sysUserService.setupOrderByAndGroupBy(sysUser);
        sysUser.setDeleteFlag(0L);
        List<SysUser> list=this.sysUserService.getList(sysUser);
        List<SysUser> treeList = TreeUtil.buildTree(list);
        return success(FIND_SUCCESS, treeList);
    }
	
    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('sysUser:find_list',true,#request)")
    @ApiOperation("获取用户信息列表（分页）")
    @ApiOperationSupport(includeParameters = {"sysUser.nid","sysUser.userId","sysUser.userName","sysUser.userLogin","sysUser.userPhone","sysUser.userType","sysUser.userRelId","sysUser.certificateType","sysUser.certificateId","sysUser.userEmail","sysUser.userGender","sysUser.avatar","sysUser.mainPost","sysUser.politicsStatus","sysUser.qualifications","sysUser.professionalTitle","sysUser.workType","sysUser.workDuty","sysUser.sortNum","sysUser.disableFlag","sysUser.createdTime","sysUser.updatedTime","sysUser.tenantId"})
    @JSON(type = SysUser.class,include = "nid,userId,userName,userLogin,userPhone,userType,userRelId,certificateType,certificateId,userEmail,userGender,avatar,mainPost,politicsStatus,qualifications,professionalTitle,workType,workDuty,sortNum,disableFlag,createdTime,updatedTime,tenantId,children,treeId,parentTreeId")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) SysUser sysUser,HttpServletRequest request) throws  Exception {
        PageHelper.startPage(sysUser.getPageNum(), sysUser.getPageSize());
        if (StringUtils.isEmpty(sysUser.getSortField())) {
            sysUser.setSortField("sortNum");
        }
        if (StringUtils.isEmpty(sysUser.getSortOrder())) {
            sysUser.setSortOrder("asc");
        }
        sysUser.setDeleteFlag(0L);
        sysUserService.setupOrderByAndGroupBy(sysUser);
        PageInfo<SysUser> list = sysUserService.getListByPage(sysUser);
        return success(FIND_SUCCESS, list);
    }
    
    @GetMapping("/disable")
    @PreAuthorize("@ps.hasAuthority('sysUser:disable',true,#request)")
    @ApiOperation("按id启用或禁用用户信息")
    public RespMsgBean disable(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,
                               @ApiParam(value = "1启用，0禁用", required = true,allowableValues = "0,1") @RequestParam("flag") Long flag,
                               HttpServletRequest request) {
        SysUser model=new SysUser(id);
        model.setDisableFlag(flag);
        return success(SUCCESS, this.sysUserService.updateSelective(model));
    }


    @GetMapping("/getUserInfo")
    @ApiOperation("获取当前用户信息")
    @JSON(type = SysUser.class,include = "nid,userId,userName,userLogin,userPhone,userType,userRelId,userEmail,certificateType,certificateId,userGender,avatar,mainPost,politicsStatus,qualifications,professionalTitle,workType,workDuty,sortNum,disableFlag")
    public RespMsgBean getUserInfo(HttpServletRequest request) {
        return success(FIND_SUCCESS, this.sysUserService.getCurrentUser());
    }
}
