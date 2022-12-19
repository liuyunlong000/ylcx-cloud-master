package com.lcxbs.wz.controller;


import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzPersonnelRecruit;
import com.lcxbs.wz.service.WzPersonnelRecruitService;
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
@RequestMapping("/wzPersonnelRecruit")
@Api(value = "/wzPersonnelRecruit", tags = "人才招聘")
public class WzPersonnelRecruitController extends BaseController {
    @Resource
    private WzPersonnelRecruitService wzPersonnelRecruitService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询人才招聘")
    @JSON(type = WzPersonnelRecruit.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzPersonnelRecruitService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取人才招聘列表")
    @JSON(type = WzPersonnelRecruit.class,include = "nid,title,content,releaseTime,source")
    public RespMsgBean findList(@ApiParam(value = "count", required = true) @RequestParam("count") Integer count,HttpServletRequest request) throws Exception{
        WzPersonnelRecruit entity=new WzPersonnelRecruit();
        entity.setSortField("RELEASE_TIME");
        entity.setSortOrder("desc");
        entity.setDeleteFlag(0L);
        entity.setPageNum(1);
        entity.setPageSize(count);
        PageInfo<WzPersonnelRecruit> page=this.wzPersonnelRecruitService.getListByPage(entity);
        return success(FIND_SUCCESS, page.getList());
    }
}
