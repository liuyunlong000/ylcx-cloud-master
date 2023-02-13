package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzLeader;
import com.lcxbs.wz.model.WzScientific;
import com.lcxbs.wz.service.WzScientificService;
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
@RequestMapping("/wzScientific")
@Api(value = "/wzScientific", tags = "科学研究")
public class WzScientificController extends BaseController {
    @Resource
    private WzScientificService wzScientificService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询科学研究")
    @JSON(type = WzScientific.class,include = "nid,name,content,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzScientificService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取科学研究列表")
    @JSON(type = WzScientific.class,include = "nid,name,link,content,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzScientific entity=new WzScientific();
        entity.setSortField("sortNum");
        entity.setSortOrder("asc");
        entity.setDeleteFlag(0L);
        wzScientificService.setupOrderByAndGroupBy(entity);
        List<WzScientific> list=this.wzScientificService.getList(entity);
        return success(FIND_SUCCESS, list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzScientific:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzScientific wzScientific, HttpServletRequest request) {
        WzScientific entity=new WzScientific();
        entity.getMap().put("orderBy","ORDER BY A.SORT_NUM ASC");
        List<WzScientific> list=wzScientificService.getList(entity);
        Long sort=wzScientific.getSortNum();
        Boolean copy=true;
        int result=0;
        for(int i=0;i<list.size();i++){
            if( sort!=null && i+1>=sort){
                list.get(i).setSortNum(2L+i);
                if(copy){
                    copy=false;
                    wzScientific.setSortNum(1L+i);
                    result = wzScientificService.insert(wzScientific);
                }
            }else {
                list.get(i).setSortNum(1L+i);
            }
            wzScientificService.updateSelective(list.get(i));
        }
        if(copy){
            wzScientific.setSortNum(1L+list.size());
            result = wzScientificService.insert(wzScientific);
        }
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }

    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzScientific:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzScientific wzScientific,HttpServletRequest request) {
        WzScientific entity=new WzScientific();
        entity.getMap().put("orderBy","where A.NID<>"+wzScientific.getNid()+" ORDER BY A.SORT_NUM ASC");
        List<WzScientific> list=wzScientificService.getList(entity);
        Long sort=wzScientific.getSortNum();
        Boolean copy=true;
        int result=0;
        for(int i=0;i<list.size();i++){
            if( sort!=null && i+1>=sort){
                list.get(i).setSortNum(2L+i);
                if(copy){
                    copy=false;
                    wzScientific.setSortNum(1L+i);
                    result = wzScientificService.updateSelective(wzScientific);
                }
            }else {
                list.get(i).setSortNum(1L+i);
            }
            wzScientificService.updateSelective(list.get(i));
        }
        if(copy){
            wzScientific.setSortNum(1L+list.size());
            result = wzScientificService.updateSelective(wzScientific);
        }
        return success(UPDATE_SUCCESS, result);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzScientific:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        int result =wzScientificService.delete(id);
        WzScientific entity=new WzScientific();
        entity.getMap().put("orderBy","ORDER BY A.SORT_NUM ASC");
        List<WzScientific> list=wzScientificService.getList(entity);
        for(int i=0;i<list.size();i++){
            list.get(i).setSortNum(1L+i);
            wzScientificService.updateSelective(list.get(i));
        }
        return success(DELETE_SUCCESS, result);
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('wzScientific:find_list',true,#request)")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzScientific.class,include = "nid,name,content,sortNum")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzScientific wzScientific,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzScientific.getPageNum(), wzScientific.getPageSize());
        wzScientificService.setupOrderByAndGroupBy(wzScientific);
        PageInfo<WzScientific> list = wzScientificService.getListByPage(wzScientific);
        List<WzScientific> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
