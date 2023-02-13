package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzPersonnelDynamic;
import com.lcxbs.wz.model.WzPersonnelRecruit;
import com.lcxbs.wz.model.WzScientificDynamic;
import com.lcxbs.wz.service.WzPersonnelRecruitService;
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
@RequestMapping("/wzPersonnelRecruit")
@Api(value = "/wzPersonnelRecruit", tags = "人才招聘")
public class WzPersonnelRecruitController extends BaseController {
    @Resource
    private WzPersonnelRecruitService wzPersonnelRecruitService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询人才招聘")
    @JSON(type = WzPersonnelRecruit.class,include = "nid,title,content,releaseTime,source,top")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzPersonnelRecruitService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取人才招聘列表")
    @JSON(type = WzPersonnelRecruit.class,include = "nid,title,content,releaseTime,source,top")
    public RespMsgBean findList(@ApiParam(value = "count", required = true) @RequestParam("count") Integer count,HttpServletRequest request) throws Exception{
        WzPersonnelRecruit entity=new WzPersonnelRecruit();
        entity.setSortField("releaseTime");
        entity.setSortOrder("desc");
        entity.setDeleteFlag(0L);
        PageHelper.startPage(1, count);
        wzPersonnelRecruitService.setupOrderByAndGroupBy(entity);
        PageInfo<WzPersonnelRecruit> list = wzPersonnelRecruitService.getListByPage(entity);
        return success(FIND_SUCCESS,list.getList());
    }
    @PostMapping("/find_list_home")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzPersonnelRecruit.class,include = "nid,title,content,releaseTime,source,top")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListHome(@RequestBody(required = false) WzPersonnelRecruit recruit,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(recruit.getPageNum(), recruit.getPageSize());
        recruit.getMap().put("orderBy","ORDER BY TOP DESC,RELEASE_TIME DESC");
        PageInfo<WzPersonnelRecruit> list = wzPersonnelRecruitService.getListByPage(recruit);
        List<WzPersonnelRecruit> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelRecruit:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzPersonnelRecruit recruit, HttpServletRequest request) {
        if(!"是".equals(recruit.getTop())){
            recruit.setTop(null);
        }
        int result = wzPersonnelRecruitService.insert(recruit);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelRecruit:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzPersonnelRecruit recruit,HttpServletRequest request) {
        if(!"是".equals(recruit.getTop())){
            recruit.setTop(null);
        }
        return success(UPDATE_SUCCESS, wzPersonnelRecruitService.updateSelective(recruit));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelRecruit:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzPersonnelRecruitService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzPersonnelRecruit.class,include = "nid,title,content,releaseTime,source,top")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzPersonnelRecruit recruit,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(recruit.getPageNum(), recruit.getPageSize());
        recruit.getMap().put("orderBy","ORDER BY TOP DESC,RELEASE_TIME DESC");
        PageInfo<WzPersonnelRecruit> list = wzPersonnelRecruitService.getListByPage(recruit);
        List<WzPersonnelRecruit> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
