package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysCommonFile;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysCommonFile.java</p>
 * <p>Description:文件信息服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:31
 * @version V1.0
 */
@Service
public interface SysCommonFileService extends IBaseService<SysCommonFile, Long> {
	BaseMapper<SysCommonFile, Long> getMapper();
}
