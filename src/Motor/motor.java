package Motor;

public class motor {
    int cod_motor;
    String name_motor;
    public motor(int cod_motor, String name_motor) {
        this.cod_motor = cod_motor;
        this.name_motor = name_motor;
    }
    public int getCod_motor() {
        return cod_motor;
    }
    public void setCod_motor(int cod_motor) {
        this.cod_motor = cod_motor;
    }
    public String getName_motor() {
        return name_motor;
    }
    public void setName_motor(String name_motor) {
        this.name_motor = name_motor;
    }
    public String imprimirnomycod(){
        return this.name_motor+":"+this.cod_motor;
    }
    @Override
    public String toString() {
        return "Nombre: "+name_motor+"\nCodigo: "+cod_motor;
    }
}
