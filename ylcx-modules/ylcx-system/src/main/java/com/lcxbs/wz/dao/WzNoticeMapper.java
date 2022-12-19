package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzNotice;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzNoticeMapper extends BaseMapper<WzNotice, Long> {

	public WzNoticeMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzNoticeMapper.class;
	}
}
