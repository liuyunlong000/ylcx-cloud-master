package com.lcxbs.wz.controller;


import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzNews;
import com.lcxbs.wz.service.WzNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wzNews")
@Api(value = "/wzNews", tags = "新闻中心")
public class WzNewsController extends BaseController {
    @Resource
    private WzNewsService wzNewsService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询新闻")
    @JSON(type = WzNews.class,include = "nid,title,photo,resume,content,releaseTime,source")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzNewsService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取新闻列表")
    @JSON(type = WzNews.class,include = "nid,title,photo,resume,content,releaseTime,source")
    public RespMsgBean findList(@ApiParam(value = "count", required = true) @RequestParam("count") Integer count,HttpServletRequest request) throws Exception{
        WzNews wzNews=new WzNews();
        wzNews.setSortField("RELEASE_TIME");
        wzNews.setSortOrder("desc");
        wzNews.setDeleteFlag(0L);//未删除
        wzNews.setPageNum(1);
        wzNews.setPageSize(count);
        PageInfo<WzNews> page=this.wzNewsService.getListByPage(wzNews);
        return success(FIND_SUCCESS, page.getList());
    }
    @GetMapping("/find_list_home")
    @ApiOperation("获取首页新闻列表")
    @JSON(type = WzNews.class,include = "nid,title,photo,resume,content,releaseTime,source")
    public RespMsgBean findListHome(@ApiParam(value = "count", required = true) @RequestParam("count") Integer count,HttpServletRequest request) throws Exception{
        WzNews wzNews=new WzNews();
        wzNews.setSortField("SORT_NUM");
        wzNews.setSortOrder("asc");
        wzNews.setDeleteFlag(0L);//未删除
        wzNews.setPageNum(1);
        wzNews.setPageSize(count);
        PageInfo<WzNews> page=this.wzNewsService.getListByPage(wzNews);
        return success(FIND_SUCCESS, page.getList());
    }
}
