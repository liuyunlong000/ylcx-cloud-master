package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysMenu;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysMenuMapper.java</p>
 * <p>Description:菜单信息DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:33
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysMenuMapper extends BaseMapper<SysMenu, Long> {

	public SysMenuMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysMenuMapper.class;
	}
}
