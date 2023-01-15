package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.googlecode.aviator.runtime.type.seq.ArrayCollector;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzLeader;
import com.lcxbs.wz.model.WzNews;
import com.lcxbs.wz.service.WzLeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/wzLeader")
@Api(value = "/wzLeader", tags = "院领导")
public class WzLeaderController extends BaseController {
    @Resource
    private WzLeaderService wzLeaderService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询院领导")
    @JSON(type = WzLeader.class,include = "nid,name,post,photo,resume,content,releaseTime,source,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzLeaderService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取院领导列表")
    @JSON(type = WzLeader.class,include = "nid,name,post,photo,resume,content,releaseTime,source,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzLeader wzLeader=new WzLeader();
        wzLeader.setSortField("sortNum");
        wzLeader.setSortOrder("asc");
        wzLeader.setDeleteFlag(0L);
        wzLeaderService.setupOrderByAndGroupBy(wzLeader);
        List<WzLeader> list=this.wzLeaderService.getList(wzLeader);
        Map<String,List<WzLeader>> map =new LinkedHashMap<>();
        for (WzLeader item :list) {
            if(!map.containsKey(item.getPost())) {
                map.put(item.getPost(), new ArrayList<WzLeader>());
            }
            map.get(item.getPost()).add(item);
        }
        List<Object> _list =new ArrayList<>();
        for(String key:map.keySet()){
            Map<String,Object> data=new HashMap<>();
            data.put("post",key);
            data.put("list",map.get(key));
            _list.add(data);
        }
        return success(FIND_SUCCESS, _list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzLeader:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzLeader wzLeader, HttpServletRequest request) {
        int result = wzLeaderService.insert(wzLeader);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzLeader:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzLeader wzLeader,HttpServletRequest request) {
        return success(UPDATE_SUCCESS, wzLeaderService.updateSelective(wzLeader));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzLeader:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzLeaderService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('wzLeader:find_list',true,#request)")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzLeader.class,include = "nid,name,post,photo,resume,content,releaseTime,source,sortNum")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzLeader wzLeader,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzLeader.getPageNum(), wzLeader.getPageSize());
        wzLeaderService.setupOrderByAndGroupBy(wzLeader);
        PageInfo<WzLeader> list = wzLeaderService.getListByPage(wzLeader);
        List<WzLeader> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
