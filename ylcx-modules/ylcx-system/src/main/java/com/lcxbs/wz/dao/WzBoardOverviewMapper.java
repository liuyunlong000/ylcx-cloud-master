package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzBoardOverview;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzBoardOverviewMapper extends BaseMapper<WzBoardOverview, Long> {

	public WzBoardOverviewMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzBoardOverviewMapper.class;
	}
}
