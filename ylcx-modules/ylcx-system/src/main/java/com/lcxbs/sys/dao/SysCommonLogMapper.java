package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysCommonLog;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysCommonLogMapper.java</p>
 * <p>Description:登录日志DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:21
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysCommonLogMapper extends BaseMapper<SysCommonLog, Long> {

	public SysCommonLogMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysCommonLogMapper.class;
	}
}
