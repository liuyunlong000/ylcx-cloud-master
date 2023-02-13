package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzPersonnelRecruit;
import com.lcxbs.wz.model.WzScientific;
import com.lcxbs.wz.model.WzScientificDynamic;
import com.lcxbs.wz.service.WzScientificDynamicService;
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
@RequestMapping("/wzScientificDynamic")
@Api(value = "/wzScientificDynamic", tags = "科研动态")
public class WzScientificDynamicController extends BaseController {
    @Resource
    private WzScientificDynamicService wzScientificDynamicService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询科研动态")
    @JSON(type = WzScientificDynamic.class,include = "nid,title,content,top,releaseTime,source")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzScientificDynamicService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取科研动态列表")
    @JSON(type = WzScientificDynamic.class,include = "nid,title,content,top,releaseTime,source")
    public RespMsgBean findList(@ApiParam(value = "count", required = true) @RequestParam("count") Integer count,HttpServletRequest request) throws Exception{
        WzScientificDynamic entity=new WzScientificDynamic();
        entity.setDeleteFlag(0L);
        PageHelper.startPage(1, count);
        entity.getMap().put("orderBy","ORDER BY TOP DESC,RELEASE_TIME DESC");
        PageInfo<WzScientificDynamic> list = wzScientificDynamicService.getListByPage(entity);
        return success(FIND_SUCCESS,list.getList());
    }
    @PostMapping("/find_list_home")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzScientificDynamic.class,include = "nid,title,content,top,releaseTime,source")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListHome(@RequestBody(required = false) WzScientificDynamic dynamic,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(dynamic.getPageNum(), dynamic.getPageSize());
        dynamic.getMap().put("orderBy","ORDER BY TOP DESC,RELEASE_TIME DESC");
        PageInfo<WzScientificDynamic> list = wzScientificDynamicService.getListByPage(dynamic);
        List<WzScientificDynamic> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzScientificDynamic:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzScientificDynamic dynamic, HttpServletRequest request) {
        if(!"是".equals(dynamic.getTop())){
            dynamic.setTop(null);
        }
        int result = wzScientificDynamicService.insert(dynamic);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzScientificDynamic:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzScientificDynamic dynamic,HttpServletRequest request) {
        if(!"是".equals(dynamic.getTop())){
            dynamic.setTop(null);
        }
        return success(UPDATE_SUCCESS, wzScientificDynamicService.updateSelective(dynamic));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzScientificDynamic:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzScientificDynamicService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzScientificDynamic.class,include = "nid,title,content,top,releaseTime,source")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzScientificDynamic dynamic,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(dynamic.getPageNum(), dynamic.getPageSize());
        dynamic.getMap().put("orderBy","ORDER BY TOP DESC,RELEASE_TIME DESC");
        PageInfo<WzScientificDynamic> list = wzScientificDynamicService.getListByPage(dynamic);
        List<WzScientificDynamic> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
