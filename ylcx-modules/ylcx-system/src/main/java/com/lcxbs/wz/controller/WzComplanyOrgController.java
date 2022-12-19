package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzComplanyOrg;
import com.lcxbs.wz.service.WzComplanyOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wzComplanyOrg")
@Api(value = "/wzComplanyOrg", tags = "组织架构")
public class WzComplanyOrgController extends BaseController {
    @Resource
    private WzComplanyOrgService wzComplanyOrgService;


    @GetMapping("/find_list")
    @ApiOperation("获取组织架构树列表")
    @JSON(type = WzComplanyOrg.class,include = "nid,title,parentId,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzComplanyOrg wzComplanyOrg=new WzComplanyOrg();
        wzComplanyOrg.setParentId(0L);
        wzComplanyOrg.setSortField("sortNum");
        wzComplanyOrg.setSortOrder("asc");
        wzComplanyOrg.setDeleteFlag(0L);
        List<WzComplanyOrg> list=this.wzComplanyOrgService.getList(wzComplanyOrg);
        List<WzComplanyOrg> treeList =new ArrayList<>();
        for(WzComplanyOrg org:list){
            treeList.add(org);
            Tree(treeList,org.getNid());
        }
        return success(FIND_SUCCESS, treeList);
    }
    private void Tree(List<WzComplanyOrg> treeList,Long parentId){
        WzComplanyOrg wzComplanyOrg=new WzComplanyOrg();
        wzComplanyOrg.setParentId(parentId);
        wzComplanyOrg.setSortField("sortNum");
        wzComplanyOrg.setSortOrder("asc");
        wzComplanyOrg.setDeleteFlag(0L);
        List<WzComplanyOrg> list=this.wzComplanyOrgService.getList(wzComplanyOrg);
        for(WzComplanyOrg org:list){
            treeList.add(org);
            Tree(treeList,org.getNid());
        }
    }
}
