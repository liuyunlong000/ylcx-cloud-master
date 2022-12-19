package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzPersonnelDynamic;
import org.springframework.stereotype.Service;


@Service
public interface WzPersonnelDynamicService extends IBaseService<WzPersonnelDynamic, Long> {
	BaseMapper<WzPersonnelDynamic, Long> getMapper();
}
