package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzProduct;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzProductMapper extends BaseMapper<WzProduct, Long> {

	public WzProductMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzProductMapper.class;
	}
}
