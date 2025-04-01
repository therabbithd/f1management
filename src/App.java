import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import equipos.*;
import Connection.connectionpool;
import GP.GP;
import GP.GPservice;
import pilotos.piloto;
import pilotos.pilotosService;
import resultado.resultado;
import resultado.resultadoservice;
public class App {
    public static void menu(connectionpool connpool) {
        try {
            Connection conn = connpool.getConnection();
            System.out.println("1.Listar pilotos\n2.Listar pilotos por equipo\n3.Listar GPs\n4.Listar resultados por piloto\n5.Listar resultados por GP\n6.Salir");
            int op = Integer.parseInt(System.console().readLine());
            pilotosService ps = new pilotosService(conn);
            resultadoservice rs = new resultadoservice(conn);
            GPservice gps = new GPservice(conn);

            switch (op) {
                case 1:
                    
                    ArrayList<piloto> listapilotos = ps.requestAll();
                    for (piloto piloto : listapilotos) {
                        System.out.println(piloto.toString()+"\n");
                    }
                    break;
                case 2:
                    pilotosService ps2 = new pilotosService(conn);
                    HashMap<equipo, ArrayList<piloto>> pilporeq = ps2.requestAllByEquipo();
                    for (equipo eq : pilporeq.keySet()) {
                        System.out.println("Equipo: " + eq.getName_equipo()+"\n#####################");
                        ArrayList<piloto> pilotos = pilporeq.get(eq);
                        for (piloto piloto : pilotos) {
                            System.out.println(piloto.toString()+"\n");
                        }
                    }
                    menu(connpool);
                case 3:
                    ArrayList<GP> listagp = new ArrayList<GP>();
                    
                    listagp = gps.requestAll();
                    for (GP gp : listagp) {
                        System.out.println(gp+"\n");
                    }
                    menu(connpool);
                case 4:
                    
                case 5:
                    System.out.println("Ingrese el codigo del GP");
                    int codgp = Integer.parseInt(System.console().readLine());
                    GP gp = gps.requestById(codgp);
                    ArrayList<resultado> listares2 = new ArrayList<resultado>();
                    listares2 = rs.requestByGP(gp);
                    System.out.println("Resultados del GP " + gp.getName_gp() + ":");

                    for (resultado res : listares2) {
                        System.out.println(res.toString());
                    }
                    menu(connpool);
                case 6:
                    conn.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       
        String URL = "jdbc:mysql://localhost:3306/f1_for_java2";
        String USER = "prog2425";
        String PASS = "1407";
        connectionpool connpool = new connectionpool(URL, USER, PASS);
         menu(connpool);
    }
    
}
