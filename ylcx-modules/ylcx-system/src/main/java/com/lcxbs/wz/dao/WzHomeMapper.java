package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzHome;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzHomeMapper extends BaseMapper<WzHome, Long> {

	public WzHomeMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzHomeMapper.class;
	}
}
