package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysMenu;
import com.lcxbs.core.IBaseService;

import java.util.List;

/**
 * <p>Title: SysMenu.java</p>
 * <p>Description:菜单信息服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-2-26 22:10:33
 * @version V1.0
 */
@Service
public interface SysMenuService extends IBaseService<SysMenu, Long> {
	BaseMapper<SysMenu, Long> getMapper();

	/**
	 * 按用户获取所拥有的菜单
	 * @param userId
	 * @return
	 */
	List<SysMenu> getListByUserId(String userId);

	/**
	 * 按用户获取所拥有的菜单
	 * @param userId
	 * @return
	 */
	List<SysMenu> getListByUserId(String userId,SysMenu sysMenu);

	/**
	 * 判断是否存在子节点
	 * @param sysMenu
	 * @return
	 */
	boolean existChild(SysMenu sysMenu);
}
