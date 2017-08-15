package server.dao.impl;

import entities.Album;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class AlbumDAO extends DAO<Album> {

    @Override
    public List<Album> get(Album pattern) {
        List<Album> result;
        Session session = ObjectPool.getPool().getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Album> criteria = builder.createQuery(Album.class);
        Root<Album> root = criteria.from(Album.class);
        criteria.where(builder.like(root.get("title"), '%' + pattern.getTitle() + '%'));
        result = session.createQuery(criteria).list();

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