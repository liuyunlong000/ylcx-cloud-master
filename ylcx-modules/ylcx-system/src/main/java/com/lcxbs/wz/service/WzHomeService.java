package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzHome;
import org.springframework.stereotype.Service;


@Service
public interface WzHomeService extends IBaseService<WzHome, Long> {
	BaseMapper<WzHome, Long> getMapper();
}
