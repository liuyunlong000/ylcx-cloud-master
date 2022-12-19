package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzNews;
import org.springframework.stereotype.Service;


@Service
public interface WzNewsService extends IBaseService<WzNews, Long> {
	BaseMapper<WzNews, Long> getMapper();
}
