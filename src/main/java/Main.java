import entities.Album;
import server.dao.DAO;
import server.dao.impl.AlbumDAO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String... args) {
        Map<Class, DAO> map = new HashMap<>();
        map.put(Album.class, new AlbumDAO());
    }

}
