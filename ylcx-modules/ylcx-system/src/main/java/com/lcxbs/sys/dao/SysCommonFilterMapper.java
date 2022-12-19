package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysCommonFilter;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysCommonFilterMapper.java</p>
 * <p>Description:操作日志DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:03
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysCommonFilterMapper extends BaseMapper<SysCommonFilter, Long> {

	public SysCommonFilterMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysCommonFilterMapper.class;
	}
}
