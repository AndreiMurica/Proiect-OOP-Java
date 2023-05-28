package model.produse;

public class Obiect {
    protected String nume;
    protected int estimare, ID, proprietarID;
    protected static int nrObiecte = 0;

    public Obiect() {
        this.nume = "Necunoscut";
        this.estimare = 0;
        this.proprietarID = 0;
    }
    public Obiect(String nume, int estimare, int proprietarID) {
        this.nume = nume;
        this.estimare = estimare;
        this.proprietarID = proprietarID;
    }

    {
        nrObiecte++;
        this.ID = nrObiecte;
    }

    public Obiect(int ID, String nume, int estimare, int proprietarID) {
        this.nume = nume;
        this.estimare = estimare;
        this.proprietarID = proprietarID;
        this.ID = ID;
    }
    public String getNume() {
        return nume;
    }

    public int getEstimare() {
        return estimare;
    }

    public int getID() {
        return ID;
    }

    public int getProprietarID() {
        return proprietarID;
    }

    public String toString() {
        return "Obiect{" +
                "nume='" + nume + '\'' +
                ", estimare=" + estimare +
                ", ID=" + ID +
                ", proprietarID=" + proprietarID +
                '}';
    }
}
