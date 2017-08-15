package server;

import commons.Commons;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private static List<ServerInterface> interfaces;

    public static void main(String... args) {
        interfaces = new LinkedList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(Commons.SERVER_PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                interfaces.add(new ServerInterfaceImpl());
                ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(interfaces.get(interfaces.size() - 1), 0);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(stub);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
