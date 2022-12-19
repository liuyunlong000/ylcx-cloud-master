package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysRole;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysRoleMapper.java</p>
 * <p>Description:角色信息DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-3-6 22:59:59
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysRoleMapper extends BaseMapper<SysRole, Long> {

	public SysRoleMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysRoleMapper.class;
	}
}
