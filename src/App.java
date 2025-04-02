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
import equipos.*;
import Motor.*;
public class App {
    public static void menu(connectionpool connpool) {
        try {
            Connection conn = connpool.getConnection();
            System.out.println("1.Listar pilotos\n2.Listar pilotos por equipo\n3.Listar GPs\n4.Listar resultados de un piloto\n5.Listar motores\n6.Listar resultados de un GP\n7.Ver ranking de pilotos\n8.Mostrar ranking por equipos\n9.Mostrar ranking por motor\n10.Crear Motor\n11.Eliminar Motor\n12.Salir");
            int op = Integer.parseInt(System.console().readLine());
            pilotosService ps = new pilotosService(conn);
            resultadoservice rs = new resultadoservice(conn);
            GPservice gps = new GPservice(conn);
            equiposervice es = new equiposervice(conn);
            motorservice ms = new motorservice(conn);
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
                    ArrayList<motor> listmotor = new ArrayList<motor>();
                    listmotor = ms.requestAll();
                    for (motor mot : listmotor) {
                        System.out.println(mot.toString()+"\n");
                    }
                    menu(connpool);
                case 6:
                    System.out.println("Ingrese el codigo del GP");
                    int codgp = Integer.parseInt(System.console().readLine());
                    GP gp = gps.requestById(codgp);
                    ArrayList<resultado> listares2 = new ArrayList<resultado>();
                    listares2 = rs.requestByGP(gp);
                    System.out.println("Resultados del GP " + gp.getName_gp() + ":");
                    System.out.printf("%4s %-10s %-20s %s %s\n","Pos","Nombre","Apellido","Puntos","Equipo");
                    for (resultado res : listares2) {
                        System.out.println(res.toString());
                    }
                    menu(connpool);
                case 7:
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
                    System.out.printf("%4s %-10s %-20s %s %s\n","Pos","Nombre","Apellido","Puntos","Equipo");

                    for (int i = 0; i < order.size(); i++) {
                        piloto pil2 = order.get(i);

                        System.out.printf("%2d.- %-10s %-20s %-6d %s\n", i + 1,pil2.getNamepiloto(), pil2.getSurnamepiloto(), pilypuntos.get(pil2),pil2.getEquipo().getName_equipo());
                    }
                    menu(connpool);
                case 8:
                ArrayList<equipo> listaeq = new ArrayList<equipo>();
                HashMap<equipo,Integer> eqypuntos = new HashMap<equipo,Integer>();
                listaeq = es.requestAll();
                for (equipo eq : listaeq) {
                    int puntos = 0;
                    ArrayList<resultado> listares4 = new ArrayList<resultado>();
                    listares4 = rs.requestbyequipo(eq);
                    for (resultado res : listares4) {
                        puntos += res.getPuntos();
                    }
                    eqypuntos.put(eq, puntos);
                }
                HashMap<equipo,Integer> eqypuntos2 = new HashMap<equipo,Integer>();
                eqypuntos2.putAll(eqypuntos);
                ArrayList<equipo> order2 = new ArrayList<equipo>();
                for (int i = 0; i < eqypuntos.size(); i++) {
                    int max = 0;
                    equipo maxeq =null;
                    for (equipo eq : eqypuntos2.keySet()) {
                        if (eqypuntos2.get(eq) > max) {
                            max = eqypuntos2.get(eq);
                            maxeq = eq;
                        }
                    }
                    if (maxeq == null) {
                        break;
                    }
                    order2.add(maxeq);
                    eqypuntos2.remove(maxeq);
                }
                for (equipo eq : eqypuntos.keySet()) {
                    if (eqypuntos.get(eq) == 0) {
                        order2.add(eq);
                    }
                }
                System.out.println("Ranking de equipos:");
                System.out.printf("%4s %-50s %s\n","Pos","Nombre","Puntos");
                for (int i = 0; i < order2.size(); i++) {
                    equipo eq = order2.get(i);
                    System.out.printf("%2d.- %-50s %-6d\n", i + 1,eq.getName_equipo(), eqypuntos.get(eq));
                }
                System.out.println("");
                menu(connpool);
                case 9:
                ArrayList<motor> listamotor = new ArrayList<motor>();
                HashMap<motor,Integer> motorypuntos = new HashMap<motor,Integer>();
                listamotor = ms.requestAll();
                for (motor mot : listamotor) {
                    int puntos = 0;
                    ArrayList<resultado> listares5 = new ArrayList<resultado>();
                    listares5 = rs.requestbymotor(mot);
                    for (resultado res : listares5) {
                        puntos += res.getPuntos();
                    }
                    motorypuntos.put(mot, puntos);
                }
                HashMap<motor,Integer> motorypuntos2 = new HashMap<motor,Integer>();
                motorypuntos2.putAll(motorypuntos);
                ArrayList<motor> order3 = new ArrayList<motor>();
                for (int i = 0; i < motorypuntos.size(); i++) {
                    int max = 0;
                    motor maxmot =null;
                    for (motor mot : motorypuntos2.keySet()) {
                        if (motorypuntos2.get(mot) > max) {
                            max = motorypuntos2.get(mot);
                            maxmot = mot;
                        }
                    }
                    if (maxmot == null) {
                        break;
                    }
                    order3.add(maxmot);
                    motorypuntos2.remove(maxmot);
                }
                for (motor mot : motorypuntos.keySet()) {
                    if (motorypuntos.get(mot) == 0) {
                        order3.add(mot);
                    }
                }
                System.out.println("Ranking de motores:");
                System.out.printf("%4s %-50s %s\n","Pos","Nombre","Puntos");
                for (int i = 0; i < order3.size(); i++) {
                    motor mot = order3.get(i);
                    System.out.printf("%2d.- %-50s %-6d\n", i + 1,mot.getName_motor(), motorypuntos.get(mot));
                }
                System.out.println("");
                menu(connpool);
                case 10:
                    System.out.println("Ingrese el nombre del motor");
                    String name_motor = System.console().readLine();
                    motor mot = new motor(0, name_motor);
                    ms.create(mot);
                    menu(connpool);
                case 11:
                    System.out.print("Ingrese el codigo del motor :");
                    int codmotor = Integer.parseInt(System.console().readLine());
                    if(ms.delete(codmotor)){
                        System.out.println("Motor eliminado correctamente");
                    }
                    else{
                        System.out.println("No se ha podido eliminar el motor");
                    }
                    menu(connpool);
                case 12:
                    

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
