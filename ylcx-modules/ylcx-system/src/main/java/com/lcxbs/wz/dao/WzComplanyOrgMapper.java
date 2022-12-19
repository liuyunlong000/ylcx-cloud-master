package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzComplanyOrg;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzComplanyOrgMapper extends BaseMapper<WzComplanyOrg, Long> {

	public WzComplanyOrgMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzComplanyOrgMapper.class;
	}
}
