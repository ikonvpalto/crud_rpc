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
    protected String getWhereStatement(Artist pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getName())
            s.append(" Artist.title = ")
                    .append(pattern.getName());
        return s.toString();
    }

    @Override
    protected String getSetStatement(Artist pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getName())
            s.append(" Artist.title = ")
                    .append(pattern.getName());
        return s.toString();
    }

    @Override
    protected int getId(Artist value) {
        return value.getId();
    }
}
