package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzFriendlyLinks;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzFriendlyLinksMapper extends BaseMapper<WzFriendlyLinks, Long> {

	public WzFriendlyLinksMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzFriendlyLinksMapper.class;
	}
}
