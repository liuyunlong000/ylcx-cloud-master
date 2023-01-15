package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzHome;
import com.lcxbs.wz.model.WzNews;
import com.lcxbs.wz.service.WzNewsService;
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
@RequestMapping("/wzNews")
@Api(value = "/wzNews", tags = "新闻中心")
public class WzNewsController extends BaseController {
    @Resource
    private WzNewsService wzNewsService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询新闻")
    @JSON(type = WzNews.class,include = "nid,title,photo,resume,content,releaseTime,source,home")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzNewsService.getModelByKey(id));
    }
    @GetMapping("/find_list")
    @ApiOperation("获取首页新闻列表")
    @JSON(type = WzNews.class,include = "nid,title,photo,resume,content,releaseTime,source")
    public RespMsgBean findListHome(HttpServletRequest request) throws Exception{
        WzNews wzNews=new WzNews();
        wzNews.setSortField("releaseTime");
        wzNews.setSortOrder("desc");
        wzNewsService.setupOrderByAndGroupBy(wzNews);
        wzNews.setDeleteFlag(0L);//未删除
        wzNews.setHome("是");
        PageInfo<WzNews> page=this.wzNewsService.getListByPage(wzNews);
        return success(FIND_SUCCESS, page.getList());
    }

    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzNews:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzNews wzNews, HttpServletRequest request) {
        int result = wzNewsService.insert(wzNews);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzNews:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzNews wzNews,HttpServletRequest request) {
        return success(UPDATE_SUCCESS, wzNewsService.updateSelective(wzNews));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzNews:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzNewsService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzNews.class,include = "nid,title,photo,resume,content,releaseTime,source,home")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzNews wzNews,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzNews.getPageNum(), wzNews.getPageSize());
        wzNewsService.setupOrderByAndGroupBy(wzNews);
        PageInfo<WzNews> list = wzNewsService.getListByPage(wzNews);
        List<WzNews> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
