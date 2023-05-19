package model.utilizatori;
import model.produse.*;

import java.util.ArrayList;
import java.util.List;

public class Persoana {
    protected String nume;
    protected int varsta, ID;
    protected int balanta;
    protected static int nrPersoane = 0;
    protected List<Obiect> obiecte;

    public Persoana(String nume, int varsta, int balanta) {
        this.nume = nume;
        this.varsta = varsta;
        this.balanta = balanta;
        this.obiecte = new ArrayList<>();
    }

    {
        nrPersoane++;
        this.ID = nrPersoane;
    }
    public void adaugaObiect(Obiect obiect){
        this.obiecte.add(obiect);
    }

    public void StergeObiect(Obiect obiect){
        this.obiecte.remove(obiect);
    }

    public List<Obiect> getObiecte() {
        return obiecte;
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

    public int getID() {
        return ID;
    }

    public void setBalanta(int balanta) {
        this.balanta = balanta;
    }

}
