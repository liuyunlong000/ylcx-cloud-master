package com.lcxbs.sys.service;
import cn.hutool.core.date.DateUtil;
import com.lcxbs.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcxbs.sys.dao.SysMenuMapper;
import com.lcxbs.sys.model.SysMenu;
import com.lcxbs.core.BaseService;
import com.lcxbs.core.BaseMapper;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.core.DynamicDataSource;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>Title: SysMenuServiceImpl.java</p>
 * <p>Description:菜单信息服务接口实现</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:34
 * @version V1.0
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseService<SysMenu, Long> implements SysMenuService {

    protected static Logger logger = null;

    @Resource
    private SysMenuMapper sysMenuMapper;
	
	@Resource
    private SysUserService sysUserService;

    public SysMenuServiceImpl() {
        super();
        logger = Logger.getLogger(this.getClass());
    }
    
    @Override
    public BaseMapper<SysMenu, Long> getMapper() {
    	DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);//设置为默认数据源
        return sysMenuMapper;
    }
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sysMenuMapper.getSqlSessionFactory();
    }
	
	@Override
    public int insert(SysMenu model) {
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
        checkParentMenuType(model);
        return super.insert(model);
    }
	
	@Override
    public int updateSelective(SysMenu model) {
        String parentId = model.getParentId();
        checkParentMenuType(model);
        if (!StringUtils.isBlank(parentId)) {
            boolean result = checkParentCode(model.getMenuId(), model.getParentId());
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
     * 判断是否存在子节点
     *
     * @param sysMenu
     * @return
     */
    public boolean existChild(SysMenu sysMenu) {
        if (sysMenu != null && sysMenu.getMenuId() != null) {
            SysMenu subMenu = new SysMenu();
            subMenu.setParentId(sysMenu.getMenuId());
            subMenu.setDeleteFlag(0L);
            List<SysMenu> subMenuList = super.getList(subMenu);
            if (null != subMenuList && subMenuList.size() > 0) {
                throw new CommonException(0, "[" + sysMenu.getMenuName() + "]菜单下还存在子项，请先删除子项后再删除。");
            }
        }
        return true;
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
            SysMenu model=new SysMenu(id);
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
        SysMenu menu = this.getModelByKey(id);
        if (menu != null) {
            SysMenu model = new SysMenu(id);
            model.setDeleteFlag(1L);
            return this.updateSelective(model);
        } else {
            return 0;
        }
    }

    /**
     * 按用户获取所拥有的菜单
     *
     * @param userId
     * @return
     */
    public List<SysMenu> getListByUserId(String userId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.getMap().put("userId", userId);
        return this.sysMenuMapper.getList("getListByUserId", sysMenu);
    }

    /**
     * 按用户获取所拥有的菜单
     *
     * @param userId
     * @return
     */
    public List<SysMenu> getListByUserId(String userId, SysMenu sysMenu) {
        sysMenu.getMap().put("userId", userId);
        return this.sysMenuMapper.getList("getListByUserId", sysMenu);
    }

    /**
     * 检查选择的上级菜单是否符合要求
     * @param sysMenu
     * @return
     */
    public boolean checkParentMenuType(SysMenu sysMenu){
        if(sysMenu.getParentId()==null||sysMenu.getParentId().equals("0")){
            return true;
        }
        String parentId=sysMenu.getParentId();
        SysMenu model=new SysMenu();
        model.setMenuId(parentId);
        model.setDeleteFlag(0L);
        model=super.getModelByWhere(model);
        if(model==null){
            return true;
        }
        if(sysMenu.getMenuType()==1&&model.getMenuType()!=1){
            throw new CommonException(0,"‘目录’类型的上级菜单只能选择目录，请重新选择。");
        }
        if(sysMenu.getMenuType()==2&&model.getMenuType()!=1){
            throw new CommonException(0,"‘菜单’类型的上级菜单只能选择目录，请重新选择。");
        }
        if(sysMenu.getMenuType()==3&&model.getMenuType()!=1&&model.getMenuType()!=2){
            throw new CommonException(0,"‘操作’类型的上级菜单只能选择目录或菜单，请重新选择。");
        }
        return true;
    }

    /**
     * 判断上级菜单是否选择自己或下级节点
     *
     * @param menuId      当前节点编码
     * @param parentId 选择的上级菜单编码
     * @return
     */
    public boolean checkParentCode(String menuId, String parentId) {
        SysMenu sysResource = new SysMenu();
        sysResource.setMenuId(parentId);
        sysResource.setDeleteFlag(0L);
        SysMenu model = super.getModelByWhere(sysResource);
        if (model == null) {
            return true;
        }
        if (model.getMenuId().equals(menuId)) {
            return false;
        } else {
            return checkParentCode(menuId, model.getParentId());
        }
    }
}


