import entities.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.ObjectPool;
import server.dao.impl.TrackDAO;

import java.util.List;

public class TestTrackDAO extends Assert {

    private Track[] tracks = new Track[] {
            new Track("1"),
            new Track("2"),
            new Track("3"),
            new Track("31"),
            new Track("3"),
            new Track("6"),
            new Track("7"),
            new Track("8"),
            new Track("9"),
            new Track("10")
    };
    private TrackDAO dao;

    @Before
    public void setUp() {
        dao = (TrackDAO) ObjectPool.getPool().getDAO(Track.class);
    }

    @Test
    public void testSave() {
        dao.save(tracks[0]);
        assertNotEquals(-1, tracks[0].getId());
    }

    @Test
    public void testGet() {
        dao.save(tracks[1]);
        dao.save(tracks[2]);
        dao.save(tracks[3]);
        List<Track> tr = dao.get(tracks[4]);
        tr.forEach(System.out::println);
        assertTrue(2 == tr.size());
        tracks[4].setTitle("2");
        tr = dao.get(tracks[4]);
        assertTrue(1 == tr.size());
        tr.forEach(System.out::println);
    }

    @Test
    public void testGetById() {
        dao.save(tracks[5]);
        dao.save(tracks[6]);
        tracks[7].setId(tracks[5].getId());
        System.out.println(tracks[5]);
        Track tr = dao.getById(tracks[7]);
        System.out.println(tr);
        assertTrue(tr.equals(tracks[5]));
    }

    @Test
    public void testUpdate() {
        dao.save(tracks[8]);
        tracks[8].setTitle("hui");
        dao.update(tracks[8]);
        assertTrue(tracks[8].equals(dao.getById(tracks[8])));
    }

    @Test
    public void testDelete() {
        dao.save(tracks[9]);
        dao.delete(tracks[9]);
        assertTrue(0 == dao.get(tracks[9]).size());
    }

    @After
    public void tearDown() {
    }

}
