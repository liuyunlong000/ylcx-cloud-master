package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzFriendlyLinks;
import org.springframework.stereotype.Service;


@Service
public interface WzFriendlyLinksService extends IBaseService<WzFriendlyLinks, Long> {
	BaseMapper<WzFriendlyLinks, Long> getMapper();
}
