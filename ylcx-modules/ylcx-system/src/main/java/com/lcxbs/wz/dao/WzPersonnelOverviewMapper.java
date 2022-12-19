package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzPersonnelOverview;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzPersonnelOverviewMapper extends BaseMapper<WzPersonnelOverview, Long> {

	public WzPersonnelOverviewMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzPersonnelOverviewMapper.class;
	}
}
