package equipos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Motor.motor;
import Motor.motorservice;

public class equiposervice  implements CRUD.CRUDSERVICE<equipo>{
    Connection conn;
    public equiposervice(Connection conn) {
        this.conn = conn;
    }
    @Override
    public ArrayList<equipo> requestAll() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM equipo");
        ArrayList<equipo> aux = new ArrayList<equipo>();
        motorservice mot = new motorservice(conn);
        while (rs.next()) {
            int codequipo = rs.getInt("CodEq");
            String nameequipo = rs.getString("NomEq");
            int codmot = rs.getInt("CodMot");
            motor motoreq = mot.requestById(codmot);
            equipo equi = new equipo(codequipo, nameequipo, motoreq);
            aux.add(equi);
        }
        stmt.close();
        rs.close();
        return aux;
    }

    @Override
    public equipo requestById(long id) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM equipo WHERE CodEq = " + id);
        equipo aux = null;
        motorservice mot = new motorservice(conn);
        if (rs.next()) {
            int codequipo = rs.getInt("CodEq");
            String nameequipo = rs.getString("NomEq");
            int codmot = rs.getInt("CodMot");
            motor motoreq = mot.requestById(codmot);
            aux = new equipo(codequipo, nameequipo, motoreq);
        }
        stmt.close();
        rs.close();
        return aux;
    }

    @Override
    public void create(equipo model) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String consulta= String.format("INSERT INTO equipo (NomEq, CodMot) VALUES ('%s',%d)",model.getName_equipo(),model.getMotor().getCod_motor());
        stmt.executeUpdate("INSERT INTO equipo (NomEq, CodMot) VALUES ('" + model.getName_equipo() + "', " + model.getMotor().getCod_motor() + ")");
        stmt.close();
    }

    @Override
    public int update(equipo object) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        int result = stmt.executeUpdate("UPDATE equipo SET NomEq = '" + object.getName_equipo() + "', CodMot = " + object.getMotor().getCod_motor() + " WHERE CodEq = " + object.getCod_equipo());
        stmt.close();
        return result;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        boolean result = stmt.execute("DELETE FROM equipo WHERE CodEq = " + id);
        stmt.close();
        return result;
    }
    
    
    
}
