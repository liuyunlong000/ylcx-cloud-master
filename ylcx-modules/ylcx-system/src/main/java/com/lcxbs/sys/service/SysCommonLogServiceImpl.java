package com.lcxbs.sys.service;
import org.apache.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcxbs.sys.dao.SysCommonLogMapper;
import com.lcxbs.sys.model.SysCommonLog;
import com.lcxbs.core.BaseService;
import cn.hutool.core.date.DateUtil;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.sys.service.SysUserService;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>Title: SysCommonLogServiceImpl.java</p>
 * <p>Description:登录日志服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:40
 * @version V1.0
 */
@Service("sysCommonLogService")
public class SysCommonLogServiceImpl extends BaseService<SysCommonLog, Long> implements SysCommonLogService {

    protected static Logger logger = null;

    @Resource
    private SysCommonLogMapper sysCommonLogMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysCommonLogServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysCommonLog, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysCommonLogMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysCommonLogMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysCommonLog model) {
        SysUser currentUser=this.sysUserService.getCurrentUser();
       if(model.getTenantId() == null && null!=currentUser) {
            model.setTenantId(currentUser.getTenantId());
        }
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysCommonLog model) {
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
            count = count + this.sysCommonLogMapper.delete(id);
        }
        return count;
    }
	
	/**
     * 逻辑删除
     * @throws Exception
     */
    @Override
    public int delete(Long id) {
        return this.sysCommonLogMapper.delete(id);
    }

    /**
     * 按会话ID更新退出时间和在线时长
     * @param sysCommonLog
     * @return
     */
    public int updateBySessionId(SysCommonLog sysCommonLog){
        return this.sysCommonLogMapper.update("updateBySessionId",sysCommonLog);
    }
}


