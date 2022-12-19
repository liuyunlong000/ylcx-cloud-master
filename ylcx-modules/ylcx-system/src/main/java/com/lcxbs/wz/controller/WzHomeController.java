package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzHome;
import com.lcxbs.wz.service.WzHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wzHome")
@Api(value = "/wzHome", tags = "首页轮播图")
public class WzHomeController extends BaseController {
    @Resource
    private WzHomeService wsHomeService;


    @GetMapping("/find_list")
    @ApiOperation("获取首页轮播图列表")
    @JSON(type = WzHome.class,include = "title,imgUrl,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzHome wzHome=new WzHome();
        wzHome.setSortField("SORT_NUM");
        wzHome.setSortOrder("asc");
        wzHome.setDeleteFlag(0L);//未删除
        List<WzHome> list=this.wsHomeService.getList(wzHome);
        return success(FIND_SUCCESS, list);
    }
}
