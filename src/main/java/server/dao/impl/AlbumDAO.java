package server.dao.impl;

import entities.Album;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import java.util.Collections;
import java.util.List;

public class AlbumDAO extends DAO<Album> {

    @Override
    protected String toStringPattern(Album pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getTitle() && 0 != pattern.getTitle().length())
            s.append("Album.title like '%").append(pattern.getTitle()).append("%' ");
        if (0 != s.length())
            return s.toString();
        return "";
    }

    @Override
    public List<Album> get(Album pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        List<Album> result;
        if (null != pattern && 0 < pattern.getId()) {
            result = Collections.emptyList();
            result.add(getById(pattern));
        } else {
            result = session.createQuery(toStringPattern(pattern), Album.class).list();
        }
        session.close();
        return result;
    }

    @Override
    public Album getById(Album pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Album result = session.get(Album.class, pattern.getId());
        session.close();
        return result;
    }

}