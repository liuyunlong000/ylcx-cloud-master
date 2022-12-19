package com.lcxbs.wz.controller;


import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzNotice;
import com.lcxbs.wz.service.WzNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wzNotice")
@Api(value = "/wzNotice", tags = "通知公告")
public class WzNoticeController extends BaseController {
    @Resource
    private WzNoticeService wsNoticeService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询通知公告")
    @JSON(type = WzNotice.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wsNoticeService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取通知公告列表")
    @JSON(type = WzNotice.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findList(@ApiParam(value = "count", required = true) @RequestParam("count") Integer count,HttpServletRequest request) throws Exception{
        WzNotice wsNotice=new WzNotice();
        wsNotice.setSortField("RELEASE_TIME");
        wsNotice.setSortOrder("desc");
        wsNotice.setDeleteFlag(0L);//未删除
        wsNotice.setPageNum(1);
        wsNotice.setPageSize(count);
        PageInfo<WzNotice> page=this.wsNoticeService.getListByPage(wsNotice);
        return success(FIND_SUCCESS, page.getList());
    }
}
