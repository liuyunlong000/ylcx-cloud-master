package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzLeader;
import com.lcxbs.wz.model.WzPersonnelOverview;
import com.lcxbs.wz.service.WzLeaderService;
import com.lcxbs.wz.service.WzPersonnelOverviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/wzPersonnelOverview")
@Api(value = "/wzPersonnelOverview", tags = "人才概况")
public class WzPersonnelOverviewController extends BaseController {
    @Resource
    private WzPersonnelOverviewService wzPersonnelOverviewService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询人才信息")
    @JSON(type = WzPersonnelOverview.class,include = "name,post,photo,resume,content,releaseTime,source,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzPersonnelOverviewService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取人才列表")
    @JSON(type = WzPersonnelOverview.class,include = "nid,name,post,photo,resume,content,releaseTime,source,sortNum,createdTime")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzPersonnelOverview entity1=new WzPersonnelOverview();
        entity1.setSortField("SORT_NUM");
        entity1.setSortOrder("asc");
        entity1.setDeleteFlag(0L);
        List<WzPersonnelOverview> list=this.wzPersonnelOverviewService.getList(entity1);
        Map<String,List<WzPersonnelOverview>> map =new LinkedHashMap<>();
        for (WzPersonnelOverview item :list) {
            if(!map.containsKey(item.getType())) {
                map.put(item.getType(), new ArrayList<WzPersonnelOverview>());
            }
            map.get(item.getType()).add(item);
        }
        List<Object> _list =new ArrayList<>();
        for(String key:map.keySet()){
            Map<String,Object> data=new HashMap<>();
            data.put("type",key);
            data.put("list",map.get(key));
            _list.add(data);
        }

        return success(FIND_SUCCESS, _list);
    }
}
