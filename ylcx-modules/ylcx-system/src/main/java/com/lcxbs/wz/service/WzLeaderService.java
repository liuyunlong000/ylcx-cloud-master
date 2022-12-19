package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzLeader;
import org.springframework.stereotype.Service;


@Service
public interface WzLeaderService extends IBaseService<WzLeader, Long> {
	BaseMapper<WzLeader, Long> getMapper();
}
