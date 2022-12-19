package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysRole;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysRole.java</p>
 * <p>Description:角色信息服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-3-6 22:59:59
 * @version V1.0
 */
@Service
public interface SysRoleService extends IBaseService<SysRole, Long> {
	BaseMapper<SysRole, Long> getMapper();
}
