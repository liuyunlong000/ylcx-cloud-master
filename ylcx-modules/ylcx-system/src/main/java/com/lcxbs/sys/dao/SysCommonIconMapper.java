package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysCommonIcon;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysCommonIconMapper.java</p>
 * <p>Description:图标信息DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:06
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysCommonIconMapper extends BaseMapper<SysCommonIcon, Long> {

	public SysCommonIconMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysCommonIconMapper.class;
	}
}
