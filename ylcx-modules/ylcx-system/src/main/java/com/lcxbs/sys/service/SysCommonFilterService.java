package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysCommonFilter;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysCommonFilter.java</p>
 * <p>Description:操作日志服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-8 9:47:05
 * @version V1.0
 */
@Service
public interface SysCommonFilterService extends IBaseService<SysCommonFilter, Long> {
	BaseMapper<SysCommonFilter, Long> getMapper();
}
