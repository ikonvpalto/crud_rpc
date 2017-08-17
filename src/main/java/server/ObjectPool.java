package server;

import entities.Album;
import entities.Artist;
import entities.Genre;
import entities.Track;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import server.dao.DAO;
import server.dao.impl.AlbumDAO;
import server.dao.impl.ArtistDAO;
import server.dao.impl.GenreDAO;
import server.dao.impl.TrackDAO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ObjectPool {

    private static ObjectPool pool;
    public static ObjectPool getPool() {
        if (null == pool)
            pool = new ObjectPool();
        return pool;
    }

    private SessionFactory sessionFactory;
    private ServiceRegistry serviceRegistry;
    private Map<Class, DAO> entityDAO;


    private ObjectPool() {
        serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources( serviceRegistry ).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityDAO = new HashMap<>();
        entityDAO.put(Album.class, new AlbumDAO());
        entityDAO.put(Artist.class, new ArtistDAO());
        entityDAO.put(Track.class, new TrackDAO());
        entityDAO.put(Genre.class, new GenreDAO());
    }

    public void init() {}

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void destruct() {
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    @Override
    protected void finalize() throws Throwable {
        destruct();
        super.finalize();
    }

    public DAO getDAO(Class entity) {
        return entityDAO.get(entity);
    }
}
