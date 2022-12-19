package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzNews;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzNewsMapper extends BaseMapper<WzNews, Long> {

	public WzNewsMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzNewsMapper.class;
	}
}
