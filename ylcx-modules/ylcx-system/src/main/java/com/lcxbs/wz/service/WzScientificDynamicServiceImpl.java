package com.lcxbs.wz.service;

import cn.hutool.core.date.DateUtil;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.sys.service.SysUserService;
import com.lcxbs.wz.dao.WzNoticeMapper;
import com.lcxbs.wz.dao.WzScientificDynamicMapper;
import com.lcxbs.wz.model.WzNotice;
import com.lcxbs.wz.model.WzScientificDynamic;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("wzScientificDynamicService")
public class WzScientificDynamicServiceImpl extends BaseService<WzScientificDynamic, Long> implements WzScientificDynamicService {

    protected static Logger logger = null;

    @Resource
    private WzScientificDynamicMapper wzScientificDynamicMapper;

	@Resource
    private SysUserService sysUserService;

    public WzScientificDynamicServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<WzScientificDynamic, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return wzScientificDynamicMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return wzScientificDynamicMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(WzScientificDynamic model) {
        SysUser currentUser=this.sysUserService.getCurrentUser();
        if(model.getSortNum()==null) {
            model.setSortNum(Long.valueOf(DateUtil.format(new Date(),"yyMMddHHmmss")));
        }
        if (model.getDisableFlag() == null) {
            model.setDisableFlag(1L);
        }
        model.setDeleteFlag(0L);
        if(model.getCreatedBy() == null && null!=currentUser) {
            model.setCreatedBy(String.valueOf(currentUser.getNid()));
        }
        model.setCreatedTime(new Date());
        if(model.getUpdatedBy() == null && null!=currentUser) {
            model.setUpdatedBy(String.valueOf(currentUser.getNid()));
        }
        model.setUpdatedTime(new Date());
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(WzScientificDynamic model) {
        SysUser currentUser=this.sysUserService.getCurrentUser();
        if(null!=currentUser) {
            model.setUpdatedBy(String.valueOf(currentUser.getNid()));
        }
        model.setUpdatedTime(new Date());
        return super.updateSelective(model);
    }
	
	/**
     * 批量逻辑删除
     * @throws Exception
     * @see com.lcxbs.core.IBaseService#batchDelete(List)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(List<Long> ids) throws Exception {
        int count = 0;
        for (Long id : ids) {
            WzScientificDynamic model=new WzScientificDynamic(id);
            model.setDeleteFlag(1L);
            count = count + this.updateSelective(model);
        }
        return count;
    }

}


