package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysCommonXzqh;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysCommonXzqh.java</p>
 * <p>Description:行政区划服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:15:34
 * @version V1.0
 */
@Service
public interface SysCommonXzqhService extends IBaseService<SysCommonXzqh, Long> {
	BaseMapper<SysCommonXzqh, Long> getMapper();
}
