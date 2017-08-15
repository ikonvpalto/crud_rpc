import entities.Album;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.ObjectPool;
import server.dao.impl.AlbumDAO;

import java.util.List;

public class TestAlbumDAO extends Assert {

    private Album[] albums = new Album[] {
            new Album("1"),
            new Album("2"),
            new Album("3"),
            new Album("31"),
            new Album("3"),
            new Album("6"),
            new Album("7"),
            new Album("8"),
            new Album("9"),
            new Album("10")
    };
    private AlbumDAO dao;

    @Before
    public void setUp() {
        dao = (AlbumDAO) ObjectPool.getPool().getDAO(Album.class);
    }

    @Test
    public void testSave() {
        dao.save(albums[0]);
        assertNotEquals(-1, albums[0].getId());
    }

    @Test
    public void testGet() {
        dao.save(albums[1]);
        dao.save(albums[2]);
        dao.save(albums[3]);
        List<Album> tr = dao.get(albums[4]);
        tr.forEach(System.out::println);
        assertTrue(2 == tr.size());
        albums[4].setTitle("2");
        tr = dao.get(albums[4]);
        assertTrue(1 == tr.size());
        tr.forEach(System.out::println);
    }

    @Test
    public void testGetById() {
        dao.save(albums[5]);
        dao.save(albums[6]);
        albums[7].setId(albums[5].getId());
        System.out.println(albums[5]);
        Album tr = dao.getById(albums[7]);
        System.out.println(tr);
        assertTrue(tr.equals(albums[5]));
    }

    @Test
    public void testUpdate() {
        dao.save(albums[8]);
        albums[8].setTitle("hui");
        dao.update(albums[8]);
        assertTrue(albums[8].equals(dao.getById(albums[8])));
    }

    @Test
    public void testDelete() {
        dao.save(albums[9]);
        dao.delete(albums[9]);
        assertTrue(0 == dao.get(albums[9]).size());
    }

    @After
    public void tearDown() {
    }

}
