import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import equipos.*;
import Connection.connectionpool;
import pilotos.piloto;
import pilotos.pilotosService;
public class App {
    public static void menu(connectionpool connpool) {
        try {
            Connection conn = connpool.getConnection();
            System.out.println("1.Listar pilotos\n2.Listar pilotos por equipo\n3.Listar pilotos por GP\n4.Listar resultados por piloto\n5.Listar resultados por GP\n6.Salir");
            int op = Integer.parseInt(System.console().readLine());
            switch (op) {
                case 1:
                    pilotosService ps = new pilotosService(conn);
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
                    break;
                case 3:
                    // Implementar listar pilotos por GP
                    break;
                case 4:
                    // Implementar listar resultados por piloto
                    break;
                case 5:
                    // Implementar listar resultados por GP
                    break;
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
