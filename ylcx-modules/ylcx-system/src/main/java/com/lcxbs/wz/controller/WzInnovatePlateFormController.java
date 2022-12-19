package com.lcxbs.wz.controller;


import com.lcxbs.core.BaseController;
import com.lcxbs.json.annotation.JSON;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.wz.model.WzInnovatePlateForm;
import com.lcxbs.wz.service.WzInnovatePlateFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wzInnovatePlateForm")
@Api(value = "/wzInnovatePlateForm", tags = "创新平台")
public class WzInnovatePlateFormController extends BaseController {
    @Resource
    private WzInnovatePlateFormService wzInnovatePlateFormService;

    @GetMapping("/find_list")
    @ApiOperation("获取创新平台列表")
    @JSON(type = WzInnovatePlateForm.class,include = "nid,name,link,content,sortNum")
    public RespMsgBean findList(HttpServletRequest request) throws Exception{
        WzInnovatePlateForm entity=new WzInnovatePlateForm();
        entity.setSortField("SORT_NUM");
        entity.setSortOrder("asc");
        entity.setDeleteFlag(0L);
        List<WzInnovatePlateForm> list=this.wzInnovatePlateFormService.getList(entity);
        return success(FIND_SUCCESS, list);
    }
}
