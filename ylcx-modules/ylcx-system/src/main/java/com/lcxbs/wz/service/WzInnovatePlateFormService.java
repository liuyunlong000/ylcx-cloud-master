package com.lcxbs.wz.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.IBaseService;
import com.lcxbs.wz.model.WzInnovatePlateForm;
import org.springframework.stereotype.Service;


@Service
public interface WzInnovatePlateFormService extends IBaseService<WzInnovatePlateForm, Long> {
	BaseMapper<WzInnovatePlateForm, Long> getMapper();
}
