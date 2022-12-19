package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzLeader;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzLeaderMapper extends BaseMapper<WzLeader, Long> {

	public WzLeaderMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzLeaderMapper.class;
	}
}
