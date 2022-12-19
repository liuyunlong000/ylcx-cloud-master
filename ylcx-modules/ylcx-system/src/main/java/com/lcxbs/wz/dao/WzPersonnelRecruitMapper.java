package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzPersonnelRecruit;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzPersonnelRecruitMapper extends BaseMapper<WzPersonnelRecruit, Long> {

	public WzPersonnelRecruitMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzPersonnelRecruitMapper.class;
	}
}
