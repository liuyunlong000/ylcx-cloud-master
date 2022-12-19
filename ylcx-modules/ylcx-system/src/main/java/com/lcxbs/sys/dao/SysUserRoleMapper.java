package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysUserRole;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysUserRoleMapper.java</p>
 * <p>Description:用户-角色关联DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:58
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysUserRoleMapper extends BaseMapper<SysUserRole, Long> {

	public SysUserRoleMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysUserRoleMapper.class;
	}
}
