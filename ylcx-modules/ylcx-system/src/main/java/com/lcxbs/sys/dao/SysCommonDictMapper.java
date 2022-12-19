package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysCommonDict;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysCommonDictMapper.java</p>
 * <p>Description:数据字典DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:09:44
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysCommonDictMapper extends BaseMapper<SysCommonDict, Long> {

	public SysCommonDictMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysCommonDictMapper.class;
	}
}
