package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzInnovate;
import com.lcxbs.wz.model.WzInnovatePlateForm;
import com.lcxbs.wz.model.WzLeader;
import com.lcxbs.wz.model.WzScientific;
import com.lcxbs.wz.service.WzInnovatePlateFormService;
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
@RequestMapping("/wzInnovatePlateForm")
@Api(value = "/wzInnovatePlateForm", tags = "创新平台")
public class WzInnovatePlateFormController extends BaseController {
    @Resource
    private WzInnovatePlateFormService wzInnovatePlateFormService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询技术创新")
    @JSON(type = WzInnovatePlateForm.class,include = "nid,name,content,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzInnovatePlateFormService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取创新平台列表")
    @JSON(type = WzInnovatePlateForm.class,include = "nid,name,link,content,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzInnovatePlateForm entity=new WzInnovatePlateForm();
        entity.setSortField("sortNum");
        entity.setSortOrder("asc");
        entity.setDeleteFlag(0L);
        wzInnovatePlateFormService.setupOrderByAndGroupBy(entity);
        List<WzInnovatePlateForm> list=this.wzInnovatePlateFormService.getList(entity);
        return success(FIND_SUCCESS, list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzInnovatePlateForm:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzInnovatePlateForm wzInnovate, HttpServletRequest request) {
        WzInnovatePlateForm entity=new WzInnovatePlateForm();
        entity.getMap().put("orderBy","ORDER BY A.SORT_NUM ASC");
        List<WzInnovatePlateForm> list=wzInnovatePlateFormService.getList(entity);
        Long sort=wzInnovate.getSortNum();
        Boolean copy=true;
        int result=0;
        for(int i=0;i<list.size();i++){
            if( sort!=null && i+1>=sort){
                list.get(i).setSortNum(2L+i);
                if(copy){
                    copy=false;
                    wzInnovate.setSortNum(1L+i);
                    result = wzInnovatePlateFormService.insert(wzInnovate);
                }
            }else {
                list.get(i).setSortNum(1L+i);
            }
            wzInnovatePlateFormService.updateSelective(list.get(i));
        }
        if(copy){
            wzInnovate.setSortNum(1L+list.size());
            result = wzInnovatePlateFormService.insert(wzInnovate);
        }
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzInnovatePlateForm:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzInnovatePlateForm wzInnovate,HttpServletRequest request) {
        WzInnovatePlateForm entity=new WzInnovatePlateForm();
        entity.getMap().put("orderBy","where A.NID<>"+wzInnovate.getNid()+" ORDER BY A.SORT_NUM ASC");
        List<WzInnovatePlateForm> list=wzInnovatePlateFormService.getList(entity);
        Long sort=wzInnovate.getSortNum();
        Boolean copy=true;
        int result=0;
        for(int i=0;i<list.size();i++){
            if( sort!=null && i+1>=sort){
                list.get(i).setSortNum(2L+i);
                if(copy){
                    copy=false;
                    wzInnovate.setSortNum(1L+i);
                    result = wzInnovatePlateFormService.updateSelective(wzInnovate);
                }
            }else {
                list.get(i).setSortNum(1L+i);
            }
            wzInnovatePlateFormService.updateSelective(list.get(i));
        }
        if(copy){
            wzInnovate.setSortNum(1L+list.size());
            result = wzInnovatePlateFormService.updateSelective(wzInnovate);
        }
        return success(UPDATE_SUCCESS, result);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzInnovatePlateForm:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id, HttpServletRequest request) {
        int result =wzInnovatePlateFormService.delete(id);
        WzInnovatePlateForm entity=new WzInnovatePlateForm();
        entity.getMap().put("orderBy","ORDER BY A.SORT_NUM ASC");
        List<WzInnovatePlateForm> list=wzInnovatePlateFormService.getList(entity);
        for(int i=0;i<list.size();i++){
            list.get(i).setSortNum(1L+i);
            wzInnovatePlateFormService.updateSelective(list.get(i));
        }
        return success(DELETE_SUCCESS, result);
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('wzInnovatePlateForm:find_list',true,#request)")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzInnovatePlateForm.class,include = "nid,name,content,sortNum")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzInnovatePlateForm wzInnovate,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzInnovate.getPageNum(), wzInnovate.getPageSize());
        wzInnovatePlateFormService.setupOrderByAndGroupBy(wzInnovate);
        PageInfo<WzInnovatePlateForm> list = wzInnovatePlateFormService.getListByPage(wzInnovate);
        List<WzInnovatePlateForm> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
