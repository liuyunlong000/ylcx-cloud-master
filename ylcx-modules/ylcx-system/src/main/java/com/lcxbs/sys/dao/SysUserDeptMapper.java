package com.lcxbs.sys.dao;
import org.springframework.stereotype.Repository;

import com.lcxbs.sys.model.SysUserDept;
import com.lcxbs.core.BaseMapper;

/**
 * <p>Title: SysUserDeptMapper.java</p>
 * <p>Description:用户-部门关联信息DAO</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:55
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SysUserDeptMapper extends BaseMapper<SysUserDept, Long> {

	public SysUserDeptMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return SysUserDeptMapper.class;
	}
}
