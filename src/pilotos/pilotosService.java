package pilotos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import equipos.equipo;
import equipos.equiposervice;

public class pilotosService implements CRUD.CRUDSERVICE<piloto>{
    Connection con;
    public pilotosService(Connection con) {
        this.con = con;
    }
    @Override
    public ArrayList<piloto> requestAll() throws SQLException {
        Statement stmt = null;  
        ResultSet rs = null;
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM pilotos");
        ArrayList<piloto> aux = new ArrayList<piloto>();
        equiposervice equi = new equiposervice(con);
        while (rs.next()) {
            int codpil = rs.getInt("CodPil");
            String namepil = rs.getString("NomPil");
            String surnamepil = rs.getString("Ape1Pil");
            Date date = rs.getDate("FechaNacimiento");
            int codequipo = rs.getInt("CodEq");
            equipo equipopil = equi.requestById(codequipo);
            piloto pil = new piloto(codpil, namepil,surnamepil, date, equipopil);
            aux.add(pil);
        }
        stmt.close();
        rs.close();
        return aux;
    }
    @Override
    public piloto requestById(long id) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM pilotos WHERE CodPil = " + id);
        piloto aux = null;
        equiposervice equi = new equiposervice(con);
        if (rs.next()) {
            int codpil = rs.getInt("CodPil");
            String namepil = rs.getString("NomPil");
            String surnamepil = rs.getString("Ape1Pil");
            Date date = rs.getDate("FechaNacimiento");
            int codequipo = rs.getInt("CodEq");
            equipo equipopil = equi.requestById(codequipo);
            aux = new piloto(codpil, namepil,surnamepil, date, equipopil);
        }
        stmt.close();
        rs.close();
        return aux;
    }
    @Override
    public void create(piloto model) throws SQLException {
        Statement stmt = null;
        stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO pilotos (NomPil, Ape1Pil, FechaNacimiento, CodEq) VALUES ('" + model.getNamepiloto() + "','" + model.getSurnamepiloto() + "','" + model.getFecnac() + "'," + model.getEquipo().getCod_equipo() + ")");
        stmt.close();
    }
    @Override
    public int update(piloto object) throws SQLException {
        Statement stmt = null;
        stmt = con.createStatement();
        int result = stmt.executeUpdate("UPDATE pilotos SET NomPil = '" + object.getNamepiloto() + "', Ape1Pil = '" + object.getSurnamepiloto() + "', FechaNacimiento = '" + object.getFecnac() + "', CodEq = " + object.getEquipo().getCod_equipo() + " WHERE CodPil = " + object.getCod_piloto());
        if (result == 0) {
            throw new SQLException("Error al actualizar el registro");
        }
        stmt.close();
        return result;
    }
    @Override
    public boolean delete(long id) throws SQLException {
        Statement stmt = null;
        stmt = con.createStatement();
        boolean result = stmt.execute("DELETE FROM pilotos WHERE CodPil = " + id);
        stmt.close();
        return result;
    }


}
