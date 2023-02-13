package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzNews;
import com.lcxbs.wz.model.WzNotice;
import com.lcxbs.wz.service.WzNoticeService;
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
@RequestMapping("/wzNotice")
@Api(value = "/wzNotice", tags = "通知公告")
public class WzNoticeController extends BaseController {
    @Resource
    private WzNoticeService wsNoticeService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询通知公告")
    @JSON(type = WzNotice.class,include = "nid,title,content,releaseTime,top,source")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wsNoticeService.getModelByKey(id));
    }
    @PostMapping("/find_list_home")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzNotice.class,include = "nid,title,content,releaseTime,top,source")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListHome(@RequestBody(required = false) WzNotice wsNotice,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wsNotice.getPageNum(), wsNotice.getPageSize());
        wsNotice.getMap().put("orderBy","ORDER BY TOP DESC,RELEASE_TIME DESC");
        PageInfo<WzNotice> list = wsNoticeService.getListByPage(wsNotice);
        List<WzNotice> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzNotice:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzNotice wsNotice, HttpServletRequest request) {
        if(!"是".equals(wsNotice.getTop())){
            wsNotice.setTop(null);
        }
        int result = wsNoticeService.insert(wsNotice);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzNotice:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzNotice wsNotice,HttpServletRequest request) {
        if(!"是".equals(wsNotice.getTop())){
            wsNotice.setTop(null);
        }
        return success(UPDATE_SUCCESS, wsNoticeService.updateSelective(wsNotice));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzNotice:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wsNoticeService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzNotice.class,include = "nid,title,content,releaseTime,top,source")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzNotice wsNotice,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wsNotice.getPageNum(), wsNotice.getPageSize());
        wsNotice.getMap().put("orderBy","ORDER BY TOP DESC,RELEASE_TIME DESC");
        PageInfo<WzNotice> list = wsNoticeService.getListByPage(wsNotice);
        List<WzNotice> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
