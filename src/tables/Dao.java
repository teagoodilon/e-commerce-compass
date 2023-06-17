package tables;

import java.sql.SQLException;
import java.util.List;

public interface Dao {
    public Boolean insert (Object obj) throws SQLException;
    public Boolean update (Object obj, Integer i) throws SQLException;
    public Boolean delete (Integer i) throws SQLException;
    public Object select(Integer i) throws SQLException;
    public List<Object> select() throws SQLException;
}
