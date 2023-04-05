package produse;

public class Antichitate extends Obiecte {
    protected int an;
    protected String zona;

    public Antichitate() {
        super();
        this.an = 0;
        this.zona = "Necunoscuta";
    }

    public Antichitate(String nume, int estimare, int proprietarID, int an, String zona) {
        super(nume, estimare, proprietarID);
        this.an = an;
        this.zona = zona;
    }

    public int getAn() {
        return an;
    }

    public String getZona() {
        return zona;
    }
}
