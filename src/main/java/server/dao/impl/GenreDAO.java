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
    protected String getWhereStatement(Genre pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getName())
            s.append(" Genre.title = ")
             .append(pattern.getName());
        return s.toString();
    }

    @Override
    protected String getSetStatement(Genre pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getName())
            s.append(" Genre.title = ")
             .append(pattern.getName());
        return s.toString();
    }

    @Override
    protected int getId(Genre value) {
        return value.getId();
    }
}
