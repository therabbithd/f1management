package resultado;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import CRUD.CRUDSERVICE;
import GP.GP;
import GP.GPservice;
import Motor.motor;
import equipos.equipo;
import pilotos.piloto;
import pilotos.pilotosService;

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
        rs = stmt.executeQuery("SELECT CodGP,CodPil,pos,CASE WHEN pos = 1 THEN 25 WHEN pos = 2 THEN 18 WHEN pos = 3 THEN 15 WHEN pos = 4 THEN 12 WHEN pos = 5 THEN 10  WHEN pos = 6 THEN 8 WHEN pos = 7 THEN 6 WHEN pos = 8 THEN 4 WHEN pos = 9 THEN 2 WHEN pos = 10 THEN 1 ELSE 0 END as PUNTOS  FROM resultado");
        pilotosService ps = new pilotosService(conn);
        GPservice gs = new GPservice(conn);
        while (rs.next()) {
            int codgp = rs.getInt("CodGP");
            int codpil = rs.getInt("CodPil");
            int puntos = rs.getInt("PUNTOS");
            int pos = rs.getInt("pos");
            GP gp = gs.requestById(codgp);
            piloto pil = ps.requestById(codpil);
            resultado res = new resultado(pil,pos,gp,puntos);
            aux.add(res);
        }
        return aux;

    }
    @Override
    public resultado requestById(long id) throws SQLException {
       int codGP = (int)id;
       System.out.print("Ingresa el codigo de piloto");
       int codPil = Integer.parseInt(System.console().readLine());
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("Select CodGP,CodPil,pos,CASE WHEN pos = 1 THEN 25 WHEN pos = 2 THEN 18 WHEN pos = 3 THEN 15 WHEN pos = 4 THEN 12 WHEN pos = 5 THEN 10  WHEN pos = 6 THEN 8 WHEN pos = 7 THEN 6 WHEN pos = 8 THEN 4 WHEN pos = 9 THEN 2 WHEN pos = 10 THEN 1 ELSE 0 END as PUNTOS from resultado where CodPil = "+codPil+" && CodGP ="+codGP);
       GPservice gs = new GPservice(conn);
       pilotosService ps = new pilotosService(conn);
       resultado res = null;
       while (rs.next()) {
            int codgp = rs.getInt("CodGP");
            int codpil = rs.getInt("CodPil");
            int pos = rs.getInt("pos");
            int puntos = rs.getInt("PUNTOS");
            GP gp = gs.requestById(codgp);
            piloto pil = ps.requestById(codpil);
            res = new resultado(pil,pos,gp,puntos);
       }
       return res;

       
    }
    @Override
    public void create(resultado model) throws SQLException {
        Statement stmt = conn.createStatement();
        String consulta = String.format("INSERT INTO resultado(CodGP,CodPil,pos) VALUES (%d,%d,%d)",model.getGP().getCod_gp(),model.getPiloto().getCod_piloto(),model.getPos());
        stmt.executeUpdate(consulta);
        stmt.close();

    }
    @Override
    public int update(resultado object) throws SQLException {
        Statement stmt = conn.createStatement();
        int aux;
         aux = stmt.executeUpdate("update resultado SET pos = "+object.getPos()+"WHERE CodGP = "+object.getGP().getCod_gp()+"&& CodPil = "+object.getPiloto().getCod_piloto() );
        if(aux == 0){
            throw new SQLException("Error al actualizar el registro");
        }
        stmt.close();
        return aux;
    }
    @Override
    public boolean delete(long id) throws SQLException {
        resultado res = requestById(id);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM resultado WHERE CodGP = "+res.getGP().getCod_gp()+" && CodPil = "+res.getPiloto().getCod_piloto());
        stmt.close();
        return true;
    }
    public ArrayList<resultado> requestByGP(GP gp) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        ArrayList<resultado> aux = new ArrayList<resultado>();
        String query ="SELECT CodGP,CodPil,pos,CASE WHEN pos = 1 THEN 25 WHEN pos = 2 THEN 18 WHEN pos = 3 THEN 15 WHEN pos = 4 THEN 12 WHEN pos = 5 THEN 10  WHEN pos = 6 THEN 8 WHEN pos = 7 THEN 6 WHEN pos = 8 THEN 4 WHEN pos = 9 THEN 2 WHEN pos = 10 THEN 1 ELSE 0 END as PUNTOS FROM resultado  WHERE CodGP = " + gp.getCod_gp()+" order by pos asc";
        rs = stmt.executeQuery(query);
        pilotosService ps = new pilotosService(conn);
        GPservice gs = new GPservice(conn);
        while (rs.next()) {
            int codgp = rs.getInt("CodGP");
            int codpil = rs.getInt("CodPil");
            int pos = rs.getInt("pos");
            int puntos = rs.getInt("PUNTOS");
            GP gpp = gs.requestById(codgp);
            piloto pil = ps.requestById(codpil);
            resultado res = new resultado(pil,pos,gpp,puntos);
            aux.add(res);
        }
        stmt.close();
        rs.close();
        return aux;
    }
    public ArrayList<resultado> requestbypil(piloto pil) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        ArrayList<resultado> aux = new ArrayList<resultado>();
        rs = stmt.executeQuery("SELECT CodGP,CodPil,pos,CASE WHEN pos = 1 THEN 25 WHEN pos = 2 THEN 18 WHEN pos = 3 THEN 15 WHEN pos = 4 THEN 12 WHEN pos = 5 THEN 10  WHEN pos = 6 THEN 8 WHEN pos = 7 THEN 6 WHEN pos = 8 THEN 4 WHEN pos = 9 THEN 2 WHEN pos = 10 THEN 1 ELSE 0 END as PUNTOS FROM resultado WHERE CodPil = " + pil.getCod_piloto()+ " order by CodGP asc");
        GPservice gs = new GPservice(conn);
        pilotosService ps = new pilotosService(conn);
        while(rs.next()){
            int codgp = rs.getInt("CodGP");
            int codpil = rs.getInt("CodPil");
            int pos = rs.getInt("pos");
            int puntos = rs.getInt("PUNTOS");
            GP gpp = gs.requestById(codgp);
            piloto pil1 = ps.requestById(codpil);
            resultado res = new resultado(pil,pos,gpp,puntos);
            aux.add(res);
        }
        return aux;
    }
    public ArrayList<resultado> requestbyequipo(equipo eq) throws SQLException {
       ArrayList<resultado> resultados = new ArrayList<resultado>();
       resultados = requestAll();
       ArrayList<resultado> resultadosporEquipo = new ArrayList<resultado>();
       for(resultado res : resultados){
            if(res.getPiloto().getEquipo().getCod_equipo() == eq.getCod_equipo()){
                resultadosporEquipo.add(res);
            }
       }
        return resultadosporEquipo;
    }
    public ArrayList<resultado> requestbymotor(motor mot) throws SQLException {
        ArrayList<resultado> resultados = new ArrayList<resultado>();
        resultados = requestAll();
        ArrayList<resultado> resultadosporMotor = new ArrayList<resultado>();
        for(resultado res : resultados){
            if(res.getPiloto().getEquipo().getMotor().getCod_motor() == mot.getCod_motor()){
                resultadosporMotor.add(res);
            }
        }
        return resultadosporMotor;
    }
}


