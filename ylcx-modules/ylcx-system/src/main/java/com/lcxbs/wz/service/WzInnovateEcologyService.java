package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzInnovateEcology;
import org.springframework.stereotype.Service;


@Service
public interface WzInnovateEcologyService extends IBaseService<WzInnovateEcology, Long> {
	BaseMapper<WzInnovateEcology, Long> getMapper();
}
