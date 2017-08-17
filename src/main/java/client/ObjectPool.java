package client;

import commons.Commons;
import commons.InitializationException;
import server.ServerInterface;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ObjectPool {

    private static ObjectPool pool;
    public static ObjectPool getPool() {
        if (null == pool)
            pool = new ObjectPool();
        return pool;
    }

    private ServerInterface serverInterface;

    private ObjectPool() {}

    public void init() throws InitializationException{
        try {
            Socket socket = new Socket("localhost", Commons.SERVER_PORT);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            serverInterface = (ServerInterface) in.readObject();
            socket.close();
        } catch (Throwable e) {
            throw new InitializationException(e);
        }
    }

    public ServerInterface getServerInterface() {
        return serverInterface;
    }
}
