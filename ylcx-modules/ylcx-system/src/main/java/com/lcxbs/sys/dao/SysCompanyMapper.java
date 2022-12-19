package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysCompany;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysCompanyMapper.java</p>
 * <p>Description:公司信息DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:27
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysCompanyMapper extends BaseMapper<SysCompany, Long> {

	public SysCompanyMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysCompanyMapper.class;
	}
}
