package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzNotice;
import com.lcxbs.wz.model.WzPersonnelDynamic;
import com.lcxbs.wz.service.WzPersonnelDynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wzPersonnelDynamic")
@Api(value = "/wzPersonnelDynamic", tags = "人才动态")
public class WzPersonnelDynamicController extends BaseController {
    @Resource
    private WzPersonnelDynamicService wzPersonnelDynamicService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询人才动态")
    @JSON(type = WzPersonnelDynamic.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzPersonnelDynamicService.getModelByKey(id));
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelDynamic:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzPersonnelDynamic dynamic, HttpServletRequest request) {
        int result = wzPersonnelDynamicService.insert(dynamic);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelDynamic:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzPersonnelDynamic dynamic,HttpServletRequest request) {
        return success(UPDATE_SUCCESS, wzPersonnelDynamicService.updateSelective(dynamic));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelDynamic:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzPersonnelDynamicService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzPersonnelDynamic.class,include = "nid,title,content,releaseTime,source")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzPersonnelDynamic dynamic,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(dynamic.getPageNum(), dynamic.getPageSize());
        wzPersonnelDynamicService.setupOrderByAndGroupBy(dynamic);
        PageInfo<WzPersonnelDynamic> list = wzPersonnelDynamicService.getListByPage(dynamic);
        List<WzPersonnelDynamic> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
