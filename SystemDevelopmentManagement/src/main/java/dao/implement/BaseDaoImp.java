package dao.implement;

import bean.Page;
import dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.List;

public class BaseDaoImp<T> implements BaseDao<T> {
    private SessionFactory sessionFactory;

    private Page<T> page;

    public BaseDaoImp() {
    }

    private SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    @SuppressWarnings("unchecked")
    public T getById(Class<T> entityClass, Object id) {
        List list;
        try {
            list = this.getSessionFactory().getCurrentSession().createQuery("from " + entityClass.getSimpleName() + " en where en.id=?1").setParameter(1, entityClass.getDeclaredField("id").getType().cast(id)).getResultList();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
        return list.size() == 0 ? null : (T) list.get(0);
    }

    public Serializable save(T entity) {
        return getSessionFactory().getCurrentSession().save(entity);
    }

    @Override
    public boolean update(T entity) {
        getSessionFactory().getCurrentSession().update(entity);
        return true;
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSessionFactory().getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    @Override
    public Boolean delete(Class<T> entityClass, Object id) {
        try {
            getSessionFactory().getCurrentSession().createQuery("delete from " + entityClass.getSimpleName() + " en where en.id=?1").setParameter(1, entityClass.getDeclaredField("id").getType().cast(id)).executeUpdate();
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll(Class<T> entityClass) {
        return getSessionFactory().getCurrentSession().createQuery("from " + entityClass.getSimpleName()).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page getAllByPage(Class<T> entityClass, int pageNumber, int pageSize) {
        page.setCurrentPage(pageNumber);
        page.setPageSize(pageSize);
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("select count(en) from " + entityClass.getSimpleName() + " en");
        page.setTotalRecords(Integer.parseInt(String.valueOf(query.getSingleResult())));
        page.setTotalPageNo(Math.round(Math.ceil(page.getTotalRecords() / (double) pageSize)));
        query = session.createQuery("from " + entityClass.getSimpleName());
        query.setMaxResults(pageSize);
        page.setData(query.setFirstResult(pageNumber - 1).setMaxResults(pageSize).getResultList());
        return page;
    }

    @Override
    public long count(Class<T> entityClass) {
        return Integer.parseInt(String.valueOf(this.getSessionFactory().getCurrentSession().createQuery("select count(*) from " + entityClass.getSimpleName()).getSingleResult()));
    }

    @Override
    public boolean batchToSave(List<T> entities) {
        for (int i = 0; i < entities.size(); ++i) {
            sessionFactory.getCurrentSession().save(entities.get(i));
            if (i % 20 == 0) {
                sessionFactory.getCurrentSession().flush();
                sessionFactory.getCurrentSession().clear();
            }
        }
        return true;
    }

    @Override
    public boolean batchToUpdate(List<T> entities) {
        for (T entity : entities) {
            sessionFactory.getCurrentSession().update(entity);
        }
        return true;
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    @Override
    public boolean batchToUpdateByIdentifierProperty(List<T> entities) {
        sessionFactory.getCurrentSession().doWork(connection -> {
            AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entities.getClass().getGenericSuperclass().getClass());
            StringBuilder sql = new StringBuilder("update " + abstractEntityPersister.getTableName() + " set ");
            String[] columnNames = abstractEntityPersister.getKeyColumnNames();
            for (int i = 0, length = columnNames.length; i < length; ++i) {
                sql.append(columnNames[i]).append(" = ?");
                if (i != length - 1) sql.append(",");
            }
            String[] identifierColumnNames = abstractEntityPersister.getIdentifierColumnNames();
            sql.append(" WHERE ").append(identifierColumnNames[0]).append(" =? ");
            for (int i = 1, length = identifierColumnNames.length; i < length; ++i)
                sql.append("AND ").append(identifierColumnNames[i]).append(" =? ");
            PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(sql));
            Class clazz = abstractEntityPersister.getMappedClass();
            String[] propertyNames = abstractEntityPersister.getPropertyNames();
            String id = abstractEntityPersister.getIdentifierPropertyName();
            for (T entity : entities) {
                int i = 0;
                for (int length = propertyNames.length; i < length; ++i) {
                    try {
                        preparedStatement.setObject(i + 1, clazz.getDeclaredMethod("get" + propertyNames[i].replaceFirst(propertyNames[i].substring(0, 1), propertyNames[i].substring(0, 1).toUpperCase())).invoke(entity));
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    preparedStatement.setObject(++i, clazz.getDeclaredMethod("get" + id.replaceFirst(id.substring(0, 1), id.substring(0, 1).toUpperCase())).invoke(entity));
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                preparedStatement.addBatch();
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
        });
        return true;
    }

    @SuppressWarnings("deprecation")
    public boolean batchToDeleteIdentifierProperty(Class<T> entityClass, List<Object> id) {
        sessionFactory.getCurrentSession().doWork(connection -> {
            //System.out.println("ids:" + Arrays.toString(id.toArray()));
            AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entityClass);
            String[] identifierColumnName = abstractEntityPersister.getIdentifierColumnNames();
            StringBuffer sql = new StringBuffer(String.format("DELETE FROM %s en WHERE %s =? ", abstractEntityPersister.getTableName(), identifierColumnName[0]));
            for (int i = 1, length = identifierColumnName.length; i < length; ++i)
                sql.append("AND ").append(identifierColumnName[i]).append("=? ");
            PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(sql));
//            System.out.println("IDName:"+abstractEntityPersister.getIdentifierPropertyName());
            for (Object i : id) {
                preparedStatement.setObject(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
        });
        return true;
    }

    public boolean batchToDelete(Class<T> entityClass, List<Object> id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from " + entityClass.getSimpleName() + " en where en.id=?1");
        try {
            Class<?> type = entityClass.getDeclaredField("id").getType();
            for (Object i : id) {
                query.setParameter(1, type.cast(i));
            }
            query.executeUpdate();
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }
}
