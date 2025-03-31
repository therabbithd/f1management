package GP;

import java.util.Date;

public class GP {
    int cod_gp;
    String name_gp;
    Date fec_gp;
    int num_gp;
    public GP(int cod_gp, String name_gp, Date fec_gp, int num_gp) {
        this.cod_gp = cod_gp;
        this.name_gp = name_gp;
        this.fec_gp = fec_gp;
        this.num_gp = num_gp;
    }
    public Date getDate_gp() {
        return fec_gp;
    }
    public int getCod_gp() {
        return cod_gp;
    }
    public void setCod_gp(int cod_gp) {
        this.cod_gp = cod_gp;
    }
    public String getName_gp() {
        return name_gp;
    }
    public void setName_gp(String name_gp) {
        this.name_gp = name_gp;
    }
    public Date getFec_gp() {
        return fec_gp;
    }
    public int getNum_gp() {
        return num_gp;
    }
    public void setNum_gp(int num_gp) {
        this.num_gp = num_gp;
    }
    @Override
    public String toString() {
        return "GP: " + name_gp + "\nfecha: " + fec_gp + "\ncod GP: " + cod_gp + "\nnumero GP: " + num_gp;
    }
}
