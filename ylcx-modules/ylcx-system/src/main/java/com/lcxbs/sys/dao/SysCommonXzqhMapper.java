package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysCommonXzqh;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysCommonXzqhMapper.java</p>
 * <p>Description:行政区划DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:24
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysCommonXzqhMapper extends BaseMapper<SysCommonXzqh, Long> {

	public SysCommonXzqhMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysCommonXzqhMapper.class;
	}
}
