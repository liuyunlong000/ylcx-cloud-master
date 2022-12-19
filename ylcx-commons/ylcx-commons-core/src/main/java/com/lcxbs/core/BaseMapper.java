package com.lcxbs.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Mapper公共实现
 *
 * @author fanlianwei
 *
 * @param <T> 实体对象
 * @param <PK> 主键类型
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class BaseMapper<T, PK extends Serializable> extends DaoSupport implements IEntityDao<T, PK> {

    protected final Log log = LogFactory.getLog(getClass());

    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;

    public BaseMapper() {
        super();
    }

    @Override
    protected void checkDaoConfig() throws IllegalArgumentException {
       
    }
 
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 获取数据条数
     * @param model
     */
    @Override
    public Long countAll(T model) {
        return (Long) getSqlSessionTemplate().selectOne(getEntityClass().getName() + ".countAll", model);
    }

    /**
     * 获取对象信息
     */
    @Override
    public Object getObject(String statement, T model) {
        return getSqlSessionTemplate().selectOne(getEntityClass().getName() + "." + statement, model);
    }

    /**
     * 删除数据
     *
     * @param modelPK 主键
     */
    @Override
    public int delete(PK modelPK) {
        return getSqlSessionTemplate().delete(getEntityClass().getName() + ".delete", modelPK);
    }

    /**
     * 条件删除
     *
     * @param statement Mapper中定义的ID
     * @return
     */
    @Override
    public int delete(String statement, T model) {
        return getSqlSessionTemplate().delete(getEntityClass().getName() + "." + statement, model);
    }

    /**
     * 获取所有主键
     */
    @Override
    public List<PK> getAllIds(T model) {
        List selectList = getSqlSessionTemplate().selectList(getEntityClass().getName() + ".getAllIds", model);
        return selectList;
    }

    /**
     * 获取数据
     *
     * @param modelPK 主键
     */
    @Override
    public T getModelByKey(PK modelPK) {
        return (T) getSqlSessionTemplate().selectOne(getEntityClass().getName() + ".getModelByKey", modelPK);
    }

    /**
     * 插入数据
     */
    @Override
    public int insert(T model) {
        return getSqlSessionTemplate().insert(getEntityClass().getName() + ".insert", model);
    }

    /**
     * 更新全部
     */
    @Override
    public int update(T model) {
        return getSqlSessionTemplate().update(getEntityClass().getName() + ".updateByPrimaryKey", model);
    }

    /**
     * 自定义更新
     */
    @Override
    public int update(String statement, T model) {
        return getSqlSessionTemplate().update(getEntityClass().getName() + "." + statement, model);
    }

    /**
     * 选择更新
     */
    @Override
    public int updateSelective(T model) {
        return getSqlSessionTemplate().update(getEntityClass().getName() + ".updateSelective", model);
    }

    /**
     * 根据Map集合的条件更新
     */
    @Override
    public int updateByMap(T model) {
        return getSqlSessionTemplate().update(getEntityClass().getName() + ".updateByMap", model);
    }

    /**
     * 获取列表信息
     *
     * @param statement Mapper中定义的ID
     * @return
     */

    @Override
    public List<T> getList(String statement, T model) {
        return getSqlSessionTemplate().selectList(getEntityClass().getName() + "." + statement, model);
    }

    /**
     * 获取实体信息
     *
     * @param statement Mapper中定义的ID
     * @return
     */
    @Override
    public T getModel(String statement, T model) {
        return (T) getSqlSessionTemplate().selectOne(getEntityClass().getName() + "." + statement, model);
    }

    /**
     * 根据条件获取实体信息
     *
     * @param model 条件
     * @return
     */
    @Override
    public T getModelByWhere(T model) {
        return (T) getSqlSessionTemplate().selectOne(getEntityClass().getName() + ".getModelByWhere", model);
    }

    /**
     * 分页信息
     */
    @Override
    public List<T> getListByPage(T model) {
        List selectList = getSqlSessionTemplate().selectList(getEntityClass().getName() + ".getListByPage", model);
        return selectList;
    }

    public abstract Class getEntityClass();

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public static class SqlSessionTemplate {

        SqlSessionFactory sqlSessionFactory;

        public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
            this.sqlSessionFactory = sqlSessionFactory;
        }

        public Object execute(SqlSessionCallback action) {
            SqlSession session = null;
            try {
                session = sqlSessionFactory.openSession();
                Object result = action.doInSession(session);
                return result;
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }

        public Object selectOne(final String statement, final Object parameter) {
            return execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.selectOne(statement, parameter);
                }
            });
        }

        public List selectList(final String statement, final Object parameter) {
            return (List) execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.selectList(statement, parameter, new RowBounds());
                }
            });
        }

        public int delete(final String statement, final Object parameter) {
            return (Integer) execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.delete(statement, parameter);
                }
            });
        }

        public int update(final String statement, final Object parameter) {
            return (Integer) execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.update(statement, parameter);
                }
            });
        }

        public int insert(final String statement, final Object parameter) {
            return (Integer) execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.insert(statement, parameter);
                }
            });
        }
    }

    public static interface SqlSessionCallback {
        public Object doInSession(SqlSession session);
    }
}
