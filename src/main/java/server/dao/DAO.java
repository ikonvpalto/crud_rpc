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

    public abstract List<T> get(T pattern);

    public abstract T getById(T pattern);

    public void update(T newValue) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        session.beginTransaction();
        session.update(newValue);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T value) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(value);
        session.getTransaction().commit();
        session.close();
    }

}
