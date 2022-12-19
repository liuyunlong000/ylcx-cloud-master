package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysCommonLog;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysCommonLog.java</p>
 * <p>Description:登录日志服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:40
 * @version V1.0
 */
@Service
public interface SysCommonLogService extends IBaseService<SysCommonLog, Long> {
	BaseMapper<SysCommonLog, Long> getMapper();

	/**
	 * 按会话ID更新退出时间和在线时长
	 * @param sysCommonLog
	 * @return
	 */
	int updateBySessionId(SysCommonLog sysCommonLog);
}
