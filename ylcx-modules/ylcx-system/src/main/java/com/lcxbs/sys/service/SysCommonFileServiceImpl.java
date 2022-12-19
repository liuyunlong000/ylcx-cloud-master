package com.lcxbs.sys.service;
import org.apache.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcxbs.sys.dao.SysCommonFileMapper;
import com.lcxbs.sys.model.SysCommonFile;
import com.lcxbs.core.BaseService;
import cn.hutool.core.date.DateUtil;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.sys.service.SysUserService;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>Title: SysCommonFileServiceImpl.java</p>
 * <p>Description:文件信息服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:01:32
 * @version V1.0
 */
@Service("sysCommonFileService")
public class SysCommonFileServiceImpl extends BaseService<SysCommonFile, Long> implements SysCommonFileService {

    protected static Logger logger = null;

    @Resource
    private SysCommonFileMapper sysCommonFileMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysCommonFileServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysCommonFile, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysCommonFileMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysCommonFileMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysCommonFile model) {
        SysUser currentUser=this.sysUserService.getCurrentUser();
        if (model.getDisableFlag() == null) {
            model.setDisableFlag(1L);
        }
        model.setDeleteFlag(0L);
        if(model.getSortNum()==null) {
            model.setSortNum(Long.valueOf(DateUtil.format(new Date(),"yyMMddHHmmss")));
        }
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
    public int updateSelective(SysCommonFile model) {
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
            SysCommonFile model=new SysCommonFile(id);
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
        SysCommonFile model=new SysCommonFile(id);
        model.setDeleteFlag(1L);
        return this.updateSelective(model);
    }
}


