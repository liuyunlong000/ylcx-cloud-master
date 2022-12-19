package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzPersonnelOverview;
import org.springframework.stereotype.Service;


@Service
public interface WzPersonnelOverviewService extends IBaseService<WzPersonnelOverview, Long> {
	BaseMapper<WzPersonnelOverview, Long> getMapper();
}
