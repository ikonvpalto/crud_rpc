package server.dao.impl;

import entities.Genre;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenreDAO extends DAO<Genre> {

    @Override
    public List<Genre> get(Genre pattern) {
        List<Genre> result;
        Session session = ObjectPool.getPool().getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Genre> criteria = builder.createQuery(Genre.class);
        Root<Genre> root = criteria.from(Genre.class);
        criteria.where(builder.like(root.get("name"), '%' + pattern.getName() + '%'));
        result = session.createQuery(criteria).list();

        session.close();
        return result;
    }

    @Override
    public Genre getById(Genre pattern) {
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Genre result = session.get(Genre.class, pattern.getId());
        session.getTransaction().commit();
        return result;
    }
}
