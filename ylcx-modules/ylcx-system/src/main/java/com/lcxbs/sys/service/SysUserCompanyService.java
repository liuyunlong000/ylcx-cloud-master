package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysUserCompany;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysUserCompany.java</p>
 * <p>Description:用户-公司关联服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:51
 * @version V1.0
 */
@Service
public interface SysUserCompanyService extends IBaseService<SysUserCompany, Long> {
	BaseMapper<SysUserCompany, Long> getMapper();
}
