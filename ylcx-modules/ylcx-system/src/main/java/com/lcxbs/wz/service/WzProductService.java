package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzProduct;
import org.springframework.stereotype.Service;


@Service
public interface WzProductService extends IBaseService<WzProduct, Long> {
	BaseMapper<WzProduct, Long> getMapper();
}
