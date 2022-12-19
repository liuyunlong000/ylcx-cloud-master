package com.lcxbs.sys.service;
import org.springframework.stereotype.Service;
import com.lcxbs.core.BaseMapper;

import com.lcxbs.sys.model.SysDepartment;
import com.lcxbs.core.IBaseService;

/**
 * <p>Title: SysDepartment.java</p>
 * <p>Description:部门信息服务接口</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:30
 * @version V1.0
 */
@Service
public interface SysDepartmentService extends IBaseService<SysDepartment, Long> {
	BaseMapper<SysDepartment, Long> getMapper();
}
