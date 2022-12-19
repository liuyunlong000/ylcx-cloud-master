package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzNotice;
import org.springframework.stereotype.Service;


@Service
public interface WzNoticeService extends IBaseService<WzNotice, Long> {
	BaseMapper<WzNotice, Long> getMapper();
}
