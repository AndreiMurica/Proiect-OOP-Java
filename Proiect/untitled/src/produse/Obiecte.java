package produse;

public class Obiecte {
    protected String nume;
    protected int estimare, ID, proprietarID;
    protected static int nrObiecte = 0;

    public Obiecte() {
        this.nume = "Necunoscut";
        this.estimare = 0;
        this.proprietarID = 0;
    }
    public Obiecte(String nume, int estimare, int proprietarID) {
        this.nume = nume;
        this.estimare = estimare;
        this.proprietarID = proprietarID;
    }

    {
        nrObiecte++;
        this.ID = nrObiecte;
    }


}
