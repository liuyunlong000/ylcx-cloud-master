package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzBoardOverview;
import com.lcxbs.wz.service.WzBoardOverviewService;
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
@RequestMapping("/wzBoardOverview")
@Api(value = "/wzBoardOverview", tags = "板块概述")
public class WzBoardOverviewController extends BaseController {
    @Resource
    private WzBoardOverviewService wzBoardOverviewService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询板块")
    @JSON(type = WzBoardOverview.class,include = "title,content")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzBoardOverviewService.getModelByKey(id));
    }
}
