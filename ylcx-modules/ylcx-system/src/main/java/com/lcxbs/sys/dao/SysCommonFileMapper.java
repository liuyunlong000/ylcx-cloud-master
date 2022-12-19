package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysCommonFile;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysCommonFileMapper.java</p>
 * <p>Description:文件信息DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:09:59
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysCommonFileMapper extends BaseMapper<SysCommonFile, Long> {

	public SysCommonFileMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysCommonFileMapper.class;
	}
}
