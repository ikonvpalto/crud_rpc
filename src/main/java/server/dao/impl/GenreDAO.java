package server.dao.impl;

import entities.Genre;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import java.util.Collections;
import java.util.List;

public class GenreDAO extends DAO<Genre> {
    @Override
    protected String toStringPattern(Genre pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getName())
            s.append(" Genre.name like '%").append(pattern.getName()).append("%'");
        if (0 != s.length())
            return s.toString();
        return "";
    }

    @Override
    public List<Genre> get(Genre pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        List<Genre> result;
        if (null != pattern && 0 < pattern.getId()) {
            result = Collections.emptyList();
            result.add(getById(pattern));
        } else {
            result = session.createQuery(toStringPattern(pattern), Genre.class).list();
        }
        session.close();
        return result;
    }

    @Override
    public Genre getById(Genre pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Genre result = session.get(Genre.class, pattern.getId());
        session.close();
        return result;
    }
}
