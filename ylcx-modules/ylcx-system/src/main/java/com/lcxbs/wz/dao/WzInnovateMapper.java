package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzInnovate;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzInnovateMapper extends BaseMapper<WzInnovate, Long> {

	public WzInnovateMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzInnovateMapper.class;
	}
}
