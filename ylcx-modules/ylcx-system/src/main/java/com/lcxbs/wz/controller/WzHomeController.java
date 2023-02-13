package com.lcxbs.wz.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.TreeUtil;
import com.lcxbs.wz.model.WzHome;
import com.lcxbs.wz.service.WzHomeService;
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
@RequestMapping("/wzHome")
@Api(value = "/wzHome", tags = "首页轮播图")
@CrossOrigin(origins = "*")
public class WzHomeController extends BaseController {
    @Resource
    private WzHomeService wzHomeService;


    @GetMapping("/find_list")
    @ApiOperation("获取首页轮播图列表")
    @JSON(type = WzHome.class,include = "title,imgUrl,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzHome wzHome=new WzHome();
        wzHome.setSortField("sortNum");
        wzHome.setSortOrder("asc");
        wzHome.setDeleteFlag(0L);//未删除
        List<WzHome> list=this.wzHomeService.getList(wzHome);
        return success(FIND_SUCCESS, list);
    }
    @PostMapping("/save")
    @PreAuthorize("@ps.hasAuthority('wzHome:save',true,#request)")
    @ApiOperation("添加暂无信息")
    public RespMsgBean save(@RequestBody WzHome wzHome, HttpServletRequest request) {
        int result = wzHomeService.insert(wzHome);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            return success(SAVE_SUCCESS, map);
        } else {
            return success(SAVE_FAILURE);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("@ps.hasAuthority('wzHome:update',true,#request)")
    @ApiOperation("编辑暂无信息")
    public RespMsgBean update(@RequestBody WzHome wzHome,HttpServletRequest request) {
        return success(UPDATE_SUCCESS, wzHomeService.updateSelective(wzHome));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasAuthority('wzHome:delete',true,#request)")
    @ApiOperation("按id删除暂无信息")
    public RespMsgBean delete(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(DELETE_SUCCESS, wzHomeService.delete(id));
    }

    @DeleteMapping("/bat_delete")
    @ApiOperation("按id集合批量删除暂无信息")
    @PreAuthorize("@ps.hasAuthority('wzHome:delete',true,#request)")
    public RespMsgBean batDelete(@ApiParam(value = "id集合", required = true) @RequestBody List<Long> ids, HttpServletRequest request) throws Exception {
        return success(DELETE_SUCCESS, wzHomeService.batchDelete(ids));
    }

    @GetMapping("/find_id")
    @ApiOperation("按id查询暂无信息")
    @PreAuthorize("@ps.hasAuthority('wzHome:find_id',true,#request)")
    @JSON(type = WzHome.class,include = "nid,title,imgUrl,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id, HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzHomeService.getModelByKey(id));
    }

    @PostMapping("/find_list_by_page")
    @PreAuthorize("@ps.hasAuthority('wzHome:find_list',true,#request)")
    @ApiOperation("获取暂无信息列表（分页）")
    @JSON(type = WzHome.class,include = "nid,title,imgUrl,sortNum")
    @JSON(type = PageInfo.class,include = "pageNum,pageSize,size,pages,list,total")
    public RespMsgBean findListByPage(@RequestBody(required = false) WzHome wzHome,HttpServletRequest request) throws  Exception{
        PageHelper.startPage(wzHome.getPageNum(), wzHome.getPageSize());
        wzHomeService.setupOrderByAndGroupBy(wzHome);
        PageInfo<WzHome> list = wzHomeService.getListByPage(wzHome);
        List<WzHome> treeList = TreeUtil.buildTree(list.getList());
        list.setList(treeList);
        return success(FIND_SUCCESS,list);
    }
}
