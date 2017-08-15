package server;

import com.sun.org.apache.regexp.internal.RE;
import entities.*;
import org.hibernate.Session;
import server.ObjectPool;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {

    int save(Object value) throws RemoteException;
    List get(Object pattern) throws RemoteException;
    Object getById(Object pattern) throws RemoteException;
    void update(Object newValue) throws RemoteException;
    void delete(Object value) throws RemoteException;

}
