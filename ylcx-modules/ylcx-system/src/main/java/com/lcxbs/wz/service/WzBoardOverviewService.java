package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;

import com.lcxbs.wz.model.WzBoardOverview;
import org.springframework.stereotype.Service;


@Service
public interface WzBoardOverviewService extends IBaseService<WzBoardOverview, Long> {
	BaseMapper<WzBoardOverview, Long> getMapper();
}
