package server.dao.impl;

import entities.Album;
import entities.Genre;
import entities.Track;
import org.hibernate.Session;
import org.hibernate.Transaction;
import server.ObjectPool;
import server.dao.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TrackDAO extends DAO<Track> {

    @Override
    protected String getWhereStatement(Track pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getTitle())
            s.append(" Track.title = ")
                    .append(pattern.getTitle());
        if (null != pattern.getAlbum()) {
            if (0 < pattern.getAlbum().getId())
                s.append(" Track.album.id = ")
                 .append(pattern.getAlbum().getId());
            if (null != pattern.getAlbum().getArtist() && 0 < pattern.getAlbum().getArtist().getId())
                s.append(" Track.album.artist.id = ")
                 .append(pattern.getAlbum().getArtist().getId());
        }
        return s.toString();
    }

    @Override
    protected String getSetStatement(Track pattern) {
        if (null == pattern)
            return "";
        StringBuilder s = new StringBuilder();
        if (null != pattern.getTitle())
            s.append(" Track.title = ")
             .append(pattern.getTitle());
        return s.toString();
    }

    @Override
    protected int getId(Track value) {
        return value.getId();
    }

    public boolean setAlbum(Track track, Album album) {
        if (null == track || null == album || 1 > track.getId() || 1 > album.getId())
            return false;
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Track fullTrack = session.get(Track.class, track.getId());
        Album fullAlbum = session.get(Album.class, album.getId());
        fullTrack.setAlbum(fullAlbum);
        session.update(fullTrack);
        session.close();
        return true;
    }

    public boolean addGenre(Track track, Genre genre) {
        if (null == track || null == genre || 1 > track.getId() || 1 > genre.getId())
            return false;
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Track fullTrack = session.get(Track.class, track.getId());
        Genre fullGenre = session.get(Genre.class, genre.getId());
        boolean result = fullTrack.addGenre(fullGenre);
        session.update(fullTrack);
        session.close();
        return result;
    }

    public boolean removeGenre(Track track, Genre genre) {
        if (null == track || null == genre || 1 > track.getId() || 1 > genre.getId())
            return false;
        Session session = ObjectPool.getPool().getSessionFactory().openSession();
        Track fullTrack = session.get(Track.class, track.getId());
        Genre fullGenre = session.get(Genre.class, genre.getId());
        boolean result = fullTrack.removeGenre(fullGenre);
        session.update(fullTrack);
        session.close();
        return result;
    }
}
