package produse;

public class Arta extends Obiecte{
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

    public String getAutor() {
        return autor;
    }

    public int getAn() {
        return an;
    }


}
