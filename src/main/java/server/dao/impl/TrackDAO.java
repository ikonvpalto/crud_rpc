package server.dao.impl;

import entities.Track;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TrackDAO extends DAO<Track> {

    @Override
    public List<Track> get(Track pattern) {
        List<Track> result;
        Session session = ObjectPool.getPool().getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Track> criteria = builder.createQuery(Track.class);
        Root<Track> root = criteria.from(Track.class);
        criteria.where(builder.like(root.get("title"), '%' + pattern.getTitle() + '%'));
        result = session.createQuery(criteria).list();

        session.close();
        return result;
    }

    @Override
    public Track getById(Track pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Track result = session.get(Track.class, pattern.getId());
        session.getTransaction().commit();
        return result;
    }
}
