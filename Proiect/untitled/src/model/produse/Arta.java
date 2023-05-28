package model.produse;

public class Arta extends Obiect{
    protected String autor;
    protected int an;

    public Arta() {
        super();
        this.autor = "Necunoscut";
        this.an = 0;
    }

    public Arta(String nume, int estimare, int proprietarID, String autor, int an) {
        super(nume, estimare, proprietarID);
        this.autor = autor;
        this.an = an;
    }

    public Arta(int ID, String nume, int estimare, int proprietarID, String autor, int an) {
        super(ID, nume, estimare, proprietarID);
        this.autor = autor;
        this.an = an;
    }

    @Override
    public String toString() {
        return "Arta:" +
                " Opera " + nume  +
                " facuta de " + autor +
                " in anul" + an +
                ", estimare : " + estimare +
                ", ObiectID :" + ID +
                ';';
    }
    public String getAutor() {
        return autor;
    }

    public int getAn() {
        return an;
    }


}
