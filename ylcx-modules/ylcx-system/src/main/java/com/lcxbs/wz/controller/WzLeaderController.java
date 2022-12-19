package com.lcxbs.wz.controller;


import com.googlecode.aviator.runtime.type.seq.ArrayCollector;
import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzLeader;
import com.lcxbs.wz.service.WzLeaderService;
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
@RequestMapping("/wzLeader")
@Api(value = "/wzLeader", tags = "院领导")
public class WzLeaderController extends BaseController {
    @Resource
    private WzLeaderService wzLeaderService;

    @GetMapping("/find_id")
    @ApiOperation("按id查询院领导")
    @JSON(type = WzLeader.class,include = "name,post,photo,resume,content,releaseTime,source,sortNum")
    public RespMsgBean findById(@ApiParam(value = "id", required = true) @RequestParam("id") Long id,HttpServletRequest request) {
        return success(FIND_SUCCESS, this.wzLeaderService.getModelByKey(id));
    }

    @GetMapping("/find_list")
    @ApiOperation("获取院领导列表")
    @JSON(type = WzLeader.class,include = "nid,name,post,photo,resume,content,releaseTime,source,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzLeader wzLeader=new WzLeader();
        wzLeader.setSortField("SORT_NUM");
        wzLeader.setSortOrder("asc");
        wzLeader.setDeleteFlag(0L);
        List<WzLeader> list=this.wzLeaderService.getList(wzLeader);
        Map<String,List<WzLeader>> map =new LinkedHashMap<>();
        for (WzLeader item :list) {
            if(!map.containsKey(item.getPost())) {
                map.put(item.getPost(), new ArrayList<WzLeader>());
            }
            map.get(item.getPost()).add(item);
        }
        List<Object> _list =new ArrayList<>();
        for(String key:map.keySet()){
            Map<String,Object> data=new HashMap<>();
            data.put("post",key);
            data.put("list",map.get(key));
            _list.add(data);
        }
        return success(FIND_SUCCESS, _list);
    }
}
