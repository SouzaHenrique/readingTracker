package java.com.br.dao;

import java.util.List;

public class DaoTenant implements Dao{
    @Override
    public boolean Save(Object object) {
        return false;
    }

    @Override
    public boolean Update(Object object) {
        return false;
    }

    @Override
    public boolean Delete(int id) {
        return false;
    }

    @Override
    public Object get(int id) {
        return null;
    }

    @Override
    public List<Object> get() {
        return null;
    }
}
