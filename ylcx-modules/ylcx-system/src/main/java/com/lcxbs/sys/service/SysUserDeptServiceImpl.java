package com.lcxbs.sys.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.sys.dao.SysUserDeptMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.sys.model.SysUserDept;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: SysUserDeptServiceImpl.java</p>
 * <p>Description:用户-部门关联信息服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:56
 * @version V1.0
 */
@Service("sysUserDeptService")
public class SysUserDeptServiceImpl extends BaseService<SysUserDept, Long> implements SysUserDeptService {

    protected static Logger logger = null;

    @Resource
    private SysUserDeptMapper sysUserDeptMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysUserDeptServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysUserDept, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysUserDeptMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysUserDeptMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysUserDept model) {
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysUserDept model) {
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
            SysUserDept model=new SysUserDept(id);
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
        SysUserDept model=new SysUserDept(id);
        return this.updateSelective(model);
    }
}


