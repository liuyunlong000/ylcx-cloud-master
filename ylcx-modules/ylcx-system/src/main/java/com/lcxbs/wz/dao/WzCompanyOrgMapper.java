package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzCompanyOrg;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzCompanyOrgMapper extends BaseMapper<WzCompanyOrg, Long> {

	public WzCompanyOrgMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzCompanyOrgMapper.class;
	}
}
