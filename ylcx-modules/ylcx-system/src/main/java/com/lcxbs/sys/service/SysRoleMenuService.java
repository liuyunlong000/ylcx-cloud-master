package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysRoleMenu;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysRoleMenu.java</p>
 * <p>Description:角色-菜单服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:39
 * @version V1.0
 */
@Service
public interface SysRoleMenuService extends IBaseService<SysRoleMenu, Long> {
	BaseMapper<SysRoleMenu, Long> getMapper();
}
