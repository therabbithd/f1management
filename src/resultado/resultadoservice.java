package resultado;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import CRUD.CRUDSERVICE;
import GP.GP;
import GP.GPservice;
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
        rs = stmt.executeQuery("SELECT * FROM resultado");
        pilotosService ps = new pilotosService(conn);
        GPservice gs = new GPservice(conn);
        while (rs.next()) {
            int codgp = rs.getInt("CodGP");
            int codpil = rs.getInt("CodPil");
            int pos = rs.getInt("pos");
            GP gp = gs.requestById(codgp);
            piloto pil = ps.requestById(codpil);
            resultado res = new resultado(pil,pos,gp);
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
       ResultSet rs = stmt.executeQuery("Select * from resultado where CodPil = "+codPil+" && CodGP ="+codGP);
       GPservice gs = new GPservice(conn);
       pilotosService ps = new pilotosService(conn);
       resultado res = null;
       while (rs.next()) {
            int codgp = rs.getInt("CodGP");
            int codpil = rs.getInt("CodPil");
            int pos = rs.getInt("pos");
            GP gp = gs.requestById(codgp);
            piloto pil = ps.requestById(codpil);
            res = new resultado(pil,pos,gp);
       }
       return res;

       
    }
    @Override
    public void create(resultado model) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeQuery("INSERT INTO resultado(CodGP,CodPil,pos) VALUES ("+model.getGP().getCod_gp()+","+model.getPiloto().getCod_piloto()+","+model.getPos()+");");
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
        stmt.executeQuery("DELETE FROM resultado WHERE CodGP = "+res.getGP().getCod_gp()+" && CodPil = "+res.getPiloto().getCod_piloto());
        stmt.close();
        return true;
    }
    public ArrayList<resultado> requestByGP(GP gp) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        ArrayList<resultado> aux = new ArrayList<resultado>();
        rs = stmt.executeQuery("SELECT * FROM resultado WHERE CodGP = " + gp.getCod_gp());
        pilotosService ps = new pilotosService(conn);
        while (rs.next()) {
            int codgp = rs.getInt("CodGP");
            int codpil = rs.getInt("CodPil");
            int pos = rs.getInt("pos");
            piloto pil = ps.requestById(codpil);
            resultado res = new resultado(pil,pos,gp);
            aux.add(res);
        }
        return aux;
    }
}
