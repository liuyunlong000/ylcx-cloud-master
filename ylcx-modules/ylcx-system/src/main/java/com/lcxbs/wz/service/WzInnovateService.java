package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzInnovate;
import org.springframework.stereotype.Service;


@Service
public interface WzInnovateService extends IBaseService<WzInnovate, Long> {
	BaseMapper<WzInnovate, Long> getMapper();
}
