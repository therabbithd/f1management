package resultado;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import CRUD.CRUDSERVICE;

public class resultadoservice implements CRUDSERVICE<resultado>{
    Connection conn;
    public resultadoservice(Connection conn) {
        this.conn = conn;
    }
    @Override
    public ArrayList<resultado> requestAll() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        ArrayList<resultado> aux = new ArrayList<resultado>();
        rs = stmt.executeQuery("SELECT * FROM resultado");
        while (rs.next()) {
            int codres = rs.getInt("CodRes");
            int codgp = rs.getInt("CodGP");
            int codpil = rs.getInt("CodPil");
            int codpos = rs.getInt("CodPos");
            resultado res = new resultado(codres, codgp, codpil, codpos);
            aux.add(res);
        }

    }
    @Override
    public resultado requestById(long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestById'");
    }
    @Override
    public void create(resultado model) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    @Override
    public int update(resultado object) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public boolean delete(long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
