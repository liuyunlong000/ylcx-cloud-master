package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzCompanyOrg;
import org.springframework.stereotype.Service;


@Service
public interface WzCompanyOrgService extends IBaseService<WzCompanyOrg, Long> {
	BaseMapper<WzCompanyOrg, Long> getMapper();
}
