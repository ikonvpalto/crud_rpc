package server.dao.impl;

import entities.Artist;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArtistDAO extends DAO<Artist> {

    @Override
    public List<Artist> get(Artist pattern) {
        List<Artist> result;
        Session session = ObjectPool.getPool().getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Artist> criteria = builder.createQuery(Artist.class);
        Root<Artist> root = criteria.from(Artist.class);
        criteria.where(builder.like(root.get("name"), '%' + pattern.getName() + '%'));
        result = session.createQuery(criteria).list();

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
