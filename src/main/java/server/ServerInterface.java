package server;

import entities.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {

    int save(Object value) throws RemoteException;
    List get(Object pattern) throws RemoteException;
    Object getById(Object pattern) throws RemoteException;
    boolean update(Object newValue) throws RemoteException;
    boolean delete(Object value) throws RemoteException;
    boolean setTrackAlbum(Track track, Album album) throws RemoteException;
    boolean setAlbumArtist(Album album, Artist artist) throws RemoteException;
    boolean addGenreToTrack(Track track, Genre genre) throws RemoteException;
    boolean removeGenreFromTrack(Track track, Genre genre) throws RemoteException;

}
