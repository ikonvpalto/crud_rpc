package server.dao;

import org.hibernate.Session;
import server.ObjectPool;

import java.util.List;

public abstract class DAO<T> {

    public int save(T value) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        session.beginTransaction();
        int id = (Integer) session.save(value);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public List<T> get(T pattern) {
        StringBuilder s = new StringBuilder()
                .append("from ")
                .append(pattern.getClass().getSimpleName());
        String where = getWhereStatement(pattern);
        if (0 != where.length())
            s.append(" where ").append(where);

        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        List result = session.createQuery(s.toString()).list();
        session.close();
        return result;
    }

    public T getById(T pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        T result = (T) session.get(pattern.getClass(), getId(pattern));
        session.close();
        return result;
    }

    public boolean update(T newValue) {
        String set = getSetStatement(newValue);
        int id = getId(newValue);
        if (1 > id || 0 == set.length())
            return false;
        StringBuilder s = new StringBuilder()
                .append("update ")
                .append(newValue.getClass().getSimpleName())
                .append(" set")
                .append(set)
                .append(" where id=")
                .append(id);

        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        int result = session.createQuery(s.toString()).executeUpdate();
        session.close();
        return 1 == result;
    }

    public boolean delete(T value) {
        if (null != getById(value)) {
            Session session = ObjectPool.getPool().getSessionFactory().openSession();
            session.delete(value);
            session.close();
            return true;
        }
        return false;
    }

    protected abstract String getWhereStatement(T pattern);

    protected abstract String getSetStatement(T pattern);

    protected abstract int getId(T value);
}
