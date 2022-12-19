package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysUserRole;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysUserRole.java</p>
 * <p>Description:用户-角色关联服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:58
 * @version V1.0
 */
@Service
public interface SysUserRoleService extends IBaseService<SysUserRole, Long> {
	BaseMapper<SysUserRole, Long> getMapper();
}
