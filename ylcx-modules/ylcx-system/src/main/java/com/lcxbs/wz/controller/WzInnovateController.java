package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzInnovate;
import com.lcxbs.wz.model.WzScientific;
import com.lcxbs.wz.service.WzInnovateService;
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
@RequestMapping("/wzInnovate")
@Api(value = "/wzInnovate", tags = "技术创新")
public class WzInnovateController extends BaseController {
    @Resource
    private WzInnovateService wzInnovateService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询技术创新")
    @JSON(type = WzInnovate.class,include = "nid,name,content,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzInnovateService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取技术创新列表")
    @JSON(type = WzInnovate.class,include = "nid,name,link,content,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzInnovate wzInnovate=new WzInnovate();
        wzInnovate.setSortField("sortNum");
        wzInnovate.setSortOrder("asc");
        wzInnovate.setDeleteFlag(0L);
        wzInnovateService.setupOrderByAndGroupBy(wzInnovate);
        List<WzInnovate> list=this.wzInnovateService.getList(wzInnovate);
        return success(FIND_SUCCESS, list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzInnovate:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzInnovate wzInnovate, HttpServletRequest request) {
        int result = wzInnovateService.insert(wzInnovate);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzInnovate:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzInnovate wzInnovate,HttpServletRequest request) {
        return success(UPDATE_SUCCESS, wzInnovateService.updateSelective(wzInnovate));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzInnovate:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzInnovateService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('wzInnovate:find_list',true,#request)")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzInnovate.class,include = "nid,name,content,sortNum")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzInnovate wzInnovate,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzInnovate.getPageNum(), wzInnovate.getPageSize());
        wzInnovateService.setupOrderByAndGroupBy(wzInnovate);
        PageInfo<WzInnovate> list = wzInnovateService.getListByPage(wzInnovate);
        List<WzInnovate> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
