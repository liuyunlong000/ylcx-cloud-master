package com.lcxbs.sys.service;
import org.apache.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcxbs.sys.dao.SysCommonFilterMapper;
import com.lcxbs.sys.model.SysCommonFilter;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.DynamicDataSource;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>Title: SysCommonFilterServiceImpl.java</p>
 * <p>Description:操作日志服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-3-8 9:47:05
 * @version V1.0
 */
@Service("sysCommonFilterService")
public class SysCommonFilterServiceImpl extends BaseService<SysCommonFilter, Long> implements SysCommonFilterService {

    protected static Logger logger = null;

    @Resource
    private SysCommonFilterMapper sysCommonFilterMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysCommonFilterServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysCommonFilter, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysCommonFilterMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysCommonFilterMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysCommonFilter model) {
        SysUser currentUser=this.sysUserService.getCurrentUser();
        if(model.getCreatedBy() == null && null!=currentUser) {
            model.setCreatedBy(currentUser.getUserLogin());
        }
        model.setCreatedTime(System.currentTimeMillis());
       if(model.getTenantId() == null && null!=currentUser) {
            model.setTenantId(currentUser.getTenantId());
        }
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysCommonFilter model) {
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
            count = count + this.sysCommonFilterMapper.delete(id);
        }
        return count;
    }
	
	/**
     * 逻辑删除
     * @throws Exception
     */
    @Override
    public int delete(Long id) {
        return this.sysCommonFilterMapper.delete(id);
    }
}


