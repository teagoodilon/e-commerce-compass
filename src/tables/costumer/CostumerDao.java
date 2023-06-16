package tables.costumer;

import database.DbConnection;
import tables.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CostumerDao implements Dao {
    @Override
    public void insert(Object obj) throws SQLException {
        Costumer c = (Costumer) obj;
        String query = "INSERT INTO COSTUMER (name, email) values (?, ?)";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setString(1, c.getName());
            conn.setString(2, c.getEmail());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean update(Object obj, Integer i) throws SQLException {
        Costumer c = (Costumer) obj;
        String query = "UPDATE COSTUMER SET name=?, email=? where id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setString(1, c.getName());
            conn.setString(2, c.getEmail());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer i) throws SQLException {
        String query = "DELETE FROM COSTUMER WHERE id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public Object select(Integer i) throws SQLException {
        Costumer c = new Costumer();
        String query = "SELECT * FROM COSTUMER WHERE id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            if(rs.next()) {
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
            }else {
                System.out.println("Não existe cliente com esse id");
                return null;
            }
        }catch (SQLException e){
            System.out.println("Não existe Produto com esse id");
        }
        if(c.getId() == i){
            return c;
        } else {
            return null;
        }
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> list = new ArrayList<>(Collections.singletonList(new ArrayList<Costumer>()));
        String query = "SELECT * FROM COSTUMER";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                Costumer c = new Costumer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                list.add(c);
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return list;
    }
}
