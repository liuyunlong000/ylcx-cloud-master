package com.lcxbs.sys.service;

import cn.hutool.core.date.DateUtil;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.exception.CommonException;
import com.lcxbs.protocol.RespCodeEnum;
import com.lcxbs.sys.dao.SysUserMapper;
import com.lcxbs.sys.model.SysUser;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: SysUserServiceImpl.java</p>
 * <p>Description:用户信息服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:43
 * @version V1.0
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseService<SysUser, Long> implements SysUserService {

    protected static Logger logger = null;

    @Resource
    private SysUserMapper sysUserMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysUserServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysUser, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysUserMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysUserMapper.getSqlSessionFactory();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @Override
    public SysUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null&&(authentication instanceof OAuth2Authentication)) {
            String userLogin = authentication.getName();
            if(!StringUtils.isBlank(userLogin)) {
                SysUser model = new SysUser();
                model.setUserLogin(userLogin);
                return super.getModelByWhere(model);
            }
        }
        return null;
    }
	
	@Override
    public int insert(SysUser model) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(model.getUserName());
        sysUser.setDeleteFlag(0L);
        List<SysUser> list = sysUserService.getList(sysUser);
        if (list.size() > 0) {
            throw new CommonException(RespCodeEnum.FAILURE.getCode(), "该登录名已经存在，请换一个试试!");
        }
        //此处密码需要加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        model.setUserPassword(passwordEncoder.encode(model.getUserPassword()));
        if (model.getSortNum() == null) {
            model.setSortNum(Long.valueOf(DateUtil.format(new Date(), "yyMMddHHmmss")));
        }
        if (model.getDisableFlag() == null) {
            model.setDisableFlag(1L);
        }
        SysUser currentUser = this.getCurrentUser();
        if (model.getCreatedBy() == null && null != currentUser) {
            model.setCreatedBy(String.valueOf(currentUser.getNid()));
        }
        if (model.getUpdatedBy() == null && null != currentUser) {
            model.setUpdatedBy(String.valueOf(currentUser.getNid()));
        }
        if (model.getTenantId() == null && null != currentUser) {
            model.setTenantId(currentUser.getTenantId());
        }
        model.setDeleteFlag(0L);
        model.setCreatedTime(System.currentTimeMillis());
        model.setUpdatedTime(System.currentTimeMillis());
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysUser model) {
        SysUser currentUser=this.getCurrentUser();
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
            SysUser model=new SysUser(id);
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
        SysUser model=new SysUser(id);
        model.setDeleteFlag(1L);
        return this.updateSelective(model);
    }

    /**
     * 按用户名获取用户
     * @param userLogin
     * @return
     */
    @Override
    public SysUser getByUserLogin(String userLogin) {
        if(StringUtils.isBlank(userLogin)){
            return null;
        }
        SysUser sysUser=new SysUser();
        sysUser.setUserLogin(userLogin);
        return this.getMapper().getModel("getByUserLogin",sysUser);
    }
}


