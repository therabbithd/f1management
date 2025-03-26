package equipos;

import Motor.motor;

public class equipo {
    int cod_equipo;
    String name_equipo;
    motor motor;
    public equipo(int cod_equipo, String name_equipo, Motor.motor motor) {
        this.cod_equipo = cod_equipo;
        this.name_equipo = name_equipo;
        this.motor = motor;
    }
    public int getCod_equipo() {
        return cod_equipo;
    }
    public void setCod_equipo(int cod_equipo) {
        this.cod_equipo = cod_equipo;
    }
    public String getName_equipo() {
        return name_equipo;
    }
    public void setName_equipo(String name_equipo) {
        this.name_equipo = name_equipo;
    }
    public Motor.motor getMotor() {
        return motor;
    }
    public void setMotor(Motor.motor motor) {
        this.motor = motor;
    }
    
}
