package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysCommonIcon;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysCommonIcon.java</p>
 * <p>Description:图标信息服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:37
 * @version V1.0
 */
@Service
public interface SysCommonIconService extends IBaseService<SysCommonIcon, Long> {
	BaseMapper<SysCommonIcon, Long> getMapper();
}
