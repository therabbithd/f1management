package resultado;
import GP.GP;
import pilotos.piloto;
public class resultado {
    piloto piloto;
    int pos;
    GP GP;
    int puntos;
    int cod;
    public resultado(pilotos.piloto piloto, int pos, GP GP,int puntos) {
        this.piloto = piloto;
        this.pos = pos;
        this.GP = GP;
        this.puntos = puntos;
    }
    public pilotos.piloto getPiloto() {
        return piloto;
    }
    public void setPiloto(pilotos.piloto piloto) {
        this.piloto = piloto;
    }
    public int getPos() {
        return pos;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }
    public GP getGP() {
        return GP;
    }
    public void setGP(GP GP) {
        this.GP = GP;
    }
    public String imprimirnomycod(){
        return this.n
    }
    @Override
    public String toString() {
        return String.format("%2d.- %-10s %-20s %-6d %s",pos,piloto.getNamepiloto(),piloto.getSurnamepiloto(),puntos,piloto.getEquipo().getName_equipo());
    }
}
