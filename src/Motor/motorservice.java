package Motor;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class motorservice implements CRUD.CRUDSERVICE<motor> {
    Connection conn;
    public motorservice(Connection conn) {
        this.conn = conn;
    } 
    @Override
    public ArrayList<motor> requestAll() throws SQLException {
        ArrayList<motor> aux = new ArrayList<motor>();
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM motor");
        while (rs.next()) {
            int codmot = rs.getInt("CodMot");
            String namemot = rs.getString("NomMot");
            motor mot = new motor(codmot, namemot);
            aux.add(mot);
        }
        stmt.close();
        rs.close();
        return aux;
    }

    @Override
    public motor requestById(long id) throws SQLException {
        motor aux = null;
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM motor WHERE CodMot = " + id);
        if (rs.next()) {
            int codmot = rs.getInt("CodMot");
            String namemot = rs.getString("NomMot");
            aux = new motor(codmot, namemot);
        }
        stmt.close();
        rs.close();
        return aux;
    }

    @Override
    public void create(motor model) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO motor (NomMot) VALUES ('" + model.getName_motor() + "')");
        stmt.close();
    }

    @Override
    public int update(motor object) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        int result = stmt.executeUpdate("UPDATE motor SET NomMot = '" + object.getName_motor() + "' WHERE CodMot = " + object.getCod_motor());
        if (result == 0) {
            throw new SQLException("No se ha podido actualizar el registro");
        }
        stmt.close();
        return result;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        int result = stmt.executeUpdate("DELETE FROM motor WHERE CodMot = " + id);
        stmt.close();
        return result > 0;
    }

}
