package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysCompany;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysCompany.java</p>
 * <p>Description:公司信息服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:46
 * @version V1.0
 */
@Service
public interface SysCompanyService extends IBaseService<SysCompany, Long> {
	BaseMapper<SysCompany, Long> getMapper();
}
