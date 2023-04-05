package utilizatori;
import produse.*;

public class Persoana {
    protected String nume;
    protected int varsta, ID;
    protected int balanta;
    protected static int nrPersoane = 0;
    protected Obiecte[] colectie = new Obiecte[100];

    public Persoana(String nume, int varsta, int balanta) {
        this.nume = nume;
        this.varsta = varsta;
        this.balanta = balanta;
    }

    {
        nrPersoane++;
        this.ID = nrPersoane;
    }
    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public int getBalanta() {
        return balanta;
    }
}
