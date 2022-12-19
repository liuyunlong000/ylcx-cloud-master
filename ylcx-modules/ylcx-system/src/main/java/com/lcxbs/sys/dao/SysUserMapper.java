package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysUserMapper.java</p>
 * <p>Description:用户信息DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:43
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysUserMapper extends BaseMapper<SysUser, Long> {

	public SysUserMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysUserMapper.class;
	}
}
