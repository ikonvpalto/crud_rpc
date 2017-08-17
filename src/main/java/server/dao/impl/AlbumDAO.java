package server.dao.impl;

import entities.Album;
import entities.Artist;
import org.hibernate.Session;
import server.ObjectPool;
import server.dao.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AlbumDAO extends DAO<Album> {

    @Override
    protected String getWhereStatement(Album pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getTitle())
            s.append(" Album.title = ")
                    .append(pattern.getTitle());
        if (null != pattern.getArtist() && 0 < pattern.getArtist().getId())
            s.append(" Album.album.artist.id = ")
             .append(pattern.getArtist().getId());
        return s.toString();
    }

    @Override
    protected String getSetStatement(Album pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getTitle())
            s.append(" Album.title = ")
                    .append(pattern.getTitle());
        return s.toString();
    }

    @Override
    protected int getId(Album value) {
        return value.getId();
    }

    public boolean setArtist(Album album, Artist artist) {
        if (null == album || null == artist || 1 > album.getId() || 1 > artist.getId())
            return false;
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Album fullAlbum = session.get(Album.class, album.getId());
        Artist fullArtist = session.get(Artist.class, artist.getId());
        fullAlbum.setArtist(fullArtist);
        session.update(fullAlbum);
        session.close();
        return true;
    }
    
}