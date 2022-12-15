package db;

public class Pays {

    private String cona;
    private String can;
    private String l;

    public Pays(String cn, String ca, String l){
        this.cona = cn;
        this.can = ca;
        this.l = l;
    }

    public String getCan() {
        return can;
    }

    public String getCona() {
        return cona;
    }

    public String getL() {
        return l;
    }
}
