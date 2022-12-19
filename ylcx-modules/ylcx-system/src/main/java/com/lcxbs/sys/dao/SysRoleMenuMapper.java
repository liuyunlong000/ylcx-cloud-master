package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysRoleMenu;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysRoleMenuMapper.java</p>
 * <p>Description:角色-菜单DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:39
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysRoleMenuMapper extends BaseMapper<SysRoleMenu, Long> {

	public SysRoleMenuMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysRoleMenuMapper.class;
	}
}
