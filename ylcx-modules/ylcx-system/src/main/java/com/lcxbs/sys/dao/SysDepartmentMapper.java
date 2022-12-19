package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysDepartment;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysDepartmentMapper.java</p>
 * <p>Description:部门信息DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:30
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysDepartmentMapper extends BaseMapper<SysDepartment, Long> {

	public SysDepartmentMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysDepartmentMapper.class;
	}
}
