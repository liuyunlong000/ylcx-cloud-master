package com.lcxbs.core;

import com.lcxbs.cache.ClearCache;
import com.lcxbs.cache.InsertCache;
import com.lcxbs.utils.SpringContextUtil;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * Title: BaseService.java</p>
 * <p>
 * Description:服务基类</p>
 * <p>
 * Copyright: Copyright (c) 2014</p>
 *
 * @param <T>  实体对象
 * @param <PK> 实体对象主键类型
 * @author fanlianwei
 * @version V1.0
 * @date 2014-8-11 9:55:20
 */
public class BaseService<T, PK extends Serializable> implements IBaseService<T, PK> {

    /**
     * 实体类类型
     */
    private final Class<T> beanClass;

    /**
     * 主键类型
     */
    private final Class<PK> keyClass;

    public BaseService() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        beanClass = (Class<T>) types[0];
        keyClass = (Class<PK>) types[1];
    }

    protected BaseMapper getMapper() {
        return null;
    }

    /**
     * @see com.lcxbs.core.IBaseService#insert(Object)
     */
    @Override
    public int insert(T model) {
        return getMapper().insert(model);
    }

    /**
     * @see com.lcxbs.core.IBaseService#delete(Object)
     */
    @ClearCache
    @Override
    public int delete(PK modelPK) {
        return getMapper().delete(modelPK);
    }

    /**
     * 批量删除
     *
     * @throws Exception
     * @see com.lcxbs.core.IBaseService#batchDelete(String)
     */
    @ClearCache
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(String ids) throws Exception {
        int count = 0;
        if (null == ids || ids.length() == 0) {
            return 0;
        }
        String[] fileIds = null;
        if (ids.contains(",")) {
            fileIds = StringUtils.split(ids, ",");
        } else {
            fileIds = new String[1];
            fileIds[0] = ids;
        }
        for (String id : fileIds) {
            if (keyClass.getName().equals("java.lang.String")) {
                count = count + getMapper().delete(id);
            } else if (keyClass.getName().equals("java.lang.Long")) {
                count = count + getMapper().delete(Long.valueOf(id));
            } else if (keyClass.getName().equals("java.lang.Integer")) {
                count = count + getMapper().delete(Integer.valueOf(id));
            }
        }
        return count;
    }

    /**
     * 批量删除
     *
     * @throws Exception
     * @see com.lcxbs.core.IBaseService#batchDelete(List)
     */
    @ClearCache
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(List<Long> ids) throws Exception {
        int count = 0;
        for (Long id : ids) {
            count = count + getMapper().delete(id);
        }
        return count;
    }

    /**
     * @param model
     * @see com.lcxbs.core.IBaseService#delete(Object,
     * java.util.Map)
     */
    @ClearCache
    @Override
    public int deleteBy(T model) {
        return getMapper().delete("deleteByWhere", model);
    }

    /**
     * @param model
     * @see com.lcxbs.core.IBaseService#delete(Object,
     * java.util.Map)
     */
    @Deprecated
    @ClearCache
    @Override
    public int delete(T model, Map map) {
        return getMapper().delete("deleteByWhere", model);
    }

    /**
     * @see com.lcxbs.core.IBaseService#getModelByKey(Object)
     */
    @InsertCache
    @Override
    public T getModelByKey(PK modelPK) {
        return (T) getMapper().getModelByKey(modelPK);
    }

    /**
     * @see com.lcxbs.core.IBaseService#update(Object)
     */
    @ClearCache
    @Override
    public int update(T model) {
        return getMapper().update(model);
    }

    /**
     * @see com.lcxbs.core.IBaseService#updateSelective(Object)
     */
    @ClearCache
    @Override
    public int updateSelective(T model) {
        return getMapper().updateSelective(model);
    }

    /**
     * @see com.lcxbs.core.IBaseService#updateByMap(Object)
     */
    @ClearCache
    @Override
    public int updateByMap(T model) {
        return getMapper().updateByMap(model);
    }

    /**
     * @param model
     * @see com.lcxbs.core.IBaseService#countAll(Object)
     */
    @Override
    public Long countAll(T model) {
        return getMapper().countAll(model);
    }

    /**
     * @param model
     * @see com.lcxbs.core.IBaseService#getAllIds(Object)
     */
    @Override
    public List<PK> getAllIds(T model) {
        return getMapper().getAllIds(model);
    }

    /**
     * @see com.lcxbs.core.IBaseService#getList(Object)
     */
    @Override
    public List<T> getList(T model) {
        return getMapper().getList("getListByWhere", model);
    }

    /**
     * @see com.lcxbs.core.IBaseService#getList(Object,
     * java.util.Map)
     */
    @Override
    public List<T> getList(T model, Map map) {
        return getMapper().getList("getListByWhere", model);
    }

    /**
     * @param model
     * @see com.lcxbs.core.IBaseService#getListByPage(Object)
     */
    @Override
    public PageInfo<T> getListByPage(T model) {
        List<T> list = getMapper().getListByPage(model);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * @param model
     * @see com.lcxbs.core.IBaseService#getListByPage(Object, java.util.Map)
     */
    @Override
    public PageInfo<T> getListByPage(T model, Map map) {
        List<T> list = getMapper().getListByPage(model);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /**
     * @param model 需要构建对象
     * @return 成功返回true，失败返回false
     * @throws Exception
     * @see com.lcxbs.core.IBaseService#setupOrderByAndGroupBy(Object)
     */
    @Override
    public boolean setupOrderByAndGroupBy(T model) throws Exception {
        if (model instanceof AbstractBaseObject) {
            AbstractBaseObject baseObject = (AbstractBaseObject) model;
            String sortFieldName = this.getColumn(baseObject.getSortField());
            String groupFieldName = this.getColumn(baseObject.getGroupField());
            baseObject.getMap().put("orderBy", baseObject.getOrderBy(sortFieldName));
            baseObject.getMap().put("groupBy", baseObject.getGroupBy(groupFieldName));
            return true;
        }
        return false;
    }

    /**
     * 返回SqlSessionFactory
     *
     * @return
     */
    public SqlSessionFactory getSqlSessionFactory() {
        return null;
    }

    /**
     * @param propertys
     * @throws Exception
     * @see com.lcxbs.core.IBaseService#getColumn(String)
     */
    @Override
    public String getColumn(String propertys) throws Exception {
        if (null == propertys || propertys.trim().length() == 0) {
            return "";
        }
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        if (sqlSessionFactory == null) {
            Object beanObj = null;
            try {
                beanObj = SpringContextUtil.getBean(SqlSessionFactory.class);
            } catch (Exception e) {
            }
            if (null != beanObj) {
                if (beanObj instanceof DefaultSqlSessionFactory) {
                    sqlSessionFactory = (DefaultSqlSessionFactory) beanObj;
                } else if (beanObj instanceof SqlSessionFactoryBean) {
                    SqlSessionFactoryBean sfb = (SqlSessionFactoryBean) beanObj;
                    sqlSessionFactory = sfb.getObject();
                }
            }
        }
        if (null != sqlSessionFactory) {
            String id = getMapper().getEntityClass().getName() + ".BaseResultMap";
            ResultMap resultMap = sqlSessionFactory.getConfiguration().getResultMap(id);
            List<ResultMapping> resultMappings = resultMap.getResultMappings();
            List<String> propertyList = Arrays.asList(propertys.toLowerCase().split(","));
            List<String> columnList = new ArrayList<String>();
            for (ResultMapping mp : resultMappings) {
                if (propertyList.contains(mp.getProperty().toLowerCase().trim())) {
                    columnList.add(mp.getColumn());
                }
            }
            return org.apache.commons.lang3.StringUtils.join(columnList.toArray(), ",");
        }
        return "";
    }

    /**
     * @see com.lcxbs.core.IBaseService#getModelByWhere(Object)
     */
    @Override
    public T getModelByWhere(T model) {
        return (T) getMapper().getModelByWhere(model);
    }

    public Class<T> getBeanClass() {
        return beanClass;
    }

    public Class<PK> getKeyClass() {
        return keyClass;
    }

}
