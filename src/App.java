import java.sql.*;
import java.util.ArrayList;

import Connection.connectionpool;
import pilotos.piloto;
import pilotos.pilotosService;
public class App {
    public static void main(String[] args) {
       
        String URL = "jdbc:mysql://localhost:3306/f1_for_java2";
        String USER = "prog2425";
        String PASS = "1407";
        connectionpool connpool = new connectionpool(URL, USER, PASS);
        try {
            pilotosService ps = new pilotos.pilotosService(connpool.getConnection());
            ArrayList<piloto> pilotos = ps.requestAll();
            for (piloto p : pilotos){
                System.out.println(p);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
       
    }
}
