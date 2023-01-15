package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzLeader;
import com.lcxbs.wz.model.WzPersonnelOverview;
import com.lcxbs.wz.service.WzLeaderService;
import com.lcxbs.wz.service.WzPersonnelOverviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/wzPersonnelOverview")
@Api(value = "/wzPersonnelOverview", tags = "人才概况")
public class WzPersonnelOverviewController extends BaseController {
    @Resource
    private WzPersonnelOverviewService wzPersonnelOverviewService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询人才信息")
    @JSON(type = WzPersonnelOverview.class,include = "nid,name,post,photo,resume,content,releaseTime,source,sortNum,type")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzPersonnelOverviewService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取人才列表")
    @JSON(type = WzPersonnelOverview.class,include = "nid,name,post,photo,resume,content,releaseTime,source,sortNum,createdTime")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzPersonnelOverview entity1=new WzPersonnelOverview();
        entity1.setSortField("sortNum");
        entity1.setSortOrder("asc");
        entity1.setDeleteFlag(0L);
        wzPersonnelOverviewService.setupOrderByAndGroupBy(entity1);
        List<WzPersonnelOverview> list=this.wzPersonnelOverviewService.getList(entity1);
        Map<String,List<WzPersonnelOverview>> map =new LinkedHashMap<>();
        for (WzPersonnelOverview item :list) {
            if(!map.containsKey(item.getType())) {
                map.put(item.getType(), new ArrayList<WzPersonnelOverview>());
            }
            map.get(item.getType()).add(item);
        }
        List<Object> _list =new ArrayList<>();
        for(String key:map.keySet()){
            Map<String,Object> data=new HashMap<>();
            data.put("type",key);
            data.put("list",map.get(key));
            _list.add(data);
        }
        return success(FIND_SUCCESS, _list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelOverview:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzPersonnelOverview wzPersonnelOverview, HttpServletRequest request) {
        int result = wzPersonnelOverviewService.insert(wzPersonnelOverview);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelOverview:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzPersonnelOverview wzPersonnelOverview,HttpServletRequest request) {
        return success(UPDATE_SUCCESS, wzPersonnelOverviewService.updateSelective(wzPersonnelOverview));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelOverview:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzPersonnelOverviewService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('wzPersonnelOverview:find_list',true,#request)")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzPersonnelOverview.class,include = "nid,name,post,photo,resume,content,type,releaseTime,source,sortNum")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzPersonnelOverview wzPersonnelOverview,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzPersonnelOverview.getPageNum(), wzPersonnelOverview.getPageSize());
        wzPersonnelOverviewService.setupOrderByAndGroupBy(wzPersonnelOverview);
        PageInfo<WzPersonnelOverview> list = wzPersonnelOverviewService.getListByPage(wzPersonnelOverview);
        List<WzPersonnelOverview> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
