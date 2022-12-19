package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysUserCompany;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysUserCompanyMapper.java</p>
 * <p>Description:用户-公司关联DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:51
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysUserCompanyMapper extends BaseMapper<SysUserCompany, Long> {

	public SysUserCompanyMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysUserCompanyMapper.class;
	}
}
