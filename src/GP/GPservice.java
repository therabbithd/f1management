package GP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class GPservice  implements CRUD.CRUDSERVICE<GP>{
    Connection conn;
    public GPservice(Connection conn){
        this.conn = conn;
    }
    @Override
    public ArrayList<GP> requestAll() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        ArrayList<GP> aux = new ArrayList<GP>();
        rs = stmt.executeQuery("SELECT * FROM GP");
        while (rs.next()) {
            int codgp = rs.getInt("CodGP");
            String namegp = rs.getString("NomGP");
            Date dategp = rs.getDate("FecGP");
            int num_gp = rs.getInt("NumGP");
            GP gp = new GP(codgp, namegp, dategp, num_gp);
            aux.add(gp);
        }
        stmt.close();
        rs.close();
        return aux;


    }

    @Override
    public GP requestById(long id) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM GP WHERE CodGP = " + id);
        GP aux = null;
        if (rs.next()) {
            int codgp = rs.getInt("CodGP");
            String namegp = rs.getString("NomGP");
            Date dategp = rs.getDate("FecGP");
            int num_gp = rs.getInt("NumGP");
            aux = new GP(codgp, namegp, dategp, num_gp);
        }
        stmt.close();
        rs.close();
        return aux;
    }

    @Override
    public void create(GP model) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO GP (NomGP, FecGP, NumGP) VALUES ('" + model.getName_gp() + "', '" + model.getDate_gp() + "', " + model.getNum_gp() + ")");
        stmt.close();
    }

    @Override
    public int update(GP object) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        int result = stmt.executeUpdate("UPDATE GP SET NomGP = '" + object.getName_gp() + "', FecGP = '" + object.getDate_gp() + "', NumGP = " + object.getNum_gp() + " WHERE CodGP = " + object.getCod_gp());
        stmt.close();
        if(result == 0){
            throw new SQLException("Update failed");
        }
        return result;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        int result = stmt.executeUpdate("DELETE FROM GP WHERE CodGP = " + id);
        stmt.close();
        if(result == 0){
            throw new SQLException("Delete failed");
        }
        return result > 0;
    }
    
}
