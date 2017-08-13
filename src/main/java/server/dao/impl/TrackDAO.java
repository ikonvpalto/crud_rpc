package server.dao.impl;

import entities.Track;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import java.util.Collections;
import java.util.List;

public class TrackDAO extends DAO<Track> {
    @Override
    protected String toStringPattern(Track pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getTitle())
            s.append("Track.title like '%").append(pattern.getTitle()).append("%' ");
        if (0 != s.length())
            return s.toString();
        return "";
    }

    @Override
    public List<Track> get(Track pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        List<Track> result;
        if (null != pattern && 0 < pattern.getId()) {
            result = Collections.emptyList();
            result.add(getById(pattern));
        } else {
            result = session.createQuery(toStringPattern(pattern), Track.class).list();
        }
        session.close();
        return result;
    }

    @Override
    public Track getById(Track pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Track result = session.get(Track.class, pattern.getId());
        session.close();
        return result;
    }
}
