package com.lcxbs.sys.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.sys.dao.SysUserCompanyMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.sys.model.SysUserCompany;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: SysUserCompanyServiceImpl.java</p>
 * <p>Description:用户-公司关联服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:52
 * @version V1.0
 */
@Service("sysUserCompanyService")
public class SysUserCompanyServiceImpl extends BaseService<SysUserCompany, Long> implements SysUserCompanyService {

    protected static Logger logger = null;

    @Resource
    private SysUserCompanyMapper sysUserCompanyMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysUserCompanyServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysUserCompany, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysUserCompanyMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysUserCompanyMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysUserCompany model) {
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysUserCompany model) {
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
            SysUserCompany model=new SysUserCompany(id);
            count = count + this.updateSelective(model);
        }
        return count;
    }
	
	/**
     * 逻辑删除
     * @throws Exception
     */
    @Override
    public int delete(Long id) {
        SysUserCompany model=new SysUserCompany(id);
        return this.updateSelective(model);
    }
}


