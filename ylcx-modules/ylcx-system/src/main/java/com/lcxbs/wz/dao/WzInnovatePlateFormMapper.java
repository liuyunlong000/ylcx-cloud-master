package com.lcxbs.wz.dao;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.wz.model.WzInnovatePlateForm;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public class WzInnovatePlateFormMapper extends BaseMapper<WzInnovatePlateForm, Long> {

	public WzInnovatePlateFormMapper() {
		super();
	}

	@Override
	public Class getEntityClass() {
		return WzInnovatePlateFormMapper.class;
	}
}
