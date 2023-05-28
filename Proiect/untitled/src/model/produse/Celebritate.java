package model.produse;

public class Celebritate extends Obiect{
    protected String vedeta;

    public Celebritate() {
        super();
        this.vedeta = "Necunoscut";
    }

    public Celebritate(String nume, int estimare, int proprietarID, String vedeta) {
        super(nume, estimare, proprietarID);
        this.vedeta = vedeta;
    }

    public Celebritate(int ID, String nume, int estimare, int proprietarID, String vedeta) {
        super(ID, nume, estimare, proprietarID);
        this.vedeta = vedeta;
    }
    @Override
    public String toString() {
        return "Celebritate :" +
                " " + nume +
                " lui " + vedeta  +
                ", estimare :" + estimare +
                ", ObiectID=" + ID +
                ';';
    }

    public String getVedeta() {
        return vedeta;
    }

}
