import Connection.connectionpool;
import GP.GP;
import GP.GPservice;
import Motor.*;
import equipos.*;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.ArrayOrBuilder;

import pilotos.piloto;
import pilotos.pilotosService;
import resultado.resultado;
import resultado.resultadoservice;
import excepciones.*;
public class App {
    public static void menu(connectionpool connpool) {
        try {
            Connection conn = connpool.getConnection();
            limpiarconsola();
            System.out.println("1.Listar datos\n2.Ver ranking\n3.insertar en una tabla \n4.eliminar un campo\n5.actualizar un campo\n6.salir");
            int op = Integer.parseInt(System.console().readLine());
            pilotosService ps = new pilotosService(conn);
            resultadoservice rs = new resultadoservice(conn);
            GPservice gps = new GPservice(conn);
            equiposervice es = new equiposervice(conn);
            motorservice ms = new motorservice(conn);
            switch (op) {
                case 1:
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 
                    System.out.println("1.pilotos\n2.Equipos\n3.Motor\n4.Resultados\n5.GPs");
                    int oplis = Integer.parseInt(System.console().readLine());
                    switch (oplis) {
                        case 1:
                            limpiarconsola();
                            System.out.println("1.Listar todos los pilotos\n2.Listar los pilotos de un equipo");
                            int oppil = Integer.parseInt(System.console().readLine());
                            switch (oppil) {
                                case 1:
                                    listarpilotos(ps);
                                    esperarenter(connpool);
                                    break;
                                case 2:
                                    listarpilporeq(ps);
                                    esperarenter(connpool);
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            
                            case 2:
                            listarequipos(es);
                            esperarenter(connpool);
                            break;
                            case 3:
                            listarmotor(ms);
                            esperarenter(connpool);
                            break;
                            case 4:
                            limpiarconsola();
                            System.out.println("1.Listar resultados de un gp\n2.Listar resultados de un piloto");
                            int opres = Integer.parseInt(System.console().readLine());        
                            switch (opres) {
                                case 1:
                                    listarresporgp(gps,rs);
                                    esperarenter(connpool);
                                case 2:
                                    listarresporpil(ps,rs);
                                    esperarenter(connpool);
                                default:
                                    break;
                            }
                            case 5:
                            listargps(gps);
                            esperarenter(connpool);
                            
                        default:
                            throw new AssertionError();
                    }
                
                case 2:
                    limpiarconsola();
                    System.out.println("1.Ranking de pilotos\n2.Ranking de equipos\n3.Ranking de motores");
                    int opra = Integer.parseInt(System.console().readLine());
                    switch (opra) {
                        case 1:
                            mostrarranking(ps, rs);
                            esperarenter(connpool);
                            break;
                        case 2:
                            mostrarrankingeq(es, rs);
                            esperarenter(connpool);
                            break;
                        case 3:
                            mostrarrankingmot(ms, rs);
                            esperarenter(connpool);
                            break;
                        default:
                            throw new AssertionError();
                    }
                
                case 3:
                    limpiarconsola();
                    System.out.println("1.Equipos\n2.Motor\n3.pilotos\n4.resultados");
                    int opins = Integer.parseInt(System.console().readLine());
                    switch (opins) {
                        case 1:
                            ingresarequipo(es,ms);
                            esperarenter(connpool);
                            break;
                        case 2:
                            ingresarmotor(ms);
                            esperarenter(connpool);
                            break;
                        case 3:
                            ingresarpiloto(ps,es);
                            esperarenter(connpool);
                            break;
                        case 4:
                            insertarres(rs, gps, ps);
                            esperarenter(connpool);
                            break;
                        default:
                            throw new AssertionError();
                    }
                case 4:
                    limpiarconsola();
                    System.out.println("1.Eliminar piloto\n2.Eliminar equipo\n3.Eliminar motor\n4.Eliminar GP\n5.Eliminar resultado");
                    int opeli = Integer.parseInt(System.console().readLine());
                    switch (opeli) {
                        case 1:
                            eliminarpil(ps,rs);
                            esperarenter(connpool);
                            break;
                        case 2:
                            eliminarequipo(es,ps);
                            esperarenter(connpool);
                            break;
                        case 3:
                            eliminarmotor(ms, es);
                            esperarenter(connpool);
                            break;
                        case 4:
                            eliminargp(gps, rs);
                            esperarenter(connpool);
                            break;
                        case 5:
                            eliminarres(rs,gps);
                            esperarenter(connpool);
                            break;
                        default:
                            throw new AssertionError();
                    }
                case 5:
                System.out.println("1.piloto\n2.equipo\n3.motor");
                int op5 = Integer.parseInt(System.console().readLine());
                switch (op5) {
                    case 1:
                        actualizarpiloto(ps,es);
                        esperarenter(connpool);
                    case 2:
                        actualizarequipo(es, ms);
                        esperarenter(connpool);
                    case 3:
                        actualizarmotor(ms);
                        esperarenter(connpool);
                    
                    }
                break;
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
    public static void listarpilotos(pilotosService ps) throws SQLException, ClassNotFoundException {
        limpiarconsola();
        ArrayList<piloto> listapilotos = ps.requestAll();
                    for (piloto piloto : listapilotos) {
                        System.out.println(piloto.toString()+"\n");
                    }
        
    }
    public static void listarpilporeq(pilotosService ps) throws SQLException{
       limpiarconsola(); 
       HashMap<equipo, ArrayList<piloto>> pilporeq = ps.requestAllByEquipo();
                    for (equipo eq : pilporeq.keySet()) {
                        System.out.println("Equipo: " + eq.getName_equipo()+"\n#####################");
                        ArrayList<piloto> pilotos = pilporeq.get(eq);
                        for (piloto piloto : pilotos) {
                            System.out.println(piloto.toString()+"\n");
                        }
                    }
    }

    public static void listargps(GPservice gps) throws SQLException{
        limpiarconsola();
        ArrayList<GP> listagp = new ArrayList<GP>();
        listagp = gps.requestAll();
        for (GP gp : listagp) {
            System.out.println(gp+"\n");
        }
    }
    public static void listarresporpil(pilotosService ps,resultadoservice rs) throws SQLException{
        limpiarconsola();
        ArrayList<resultado> listares = new ArrayList<resultado>();
        int codpil = pedircodpil(ps);
        piloto pil = ps.requestById(codpil);
        listares = rs.requestbypil(pil);
        System.out.println("Resultados del piloto " + pil.getNamepiloto() + " " + pil.getSurnamepiloto() + ":");
        for (resultado res : listares) {
            System.out.println("resultado en "+res.getGP().getName_gp()+":");
            System.out.println(res.toString());
        }
    }
    public static void listarmotor(motorservice ms) throws SQLException{
        limpiarconsola();
        ArrayList<motor> listmotor = new ArrayList<motor>();
        listmotor = ms.requestAll();
        for (motor mot : listmotor) {
                        System.out.println(mot.toString()+"\n");
        }
    }
    public static void listarresporgp(GPservice gps,resultadoservice rs) throws SQLException{
        limpiarconsola();
        
        int codgp = perircodgp(gps);
        GP gp = gps.requestById(codgp);
        ArrayList<resultado> listares2 = new ArrayList<resultado>();
        listares2 = rs.requestByGP(gp);
        System.out.println("Resultados del GP " + gp.getName_gp() + ":");
        System.out.printf("%4s %-10s %-20s %s %s\n","Pos","Nombre","Apellido","Puntos","Equipo");
        for (resultado res : listares2) {
            System.out.println(res.toString());
        }
    }
    public static void mostrarranking(pilotosService ps, resultadoservice rs) throws SQLException{
        limpiarconsola();
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
    }
    public static void mostrarrankingeq(equiposervice es, resultadoservice rs) throws SQLException{
        limpiarconsola();
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
    }
    public static void mostrarrankingmot(motorservice ms, resultadoservice rs) throws SQLException{
        limpiarconsola();
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
    }
    public static void ingresarequipo(equiposervice es, motorservice ms) throws SQLException {
        limpiarconsola();
        System.out.println("pon el nombre del equipo");
        String nom = System.console().readLine();
        int cod = pedircodmot(ms);
        if(cod != 0){
            motor mot = ms.requestById(cod);
            equipo EQ = new equipo(0, nom, mot);
            es.create(EQ);
        }
        
    }
    public static void ingresarmotor(motorservice ms) throws SQLException{
        limpiarconsola();
        System.out.println("Ingresa el nombre del motor");
        String nom = System.console().readLine();
        motor mot = new motor(0, nom);
        ms.create(mot);
    }
    public static void ingresarpiloto(pilotosService ps,equiposervice es) throws SQLException{
        limpiarconsola();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
        System.out.println("Ingresa el nombre del piloto: ");
        String nom = System.console().readLine();
        System.out.println("Ingresa el apellido del piloto: ");
        String ape = System.console().readLine();
        Date date = null;
        boolean conseguido = false;
        while (conseguido == false) {
            try {
                System.out.println("Ingresa su fecha de nacimiento (dd/MM/yyyy) ");
                date =formato.parse(System.console().readLine());
                conseguido = true;
           } catch (ParseException e) {
               System.out.println("el formato no es el correcto utiliza el formato dd/MM/yyyy ");
               conseguido = false;
           }
        }
        
       
        int cod = pedircodeq(es);
        if (cod != 0){
            equipo eq = es.requestById(cod);
            piloto pil = new piloto(0, nom, ape, date, eq, cod);
            ps.create(pil);
        }
        
        
        }
   
    public static void listarequipos(equiposervice es) throws SQLException{
        limpiarconsola();
        ArrayList<equipo> equipos = es.requestAll();
        for(equipo e : equipos){
            System.out.println(e);
        }
    }
    public  static void limpiarconsola(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    public static void esperarenter(connectionpool connpool){
        System.out.println("Pulsa enter para entrar en el menu");
        System.console().readPassword();
        menu(connpool);
    }
    public static void eliminarequipo(equiposervice es,pilotosService ps) throws SQLException{
        limpiarconsola();
        int numenc = 0;
        try {
        int cod = pedircodeq(es);
        ArrayList<piloto> listapil = ps.requestAll();
        boolean encontrado = false;
        for (piloto pil : listapil) {
            if (pil.getEquipo().getCod_equipo() == cod) {
                encontrado = true;
                numenc++;
            }
        }
        if(encontrado){
            throw new nosepuedeeliminar();
        }
        else{
            System.out.print("Seguro que quieres eliminar (s/n)");
                String opt = System.console().readLine();
                if (opt.toLowerCase().equals("s")){
                   es.delete(cod);
                }
        }
        } catch (nosepuedeeliminar e) {
            System.out.println("hay "+numenc+ " pilotos asignados a ese equipo eliminalos o cambialos de equipo");
        }
        
    }
    public  static void eliminargp(GPservice gps,resultadoservice rs) throws SQLException{
        limpiarconsola();
        int numenc = 0;
        try {
            int cod = perircodgp(gps);
            ArrayList<resultado> resultados = rs.requestAll();
            boolean encontrado = false;
            
            for(resultado r : resultados){
                if(r.getGP().getCod_gp()==cod){
                    encontrado = true;
                    numenc++;
                }
            }
            if(encontrado){
                throw new nosepuedeeliminar();
            }
            else{
                System.out.print("Seguro que quieres eliminar (s/n)");
                String opt = System.console().readLine();
                if (opt.toLowerCase().equals("s")){
                    gps.delete(cod);
                }
            }

        } catch (nosepuedeeliminar e) {
            System.out.print("hay "+numenc+" resultados con ese gp eliminalos o asignalos a otro para poder eliminarlo");
        }
    }
    public static void eliminarpil(pilotosService ps, resultadoservice rs) throws SQLException{
        limpiarconsola();
        int numenc = 0;
        try {
            int id = pedircodpil(ps);
            ArrayList<resultado> resultados = rs.requestAll();
            boolean encontrado = false;
            for(resultado r : resultados){
                if(r.getPiloto().getCod_piloto()==id){
                    encontrado = true;
                    numenc++;
                }
            }
            if(encontrado){
                throw new nosepuedeeliminar();
            }
            else {
                System.out.print("Seguro que quieres eliminar (s/n)");
                String opt = System.console().readLine();
                if (opt.toLowerCase().equals("s")){
                    ps.delete(id);
                }

                
                
            }
        } catch (nosepuedeeliminar e) {
            System.out.println("hay "+numenc+" resultados de ese piloto elimina o actualiza esos resultados");;
        }
    }
    public static void eliminarmotor(motorservice ms , equiposervice es) throws SQLException{
        limpiarconsola();
        int numenc = 0;
        try {
            int id = pedircodmot(ms);
            ArrayList<equipo>  equipos = es.requestAll();
            boolean encontrado = false;
            for(equipo e :equipos){
                if(e.getMotor().getCod_motor()==id){
                    encontrado = true;
                    numenc++;
                }
            }
            if(encontrado){
                throw new nosepuedeeliminar();
            }
            else{
                System.out.print("Seguro que quieres eliminar (s/n)");
                String opt = System.console().readLine();
                if (opt.toLowerCase().equals("s")){
                   ms.delete(id);
                }
            }
        } catch (Exception e) {
            System.out.println("hay "+ numenc+" equipos asignados a ese motor borralos o actualizalos antes de eliminar el motor ");
        }
    }
    public static void eliminarres(resultadoservice rs,GPservice gps) throws SQLException{
        limpiarconsola();
        System.out.print("ingresa el codigo de gp: ");
        int id = perircodgp(gps);
        limpiarconsola();
        System.out.print("Seguro que quieres eliminar (s/n)");
                String opt = System.console().readLine();
                if (opt.toLowerCase().equals("s")){
                    rs.delete(id);
               }
    }
    public static void insertarres(resultadoservice rs,GPservice gps,pilotosService ps) throws SQLException{
        limpiarconsola();
        int codgp = perircodgp(gps);
        if(codgp != 0){
            GP gp = gps.requestById(codgp);
            System.out.print("pon el codigo del piloto: ");
            int codpil = pedircodpil(ps);
            if(codpil != 0){
                piloto pil = ps.requestById(codpil);
                System.out.print("pon la posicion: ");
                int pos = Integer.parseInt(System.console().readLine());
                resultado res = new resultado(pil, pos, gp, pos);
                rs.create(res);
            }
           
            
        }
        
    }
    public static int perircodgp(GPservice gps) throws SQLException{
        System.out.println("pon el codigo del gp");
        int cod = Integer.parseInt(System.console().readLine());
        GP gp = gps.requestById(cod);
        System.out.println("¿seguro que quieres poner "+gp.getName_gp()+"?(s/n/e)");
        String opt = System.console().readLine().toLowerCase();
        switch (opt) {
            case "s":
                return cod;
            case "n":
                return perircodgp(gps);
            default:
                return 0;
        }
        
    }

        
    public static int pedircodpil(pilotosService ps) throws SQLException{
        System.out.print("pon el codigo del piloto: ");
        int cod = Integer.parseInt(System.console().readLine());
        piloto pil = ps.requestById(cod);
        System.out.println("¿Seguro que quieres poner "+pil.getNamepiloto() +" "+pil.getSurnamepiloto()+"?(s/n)");
        String opt = System.console().readLine().toLowerCase();
        switch (opt) {
            case "s":
                return cod;
            case "n":
                return pedircodpil(ps);
            default:
                return 0;
        }
    }
    public static  int pedircodeq(equiposervice es) throws SQLException{
        System.out.print("pon el codigo del equipo: ");
        int cod = Integer.parseInt(System.console().readLine());
        equipo eq = es.requestById(cod);
        System.out.println("¿Seguro que quieres poner "+eq.getName_equipo()+"?(s/n/e)");
        String opt = System.console().readLine().toLowerCase();
        switch (opt) {
            case "s":
                return cod;
            case "n":
                return pedircodeq(es);
            default:
                return 0;
        }
        
        
    }
    public static int pedircodmot(motorservice ms) throws SQLException{
        System.out.print("pon el codigo del motor: ");
        int cod = Integer.parseInt(System.console().readLine());
        motor mot = ms.requestById(cod);
        System.out.println("¿seguro que quieres poner "+mot.getName_motor()+"?(s/n/e)");
        String opt = System.console().readLine().toLowerCase();
        switch (opt) {
            case "s":
            return cod;
            case "n":
                return pedircodmot(ms);
            default:
                return 0;
        }
        
    }
    public static void actualizarequipo(equiposervice es,motorservice ms) throws SQLException{
        limpiarconsola();
        int cod = pedircodeq(es);
        equipo e = es.requestById(cod);
        System.out.print("Pon el nuevo nombre del equipo(si lo pones vacio deja el anterior) ");
        String nom = System.console().readLine();
        if(!nom.equals("")){
            e.setName_equipo(nom);
        }
        int codmot = pedircodmot(ms);
        if(codmot != 0){
            motor mot = ms.requestById(codmot);
            e.setMotor(mot);
        }
        es.update(e);
    }
    public static void actualizarpiloto(pilotosService ps,equiposervice es) throws SQLException{
        limpiarconsola();
        int codpil = pedircodpil(ps);
        piloto pil = ps.requestById(codpil);
        System.out.print("Pon el nuevo nombre del piloto(si lo pones vacio deja el anterior) ");
        String nom = System.console().readLine();
        if(!nom.equals("")){
            pil.setNamepiloto(nom);
        }
        System.out.print("Pon el nuevo apellido del piloto(si lo pones vacio deja el anterior) ");
        String sur = System.console().readLine();
        if(!sur.equals("")){
            pil.setSurnamepiloto(sur);
        }
        int codeq = pedircodeq(es);
        if(codeq!=0){
            equipo eq = es.requestById(codeq);
            pil.setEquipo(eq);
        }
        ps.update(pil);
    }
    public static void actualizarmotor(motorservice ms) throws SQLException{
        limpiarconsola();
        int cod = pedircodmot(ms);
        motor mot  = ms.requestById(cod);
        System.out.print("Pon el nuevo nombre del motor(si lo pones vacio deja el anterior) ");
        String nom = System.console().readLine();
        if(!nom.equals("")){
            mot.setName_motor(nom);
        }
        ms.update(mot);
    }
}

