package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysUser.java</p>
 * <p>Description:用户信息服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:43
 * @version V1.0
 */
@Service
public interface SysUserService extends IBaseService<SysUser, Long> {
	BaseMapper<SysUser, Long> getMapper();

	/**
	 * 获取当前登录用户
	 * @return
	 */
	SysUser getCurrentUser();

	/**
	 * 按用户名获取用户
	 * @param userLogin
	 * @return
	 */
	SysUser getByUserLogin(String userLogin);
}
