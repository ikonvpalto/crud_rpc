package server.dao;

import org.hibernate.Session;
import server.ObjectPool;

import java.util.List;

public abstract class DAO<T> {

    protected abstract String toStringPattern(T pattern);

    public void save(T value) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        session.save(value);
        session.close();
    }

    public abstract List<T> get(T pattern);

    public abstract T getById(T pattern);

    public void update(T newValue) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        session.update(newValue);
        session.close();
    }

    public void delete(T value) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        session.delete(value);
        session.close();
    }

}
