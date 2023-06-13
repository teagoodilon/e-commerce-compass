package tables;

import java.util.List;

public interface Dao {
    public void insert (Object obj);
    public void update (Object obj);
    public void delete (Object obj);
    public  Object select(Integer i);
    public List<Object> select();
}
