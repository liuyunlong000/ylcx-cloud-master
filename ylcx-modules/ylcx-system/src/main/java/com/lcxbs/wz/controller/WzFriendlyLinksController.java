package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzFriendlyLinks;
import com.lcxbs.wz.model.WzPersonnelOverview;
import com.lcxbs.wz.service.WzFriendlyLinksService;
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
@RequestMapping("/wzFriendlyLinks")
@Api(value = "/wzFriendlyLinks", tags = "友情链接")
public class WzFriendlyLinksController extends BaseController {
    @Resource
    private WzFriendlyLinksService service;

    @GetMapping("/find_id")
    @ApiOperation("按id查询链接")
    @JSON(type = WzFriendlyLinks.class,include = "nid,name,content,sortNum,type")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.service.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取链接列表")
    @JSON(type = WzFriendlyLinks.class,include = "nid,name,content,sortNum,type")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzFriendlyLinks entity1=new WzFriendlyLinks();
        entity1.setSortField("sortNum");
        entity1.setSortOrder("asc");
        entity1.setDeleteFlag(0L);
        service.setupOrderByAndGroupBy(entity1);
        List<WzFriendlyLinks> list=this.service.getList(entity1);
        Map<String,List<WzFriendlyLinks>> map =new LinkedHashMap<>();
        for (WzFriendlyLinks item :list) {
            if(!map.containsKey(item.getType())) {
                map.put(item.getType(), new ArrayList<WzFriendlyLinks>());
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
    public RespMsgBean save(@RequestBody WzFriendlyLinks wzFriendlyLinks, HttpServletRequest request) {
        WzFriendlyLinks entity=new WzFriendlyLinks();
        entity.getMap().put("orderBy","ORDER BY A.SORT_NUM ASC");
        List<WzFriendlyLinks> list=service.getList(entity);
        Long sort=wzFriendlyLinks.getSortNum();
        Boolean copy=true;
        int result=0;
        for(int i=0;i<list.size();i++){
            if( sort!=null && i+1>=sort){
                list.get(i).setSortNum(2L+i);
                if(copy){
                    copy=false;
                    wzFriendlyLinks.setSortNum(1L+i);
                    result = service.insert(wzFriendlyLinks);
                }
            }else {
                list.get(i).setSortNum(1L+i);
            }
            service.updateSelective(list.get(i));
        }
        if(copy){
            wzFriendlyLinks.setSortNum(1L+list.size());
            result = service.insert(wzFriendlyLinks);
        }
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzFriendlyLinks:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzFriendlyLinks wzFriendlyLinks,HttpServletRequest request) {
        WzFriendlyLinks entity=new WzFriendlyLinks();
        entity.getMap().put("orderBy","where A.NID<>"+wzFriendlyLinks.getNid()+" ORDER BY A.SORT_NUM ASC");
        List<WzFriendlyLinks> list=service.getList(entity);
        Long sort=wzFriendlyLinks.getSortNum();
        Boolean copy=true;
        int result=0;
        for(int i=0;i<list.size();i++){
            if( sort!=null && i+1>=sort){
                list.get(i).setSortNum(2L+i);
                if(copy){
                    copy=false;
                    wzFriendlyLinks.setSortNum(1L+i);
                    result = service.updateSelective(wzFriendlyLinks);
                }
            }else {
                list.get(i).setSortNum(1L+i);
            }
            service.updateSelective(list.get(i));
        }
        if(copy){
            wzFriendlyLinks.setSortNum(1L+list.size());
            result = service.updateSelective(wzFriendlyLinks);
        }
        return success(UPDATE_SUCCESS, result);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzFriendlyLinks:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        int result =service.delete(id);
        WzFriendlyLinks entity=new WzFriendlyLinks();
        entity.getMap().put("orderBy","ORDER BY A.SORT_NUM ASC");
        List<WzFriendlyLinks> list=service.getList(entity);
        for(int i=0;i<list.size();i++){
            list.get(i).setSortNum(1L+i);
            service.updateSelective(list.get(i));
        }
        return success(DELETE_SUCCESS, result);
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('wzFriendlyLinks:find_list',true,#request)")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzFriendlyLinks.class,include = "nid,name,content,type,sortNum")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzFriendlyLinks wzFriendlyLinks,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzFriendlyLinks.getPageNum(), wzFriendlyLinks.getPageSize());
        service.setupOrderByAndGroupBy(wzFriendlyLinks);
        PageInfo<WzFriendlyLinks> list = service.getListByPage(wzFriendlyLinks);
        List<WzFriendlyLinks> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
