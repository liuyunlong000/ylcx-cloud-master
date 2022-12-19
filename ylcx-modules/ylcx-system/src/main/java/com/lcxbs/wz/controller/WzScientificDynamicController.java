package com.lcxbs.wz.controller;


import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzScientific;
import com.lcxbs.wz.model.WzScientificDynamic;
import com.lcxbs.wz.service.WzScientificDynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wzScientificDynamic")
@Api(value = "/wzScientificDynamic", tags = "科研动态")
public class WzScientificDynamicController extends BaseController {
    @Resource
    private WzScientificDynamicService wzScientificDynamicService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询科研动态")
    @JSON(type = WzScientific.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzScientificDynamicService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取科研动态列表")
    @JSON(type = WzScientificDynamic.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findList(@ApiParam(value = "count", required = true) @RequestParam("count") Integer count,HttpServletRequest request) throws Exception{
        WzScientificDynamic entity=new WzScientificDynamic();
        entity.setSortField("RELEASE_TIME");
        entity.setSortOrder("desc");
        entity.setDeleteFlag(0L);//未删除
        entity.setPageNum(1);
        entity.setPageSize(count);
        PageInfo<WzScientificDynamic> page=this.wzScientificDynamicService.getListByPage(entity);
        return success(FIND_SUCCESS, page.getList());
    }
}
