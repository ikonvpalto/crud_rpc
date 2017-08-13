package server.db;

import entities.*;
import org.hibernate.Session;
import server.ObjectPool;

import java.util.List;

public interface ServerInterface {

    void save(Object value);
    List get(Object pattern);
    Object getById(Object pattern);
    void update(Object newValue);
    void delete(Object value);

}
