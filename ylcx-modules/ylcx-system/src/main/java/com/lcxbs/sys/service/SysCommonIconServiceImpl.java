package com.lcxbs.sys.service;
import org.apache.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcxbs.sys.dao.SysCommonIconMapper;
import com.lcxbs.sys.model.SysCommonIcon;
import com.lcxbs.core.BaseService;
import cn.hutool.core.date.DateUtil;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.sys.service.SysUserService;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>Title: SysCommonIconServiceImpl.java</p>
 * <p>Description:图标信息服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:37
 * @version V1.0
 */
@Service("sysCommonIconService")
public class SysCommonIconServiceImpl extends BaseService<SysCommonIcon, Long> implements SysCommonIconService {

    protected static Logger logger = null;

    @Resource
    private SysCommonIconMapper sysCommonIconMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysCommonIconServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysCommonIcon, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysCommonIconMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysCommonIconMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysCommonIcon model) {
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
    public int updateSelective(SysCommonIcon model) {
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
            SysCommonIcon model=new SysCommonIcon(id);
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
        SysCommonIcon model=new SysCommonIcon(id);
        model.setDeleteFlag(1L);
        return this.updateSelective(model);
    }
}


