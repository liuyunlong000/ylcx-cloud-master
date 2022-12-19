package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzScientific;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzScientificMapper extends BaseMapper<WzScientific, Long> {

	public WzScientificMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzScientificMapper.class;
	}
}
