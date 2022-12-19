package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzInnovateEcology;
import com.lcxbs.wz.service.WzInnovateEcologyService;
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
@RequestMapping("/wzInnovateEcology")
@Api(value = "/wzInnovateEcology", tags = "创新生态")
public class WzInnovateEcologyController extends BaseController {
    @Resource
    private WzInnovateEcologyService wzInnovateEcologyService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询创新生态")
    @JSON(type = WzInnovateEcology.class,include = "nid,title,content,releaseTime,source,sortNum,disableFlag,deleteFlag,createdBy,createdTime,updatedBy,updatedTime,map")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzInnovateEcologyService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取创新生态列表")
    @JSON(type = WzInnovateEcology.class,include = "nid,title,content,releaseTime,source,sortNum,disableFlag,deleteFlag,createdBy,createdTime,updatedBy,updatedTime,map")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzInnovateEcology entity=new WzInnovateEcology();
        entity.setSortField("releaseTime");
        entity.setSortOrder("desc");
        entity.setDeleteFlag(0L);//未删除
        List<WzInnovateEcology> list=this.wzInnovateEcologyService.getList(entity);
        return success(FIND_SUCCESS, list);
    }
}
