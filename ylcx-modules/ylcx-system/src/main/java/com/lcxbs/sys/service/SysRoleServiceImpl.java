package com.lcxbs.sys.service;
import org.apache.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcxbs.sys.dao.SysRoleMapper;
import com.lcxbs.sys.model.SysRole;
import com.lcxbs.core.BaseService;
import cn.hutool.core.date.DateUtil;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.sys.service.SysUserService;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>Title: SysRoleServiceImpl.java</p>
 * <p>Description:角色信息服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-6 22:59:59
 * @version V1.0
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseService<SysRole, Long> implements SysRoleService {

    protected static Logger logger = null;

    @Resource
    private SysRoleMapper sysRoleMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysRoleServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysRole, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysRoleMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysRoleMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysRole model) {
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
        model.setCreatedTime(System.currentTimeMillis());
        if(model.getUpdatedBy() == null && null!=currentUser) {
            model.setUpdatedBy(String.valueOf(currentUser.getNid()));
        }
        model.setUpdatedTime(System.currentTimeMillis());
       if(model.getTenantId() == null && null!=currentUser) {
            model.setTenantId(currentUser.getTenantId());
        }
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysRole model) {
        SysUser currentUser=this.sysUserService.getCurrentUser();
        if(null!=currentUser) {
            model.setUpdatedBy(String.valueOf(currentUser.getNid()));
        }
        model.setUpdatedTime(System.currentTimeMillis());
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
            SysRole model=new SysRole(id);
            model.setDeleteFlag(1L);
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
        SysRole model=new SysRole(id);
        model.setDeleteFlag(1L);
        return this.updateSelective(model);
    }
}


