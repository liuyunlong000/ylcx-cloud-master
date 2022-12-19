package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzPersonnelRecruit;
import org.springframework.stereotype.Service;


@Service
public interface WzPersonnelRecruitService extends IBaseService<WzPersonnelRecruit, Long> {
	BaseMapper<WzPersonnelRecruit, Long> getMapper();
}
