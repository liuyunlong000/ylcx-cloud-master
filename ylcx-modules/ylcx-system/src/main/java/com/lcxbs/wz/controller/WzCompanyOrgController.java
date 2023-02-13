package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzCompanyOrg;
import com.lcxbs.wz.service.WzCompanyOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wzCompanyOrg")
@Api(value = "/wzCompanyOrg", tags = "组织架构")
public class WzCompanyOrgController extends BaseController {
    @Resource
    private WzCompanyOrgService wzCompanyOrgService;


    @GetMapping("/find_id")
    @ApiOperation("按id查询板块")
    @JSON(type = WzCompanyOrg.class,include = "nid,title,content")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id, HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzCompanyOrgService.getModelByKey(id));
    }
    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzHome:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzCompanyOrg entity, HttpServletRequest request) {
        return success(UPDATE_SUCCESS, wzCompanyOrgService.updateSelective(entity));
    }

    @PostMapping("/find_list_by_page")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzCompanyOrg.class,include = "nid,title,content")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzCompanyOrg entity,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(entity.getPageNum(), entity.getPageSize());
        wzCompanyOrgService.setupOrderByAndGroupBy(entity);
        PageInfo<WzCompanyOrg> list = wzCompanyOrgService.getListByPage(entity);
        List<WzCompanyOrg> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
