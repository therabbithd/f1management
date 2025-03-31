package pilotos;


import java.util.Date;

import equipos.equipo;

public class piloto {
    int cod_piloto;
    String namepiloto;
    String surnamepiloto;
    Date fecnac;
    int  edad;
    equipo equipo;
    public piloto(int cod_piloto, String namepiloto, String surnamepiloto,Date fecnac,equipos.equipo equipo, int edad) {
        this.cod_piloto = cod_piloto;
        this.namepiloto = namepiloto;
        this.surnamepiloto = surnamepiloto;
        this.fecnac = fecnac;
        this.equipo = equipo;
        this.edad = edad;
    }

    public int getCod_piloto() {
        return cod_piloto;
    }
    public void setCod_piloto(int cod_piloto) {
        this.cod_piloto = cod_piloto;
    }
    public String getNamepiloto() {
        return namepiloto;
    }
    public void setNamepiloto(String namepiloto) {
        this.namepiloto = namepiloto;
    }
    public String getSurnamepiloto() {
        return surnamepiloto;
    }
    public void setSurnamepiloto(String surnamepiloto) {
        this.surnamepiloto = surnamepiloto;
    }
    public Date getFecnac() {
        return fecnac;
    }
    public equipos.equipo getEquipo() {
        return equipo;
    }
    public void setEquipo(equipos.equipo equipo) {
        this.equipo = equipo;
    }
    @Override
    public String toString() {
        return "piloto: " + namepiloto + " "+ surnamepiloto + "\nfecha de nacimiento: " + fecnac + "\nequipo: " + equipo.getName_equipo() + "\ncod piloto: " + cod_piloto+"\nedad: " + edad;
    }
    
}
