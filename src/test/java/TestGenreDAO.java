import entities.Genre;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.ObjectPool;
import server.dao.impl.GenreDAO;

import java.util.List;

public class TestGenreDAO extends Assert {

    private Genre[] genres = new Genre[] {
            new Genre("1"),
            new Genre("2"),
            new Genre("3"),
            new Genre("31"),
            new Genre("3"),
            new Genre("6"),
            new Genre("7"),
            new Genre("8"),
            new Genre("9"),
            new Genre("10")
    };
    private GenreDAO dao;

    @Before
    public void setUp() {
        dao = (GenreDAO) ObjectPool.getPool().getDAO(Genre.class);
    }

    @Test
    public void testSave() {
        dao.save(genres[0]);
        assertNotEquals(-1, genres[0].getId());
    }

    @Test
    public void testGet() {
        dao.save(genres[1]);
        dao.save(genres[2]);
        dao.save(genres[3]);
        List<Genre> tr = dao.get(genres[4]);
        tr.forEach(System.out::println);
        assertTrue(2 == tr.size());
        genres[4].setName("2");
        tr = dao.get(genres[4]);
        assertTrue(1 == tr.size());
        tr.forEach(System.out::println);
    }

    @Test
    public void testGetById() {
        dao.save(genres[5]);
        dao.save(genres[6]);
        genres[7].setId(genres[5].getId());
        System.out.println(genres[5]);
        Genre tr = dao.getById(genres[7]);
        System.out.println(tr);
        assertTrue(tr.equals(genres[5]));
    }

    @Test
    public void testUpdate() {
        dao.save(genres[8]);
        genres[8].setName("hui");
        dao.update(genres[8]);
        assertTrue(genres[8].equals(dao.getById(genres[8])));
    }

    @Test
    public void testDelete() {
        dao.save(genres[9]);
        dao.delete(genres[9]);
        assertTrue(0 == dao.get(genres[9]).size());
    }

    @After
    public void tearDown() {
    }

}
