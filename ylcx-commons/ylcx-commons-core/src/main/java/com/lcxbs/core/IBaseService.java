package com.lcxbs.core;

import com.lcxbs.cache.ClearCache;
import com.lcxbs.cache.InsertCache;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: IBaseService.java</p>
 * <p>
 * Description:服务接口</p>
 * <p>
 * Copyright: Copyright (c) 2014</p>
 *
 * @author fanlianwei
 * @param <T> 实体对象
 * @param <PK> 实体对象主键类型
 * @date 2014-8-11 9:55:20
 * @version V1.0
 */
public interface IBaseService<T, PK> {

    /**
     * 插入信息
     *
     * @param model 信息内容
     * @return
     */
    int insert(T model);

    /**
     * 删除信息
     *
     * @param modelPK 信息主键
     * @return
     */
    @ClearCache
    int delete(PK modelPK);

    /**
     * 批量删除
     *
     * @param ids 待删除信息ID
     * @return
     * @throws Exception
     */
    @ClearCache
    int batchDelete(String ids) throws Exception;

    /**
     * 批量删除
     *
     * @param ids 待删除信息ID
     * @return
     * @throws Exception
     */
    @ClearCache
    int batchDelete(List<Long> ids) throws Exception;

    /**
     * 条件删除
     *
     * @param model
     * @param map
     * @return
     */
    @Deprecated
    @ClearCache
    int delete(T model, Map map);

    /**
     * 条件删除
     *
     * @param model
     * @return
     */
    @ClearCache
    int deleteBy(T model);

    /**
     * 根据主键获取信息
     *
     * @param modelPK 信息主键
     * @return
     */
    @InsertCache
    T getModelByKey(PK modelPK);

    /**
     * 更新所有列
     *
     * @param model 需更新的信息
     * @return
     */
    @ClearCache
    int update(T model);

    /**
     * 精确更新
     *
     * @param model 需更新的信息
     * @return
     */
    @ClearCache
    int updateSelective(T model);

    /**
     * 根据Map集合的条件更新
     *
     * @param model 需更新的信息
     * @return
     */
    @ClearCache
    int updateByMap(T model);

    /**
     * 获取记录数
     *
     * @param model
     * @return
     */
    Long countAll(T model);

    /**
     * 获取所有主键
     *
     * @param model
     * @return
     */
    List<PK> getAllIds(T model);

    /**
     * 获取信息列表
     *
     * @param model 条件信息
     * @return
     */
    List<T> getList(T model);

    /**
     * 获取信息列表
     *
     * @param model 条件信息
     * @param map
     * @return
     */
    List<T> getList(T model, Map map);

    /**
     * 分页获取数据
     *
     * @param model
     * @return
     */
    PageInfo<T> getListByPage(T model);

    /**
     * 分页获取数据
     *
     * @param model
     * @param map
     * @return
     */
    PageInfo<T> getListByPage(T model, Map map);

    /**
     * 根据条件获取对象
     *
     * @param model 条件
     * @return
     */
    T getModelByWhere(T model);

    /**
     * 构建orderBy和groupBy 语句存入当前传入对象的map属性中，键分别为：orderBy和groupBy
     * @param model 需要构建对象
     * @return 成功返回true，失败返回false
     * @throws Exception
     */
    boolean setupOrderByAndGroupBy(T model)throws Exception;

    /**
     * 将字段属性转换为对应的数据库字段(支持带英文逗号分隔的多个字段属性同时分隔，例如：userName,userSex)
     *
     * @param propertys 字段属性(支持带英文逗号分隔的多个字段属性同时分隔，例如：userName,userSex)
     * @return 对应的数据库字段，多个以英文逗号分隔
     * @throws Exception
     */
    String getColumn(String propertys)throws Exception;
}
