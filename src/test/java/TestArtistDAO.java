import entities.Artist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.ObjectPool;
import server.dao.impl.ArtistDAO;

import java.util.List;

public class TestArtistDAO extends Assert {

    private Artist[] artists = new Artist[] {
            new Artist("1"),
            new Artist("2"),
            new Artist("3"),
            new Artist("31"),
            new Artist("3"),
            new Artist("6"),
            new Artist("7"),
            new Artist("8"),
            new Artist("9"),
            new Artist("10")
    };
    private ArtistDAO dao;

    @Before
    public void setUp() {
        dao = (ArtistDAO) ObjectPool.getPool().getDAO(Artist.class);
    }

    @Test
    public void testSave() {
        dao.save(artists[0]);
        assertNotEquals(-1, artists[0].getId());
    }

    @Test
    public void testGet() {
        dao.save(artists[1]);
        dao.save(artists[2]);
        dao.save(artists[3]);
        List<Artist> tr = dao.get(artists[4]);
        tr.forEach(System.out::println);
        assertTrue(2 == tr.size());
        artists[4].setName("2");
        tr = dao.get(artists[4]);
        assertTrue(1 == tr.size());
        tr.forEach(System.out::println);
    }

    @Test
    public void testGetById() {
        dao.save(artists[5]);
        dao.save(artists[6]);
        artists[7].setId(artists[5].getId());
        System.out.println(artists[5]);
        Artist tr = dao.getById(artists[7]);
        System.out.println(tr);
        assertTrue(tr.equals(artists[5]));
    }

    @Test
    public void testUpdate() {
        dao.save(artists[8]);
        artists[8].setName("hui");
        dao.update(artists[8]);
        assertTrue(artists[8].equals(dao.getById(artists[8])));
    }

    @Test
    public void testDelete() {
        dao.save(artists[9]);
        dao.delete(artists[9]);
        assertTrue(0 == dao.get(artists[9]).size());
    }

    @After
    public void tearDown() {
    }

}
