package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzProduct;
import com.lcxbs.wz.service.WzProductService;
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
@RequestMapping("/wzProduct")
@Api(value = "/wzProduct", tags = "产品介绍")
public class WzProductController extends BaseController {
    @Resource
    private WzProductService wzProductService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询产品介绍")
    @JSON(type = WzProduct.class,include = "nid,name,content,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzProductService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取产品介绍列表")
    @JSON(type = WzProduct.class,include = "nid,name,link,content,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzProduct entity=new WzProduct();
        entity.setSortField("SORT_NUM");
        entity.setSortOrder("asc");
        entity.setDeleteFlag(0L);
        List<WzProduct> list=this.wzProductService.getList(entity);
        return success(FIND_SUCCESS, list);
    }
}
