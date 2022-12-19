package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzComplanyOrg;
import org.springframework.stereotype.Service;


@Service
public interface WzComplanyOrgService extends IBaseService<WzComplanyOrg, Long> {
	BaseMapper<WzComplanyOrg, Long> getMapper();
}
