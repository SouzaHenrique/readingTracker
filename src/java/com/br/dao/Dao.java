package java.com.br.dao;

import java.util.List;

public interface Dao {

    public boolean Save(Object object);

    public boolean Update(Object object);

    public boolean Delete(int id);

    public Object get(int id);

    public List<Object> get();

}
