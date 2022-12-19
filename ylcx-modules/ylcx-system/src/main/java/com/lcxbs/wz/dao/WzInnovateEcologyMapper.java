package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzInnovateEcology;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzInnovateEcologyMapper extends BaseMapper<WzInnovateEcology, Long> {

	public WzInnovateEcologyMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzInnovateEcologyMapper.class;
	}
}
