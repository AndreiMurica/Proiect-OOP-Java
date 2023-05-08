package model.produse;

public class Antichitate extends Obiect {
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

    @Override
    public String toString() {
        return "Antichitate :" +
                "Artefactul " + nume  +
                " din zona '" + zona +
                " perioada an " + an +
                ", estimare :" + estimare +
                ", ObiectID :" + ID +
                ';';
    }
    public int getAn() {
        return an;
    }

    public String getZona() {
        return zona;
    }
}
