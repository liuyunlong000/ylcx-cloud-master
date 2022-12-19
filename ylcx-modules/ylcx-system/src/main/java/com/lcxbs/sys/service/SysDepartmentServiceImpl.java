package com.lcxbs.sys.service;

import cn.hutool.core.date.DateUtil;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.exception.CommonException;
import com.lcxbs.sys.dao.SysDepartmentMapper;
import com.lcxbs.sys.model.SysDepartment;
import com.lcxbs.sys.model.SysMenu;
import com.lcxbs.sys.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: SysDepartmentServiceImpl.java</p>
 * <p>Description:部门信息服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:31
 * @version V1.0
 */
@Service("sysDepartmentService")
public class SysDepartmentServiceImpl extends BaseService<SysDepartment, Long> implements SysDepartmentService {

    protected static Logger logger = null;

    @Resource
    private SysDepartmentMapper sysDepartmentMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysDepartmentServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysDepartment, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysDepartmentMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysDepartmentMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysDepartment model) {
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
        if(model.getUpdatedBy() == null && null!=currentUser) {
            model.setUpdatedBy(String.valueOf(currentUser.getNid()));
        }
        model.setCreatedTime(System.currentTimeMillis());
        model.setUpdatedTime(System.currentTimeMillis());
       if(model.getTenantId() == null && null!=currentUser) {
            model.setTenantId(currentUser.getTenantId());
        }
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysDepartment model) {
        if (!StringUtils.isBlank(model.getParentId())) {
            boolean result = checkParentCode(model.getDeptId(), model.getParentId());
            if (!result) {
                throw new CommonException(0, "上级菜单不能选择自己及下级节点。");
            }
        }
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
            SysDepartment model=new SysDepartment(id);
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
        SysDepartment model=new SysDepartment(id);
        model.setDeleteFlag(1L);
        return this.updateSelective(model);
    }

    /**
     * 判断上级是否选择自己或下级节点
     *
     * @param nodeId      当前节点编码
     * @param parentId 选择的上级阶段Id
     * @return
     */
    public boolean checkParentCode(String nodeId, String parentId) {
        SysDepartment department = new SysDepartment();
        department.setDeptId(parentId);
        department.setDeleteFlag(0L);
        SysDepartment model = super.getModelByWhere(department);
        if (model == null) {
            return true;
        }
        if (model.getDeptId().equals(nodeId)) {
            return false;
        } else {
            return checkParentCode(nodeId, model.getParentId());
        }
    }
}


