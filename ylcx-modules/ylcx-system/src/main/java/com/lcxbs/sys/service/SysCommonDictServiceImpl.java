package com.lcxbs.sys.service;
import cn.hutool.core.date.DateUtil;
import com.lcxbs.exception.CommonException;
import com.lcxbs.sys.model.SysMenu;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcxbs.sys.dao.SysCommonDictMapper;
import com.lcxbs.sys.model.SysCommonDict;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.DynamicDataSource;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>Title: SysCommonDictServiceImpl.java</p>
 * <p>Description:数据字典服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:09:44
 * @version V1.0
 */
@Service("sysCommonDictService")
public class SysCommonDictServiceImpl extends BaseService<SysCommonDict, Long> implements SysCommonDictService {

    protected static Logger logger = null;

    @Resource
    private SysCommonDictMapper sysCommonDictMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysCommonDictServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysCommonDict, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysCommonDictMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysCommonDictMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysCommonDict model) {
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
        if(model.getUpdatedBy() == null && null!=currentUser) {
            model.setUpdatedBy(String.valueOf(currentUser.getNid()));
        }
        model.setUpdatedTime(System.currentTimeMillis());
        model.setCreatedTime(System.currentTimeMillis());
       if(model.getTenantId() == null && null!=currentUser) {
            model.setTenantId(currentUser.getTenantId());
        }
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysCommonDict model) {
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
            SysCommonDict model=new SysCommonDict(id);
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
        //删除子节点
        SysCommonDict subDict = new SysCommonDict();
        subDict.setDeleteFlag(1L);
        subDict.getMap().put("parentId", id);
        this.updateByMap(subDict);
        //删除父节点
        SysCommonDict model = new SysCommonDict(id);
        model.setDeleteFlag(1L);
        return this.updateSelective(model);
    }

    /**
     * 编码是否存在
     *
     * @param sysCommonDict
     */
    public void dictCodeIsExist(SysCommonDict sysCommonDict) throws CommonException {
        SysCommonDict model = new SysCommonDict();
        model.setDeleteFlag(0L);
        model.setDictCode(sysCommonDict.getDictCode());
        if (sysCommonDict.getDictType().equals(2L)) {
            model.setDictType(2L);//字典
            if (sysCommonDict.getNid() != null) {//更新操作
                SysCommonDict oldDictClass = this.getModelByKey(sysCommonDict.getNid());
                if (null != sysCommonDict.getDictCode() && !oldDictClass.getDictCode().equals(sysCommonDict.getDictCode())) {
                    List<SysCommonDict> list = this.getList(model);
                    if (list != null && list.size() > 0) {
                        throw new CommonException(0, "字典编码不允许重复，请换一个试试！");
                    }
                }
            } else {//新增操作
                List<SysCommonDict> list = this.getList(model);
                if (list != null && list.size() > 0) {
                    throw new CommonException(0, "字典编码不允许重复，请换一个试试！");
                }
            }
        } else if (sysCommonDict.getDictType().equals(3L)) {//字典项
            model.setDictType(3L);//字典项
            model.setDictValue(sysCommonDict.getDictValue());
            if (sysCommonDict.getNid() != null) {//更新操作
                SysCommonDict oldDictClass = this.getModelByKey(sysCommonDict.getNid());
                if (null != sysCommonDict.getDictValue() && !oldDictClass.getDictValue().equals(sysCommonDict.getDictValue())) {
                    List<SysCommonDict> list = this.getList(model);
                    if (list != null && list.size() > 0) {
                        throw new CommonException(0, "编码值不允许重复，请换一个试试！");
                    }
                }
            } else {//新增操作
                List<SysCommonDict> list = this.getList(model);
                if (list != null && list.size() > 0) {
                    throw new CommonException(0, "编码值不允许重复，请换一个试试！");
                }
            }
        }
    }
}


