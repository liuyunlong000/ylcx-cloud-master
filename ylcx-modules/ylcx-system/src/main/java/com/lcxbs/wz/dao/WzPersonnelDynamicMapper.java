package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzPersonnelDynamic;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzPersonnelDynamicMapper extends BaseMapper<WzPersonnelDynamic, Long> {

	public WzPersonnelDynamicMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzPersonnelDynamicMapper.class;
	}
}
