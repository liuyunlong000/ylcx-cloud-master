package com.lcxbs.wz.controller;


import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzPersonnelDynamic;
import com.lcxbs.wz.service.WzPersonnelDynamicService;
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
@RequestMapping("/wzPersonnelDynamic")
@Api(value = "/wzPersonnelDynamic", tags = "人才动态")
public class WzPersonnelDynamicController extends BaseController {
    @Resource
    private WzPersonnelDynamicService wzPersonnelDynamicService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询人才动态")
    @JSON(type = WzPersonnelDynamic.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzPersonnelDynamicService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取人才动态列表")
    @JSON(type = WzPersonnelDynamic.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findList(@ApiParam(value = "count", required = true) @RequestParam("count") Integer count,HttpServletRequest request) throws Exception{
        WzPersonnelDynamic entity=new WzPersonnelDynamic();
        entity.setSortField("RELEASE_TIME");
        entity.setSortOrder("desc");
        entity.setDeleteFlag(0L);
        entity.setPageNum(1);
        entity.setPageSize(count);
        PageInfo<WzPersonnelDynamic> page=this.wzPersonnelDynamicService.getListByPage(entity);
        return success(FIND_SUCCESS, page.getList());
    }
}
