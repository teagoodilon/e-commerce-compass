package tables;

import java.sql.SQLException;
import java.util.List;

public interface Dao {
    public void insert (Object obj) throws SQLException;
    public boolean update (Object obj, Integer i) throws SQLException;
    public boolean delete (Integer i) throws SQLException;
    public  Object select(Integer i) throws SQLException;
    public List<Object> select() throws SQLException;
}
