package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysUserDept;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysUserDept.java</p>
 * <p>Description:用户-部门关联信息服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:55
 * @version V1.0
 */
@Service
public interface SysUserDeptService extends IBaseService<SysUserDept, Long> {
	BaseMapper<SysUserDept, Long> getMapper();
}
