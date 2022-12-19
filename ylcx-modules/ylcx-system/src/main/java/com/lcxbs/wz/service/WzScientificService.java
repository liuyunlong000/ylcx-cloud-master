package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzScientific;
import org.springframework.stereotype.Service;


@Service
public interface WzScientificService extends IBaseService<WzScientific, Long> {
	BaseMapper<WzScientific, Long> getMapper();
}
