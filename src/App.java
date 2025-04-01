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
            System.out.println("1.Listar pilotos\n2.Listar pilotos por equipo\n3.Listar GPs\n4.Listar resultados de un piloto\n5.Listar resultados de un GP\n6.Ver ranking de pilotos");
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
                    ArrayList<resultado> listares = new ArrayList<resultado>();
                    System.out.println("Ingrese el codigo del piloto");
                    int codpil = Integer.parseInt(System.console().readLine());
                    piloto pil = ps.requestById(codpil);
                    listares = rs.requestbypil(pil);
                    System.out.println("Resultados del piloto " + pil.getNamepiloto() + " " + pil.getSurnamepiloto() + ":");
                    for (resultado res : listares) {
                        System.out.println("resultado en "+res.getGP().getName_gp()+":");
                        System.out.println(res.toString());
                    }
                    menu(connpool);
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
                    HashMap<piloto,Integer> pilypuntos = new HashMap<piloto,Integer>();
                    ArrayList<piloto> pils= new ArrayList<piloto>();
                    pils = ps.requestAll();
                    for (piloto pil2 : pils) {
                        ArrayList<resultado> listares3 = new ArrayList<resultado>();
                        listares3 = rs.requestbypil(pil2);
                        int puntos = 0;
                        for (resultado res : listares3) {
                            puntos += res.getPuntos();
                        }
                        pilypuntos.put(pil2, puntos);
                    }
                    HashMap<piloto,Integer> pilypuntos2 = new HashMap<piloto,Integer>();
                    pilypuntos2.putAll(pilypuntos);
                    ArrayList<piloto> order = new ArrayList<piloto>();
                    for (int i = 0; i < pilypuntos.size(); i++) {
                        int max = 0;
                        piloto maxpil =null;
                        for (piloto pil2 : pilypuntos2.keySet()) {
                            if (pilypuntos2.get(pil2) > max) {
                                max = pilypuntos2.get(pil2);
                                maxpil = pil2;
                            }
                        }
                        if (maxpil == null) {
                            break;
                        }
                        order.add(maxpil);
                        pilypuntos2.remove(maxpil);
                    }
                    for (piloto p : pilypuntos.keySet()) {
                        if (pilypuntos.get(p) == 0) {
                            order.add(p);
                        }
                    }
                    System.out.println("Ranking de pilotos:");
                    for (int i = 0; i < order.size(); i++) {
                        piloto pil2 = order.get(i);
                        System.out.printf("%2d.- %s %s %d\n", i + 1,pil2.getNamepiloto(), pil2.getSurnamepiloto(), pilypuntos.get(pil2));
                    }
                    menu(connpool);
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
