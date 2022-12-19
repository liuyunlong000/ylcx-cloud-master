package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzInnovate;
import com.lcxbs.wz.service.WzInnovateService;
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
@RequestMapping("/wzInnovate")
@Api(value = "/wzInnovate", tags = "技术创新")
public class WzInnovateController extends BaseController {
    @Resource
    private WzInnovateService wzInnovateService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询技术创新")
    @JSON(type = WzInnovate.class,include = "name,content,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzInnovateService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取技术创新列表")
    @JSON(type = WzInnovate.class,include = "nid,name,link,content,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzInnovate wzInnovate=new WzInnovate();
        wzInnovate.setSortField("SORT_NUM");
        wzInnovate.setSortOrder("asc");
        wzInnovate.setDeleteFlag(0L);
        List<WzInnovate> list=this.wzInnovateService.getList(wzInnovate);
        return success(FIND_SUCCESS, list);
    }
}
