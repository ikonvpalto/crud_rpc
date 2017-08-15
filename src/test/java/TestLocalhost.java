import commons.Commons;
import entities.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.ServerInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;

public class TestLocalhost extends Assert{

    private ServerInterface serverInterface;
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

    @Before
    public void setUp() throws IOException, ClassNotFoundException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, Commons.SERVER_PORT);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        serverInterface = (ServerInterface) in.readObject();
        socket.close();
    }

    @Test
    public void testSave() throws RemoteException {
        serverInterface.save(tracks[0]);
        Track tr = (Track) (serverInterface.get(tracks[0]).get(1));
        assertEquals(tr.getTitle(), tracks[0].getTitle());
    }

    @Test
    public void testGet() throws RemoteException {
        tracks[1].setId(serverInterface.save(tracks[1]));
        tracks[2].setId(serverInterface.save(tracks[2]));
        tracks[3].setId(serverInterface.save(tracks[3]));
        List<Track> tr = serverInterface.get(tracks[4]);
        tr.forEach(System.out::println);
        assertTrue(2 == tr.size());
        tracks[4].setTitle("2");
        tr = serverInterface.get(tracks[4]);
        assertTrue(1 == tr.size());
        tr.forEach(System.out::println);
    }

    @Test
    public void testGetById() throws RemoteException {
        tracks[5].setId(serverInterface.save(tracks[5]));
        tracks[6].setId(serverInterface.save(tracks[6]));
        tracks[7].setId(tracks[5].getId());
        System.out.println(tracks[5]);
        Track tr = (Track) serverInterface.getById(tracks[7]);
        System.out.println(tr);
        assertTrue(tr.equals(tracks[5]));
    }

    @Test
    public void testUpdate() throws RemoteException {
        tracks[8].setId(serverInterface.save(tracks[8]));
        tracks[8].setTitle("hui");
        serverInterface.update(tracks[8]);
        assertTrue(tracks[8].equals(serverInterface.getById(tracks[8])));
    }

    @Test
    public void testDelete() throws RemoteException {
        tracks[9].setId(serverInterface.save(tracks[9]));
        serverInterface.delete(tracks[9]);
        assertTrue(0 == serverInterface.get(tracks[9]).size());
    }

    @After
    public void tearDown() {}

}
