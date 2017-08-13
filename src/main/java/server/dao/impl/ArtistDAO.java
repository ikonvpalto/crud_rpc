package server.dao.impl;

import entities.Artist;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import java.util.Collections;
import java.util.List;

public class ArtistDAO extends DAO<Artist> {


    @Override
    protected String toStringPattern(Artist pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getName())
            s.append("Artist.name like '%").append(pattern.getName()).append("%' ");
        if (0 != s.length())
            return s.toString();
        return "";
    }

    @Override
    public List<Artist> get(Artist pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        List<Artist> result;
        if (null != pattern && 0 < pattern.getId()) {
            result = Collections.emptyList();
            result.add(getById(pattern));
        } else {
            result = session.createQuery(toStringPattern(pattern), Artist.class).list();
        }
        session.close();
        return result;
    }

    @Override
    public Artist getById(Artist pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Artist result = session.get(Artist.class, pattern.getId());
        session.close();
        return result;
    }
}
