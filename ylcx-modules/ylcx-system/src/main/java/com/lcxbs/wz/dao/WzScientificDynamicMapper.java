package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzScientificDynamic;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzScientificDynamicMapper extends BaseMapper<WzScientificDynamic, Long> {

	public WzScientificDynamicMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzScientificDynamicMapper.class;
	}
}
