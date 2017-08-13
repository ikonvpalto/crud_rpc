package server.db;

import server.ObjectPool;

import java.util.List;

public class ServerInterfaceImpl implements ServerInterface {

    @Override
    public void save(Object value) {
        ObjectPool.getPool().getDAO(value.getClass()).save(value);
    }

    @Override
    public List get(Object pattern) {
        return ObjectPool.getPool().getDAO(pattern.getClass()).get(pattern);
    }

    @Override
    public Object getById(Object pattern) {
        return ObjectPool.getPool().getDAO(pattern.getClass()).getById(pattern);
    }

    @Override
    public void update(Object newValue) {
        ObjectPool.getPool().getDAO(newValue.getClass()).update(newValue);
    }

    @Override
    public void delete(Object value) {
        ObjectPool.getPool().getDAO(value.getClass()).delete(value);
    }
}
