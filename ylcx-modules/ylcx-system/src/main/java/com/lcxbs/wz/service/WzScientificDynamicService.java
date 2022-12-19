package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzScientificDynamic;
import org.springframework.stereotype.Service;


@Service
public interface WzScientificDynamicService extends IBaseService<WzScientificDynamic, Long> {
	BaseMapper<WzScientificDynamic, Long> getMapper();
}
