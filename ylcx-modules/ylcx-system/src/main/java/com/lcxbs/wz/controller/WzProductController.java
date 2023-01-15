package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzInnovatePlateForm;
import com.lcxbs.wz.model.WzProduct;
import com.lcxbs.wz.service.WzProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        entity.setSortField("sortNum");
        entity.setSortOrder("asc");
        entity.setDeleteFlag(0L);
        wzProductService.setupOrderByAndGroupBy(entity);
        List<WzProduct> list=this.wzProductService.getList(entity);
        return success(FIND_SUCCESS, list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzInnovatePlateForm:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzProduct wzProduct, HttpServletRequest request) {
        int result = wzProductService.insert(wzProduct);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzInnovatePlateForm:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzProduct wzProduct,HttpServletRequest request) {
        return success(UPDATE_SUCCESS, wzProductService.updateSelective(wzProduct));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzInnovatePlateForm:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id, HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzProductService.delete(id));
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('wzInnovatePlateForm:find_list',true,#request)")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzProduct.class,include = "nid,name,content,sortNum")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzProduct wzProduct,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzProduct.getPageNum(), wzProduct.getPageSize());
        wzProductService.setupOrderByAndGroupBy(wzProduct);
        PageInfo<WzProduct> list = wzProductService.getListByPage(wzProduct);
        List<WzProduct> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
