package server;

import entities.Album;
import entities.Artist;
import entities.Genre;
import entities.Track;
import server.dao.DAO;
import server.dao.impl.AlbumDAO;
import server.dao.impl.TrackDAO;

import java.rmi.RemoteException;
import java.util.List;

public class ServerInterfaceImpl implements ServerInterface {

    @Override
    public int save(Object value) {
        return ObjectPool.getPool().getDAO(value.getClass()).save(value);
    }

    @Override
    public List get(Object pattern) {
        return ObjectPool.getPool().getDAO(pattern.getClass()).get(pattern);
    }

    @Override
    public Object getById(Object pattern) {
        return ObjectPool.getPool().getDAO(pattern.getClass()).getById(pattern);
    }

    @Override
    public boolean update(Object newValue) {
        return ObjectPool.getPool().getDAO(newValue.getClass()).update(newValue);
    }

    @Override
    public boolean delete(Object value) {
        return ObjectPool.getPool().getDAO(value.getClass()).delete(value);
    }

    @Override
    public boolean setTrackAlbum(Track track, Album album) throws RemoteException {
        return ((TrackDAO) ObjectPool.getPool().getDAO(track.getClass())).setAlbum(track, album);
    }

    @Override
    public boolean setAlbumArtist(Album album, Artist artist) throws RemoteException {
        return ((AlbumDAO) ObjectPool.getPool().getDAO(album.getClass())).setArtist(album, artist);
    }

    @Override
    public boolean addGenreToTrack(Track track, Genre genre) throws RemoteException {
        return ((TrackDAO) ObjectPool.getPool().getDAO(track.getClass())).addGenre(track, genre);
    }

    @Override
    public boolean removeGenreFromTrack(Track track, Genre genre) throws RemoteException {
        return ((TrackDAO) ObjectPool.getPool().getDAO(track.getClass())).removeGenre(track, genre);
    }
}
