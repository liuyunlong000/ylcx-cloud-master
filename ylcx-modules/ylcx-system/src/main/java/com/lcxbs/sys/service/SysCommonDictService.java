package com.lcxbs.sys.service;
import com.lcxbs.exception.CommonException;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysCommonDict;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysCommonDict.java</p>
 * <p>Description:数据字典服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-2-26 22:09:44
 * @version V1.0
 */
@Service
public interface SysCommonDictService extends IBaseService<SysCommonDict, Long> {
	BaseMapper<SysCommonDict, Long> getMapper();
	/**
	 * 编码是否存在
	 *
	 * @param sysCommonDict
	 */
	void dictCodeIsExist(SysCommonDict sysCommonDict) throws CommonException;
}
