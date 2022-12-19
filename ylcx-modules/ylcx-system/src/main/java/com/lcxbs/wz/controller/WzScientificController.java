package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzScientific;
import com.lcxbs.wz.service.WzScientificService;
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
@RequestMapping("/wzScientific")
@Api(value = "/wzScientific", tags = "科学研究")
public class WzScientificController extends BaseController {
    @Resource
    private WzScientificService wzScientificService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询科学研究")
    @JSON(type = WzScientific.class,include = "nid,name,content,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzScientificService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取科学研究列表")
    @JSON(type = WzScientific.class,include = "nid,name,link,content,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzScientific entity=new WzScientific();
        entity.setSortField("SORT_NUM");
        entity.setSortOrder("asc");
        entity.setDeleteFlag(0L);
        List<WzScientific> list=this.wzScientificService.getList(entity);
        return success(FIND_SUCCESS, list);
    }
}
