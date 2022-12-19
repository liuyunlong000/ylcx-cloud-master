package com.lcxbs.sys.service;

import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.sys.dao.SysRoleMenuMapper;
import com.lcxbs.sys.model.SysRoleMenu;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: SysRoleMenuServiceImpl.java</p>
 * <p>Description:角色-菜单服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-2-26 22:10:40
 * @version V1.0
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseService<SysRoleMenu, Long> implements SysRoleMenuService {

    protected static Logger logger = null;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
	
    public SysRoleMenuServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysRoleMenu, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysRoleMenuMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysRoleMenuMapper.getSqlSessionFactory();
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
            count = count + this.delete(id);
        }
        return count;
    }
}


