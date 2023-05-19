package model.tranzactii;


public class Oferta {
    protected int  pret, cumparatorID, vanzatorID, obiectID;

    public Oferta() {
        this.pret = 0;
        this.cumparatorID = 0;
        this.vanzatorID = 0;
        this.obiectID = 0;
    }
    public Oferta( int pret, int cumparatorID, int vanzatorID, int obiectID) {
        this.pret = pret;
        this.cumparatorID = cumparatorID;
        this.vanzatorID = vanzatorID;
        this.obiectID = obiectID;
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "pret=" + pret +
                ", cumparatorID=" + cumparatorID +
                ", vanzatorID=" + vanzatorID +
                ", obiectID=" + obiectID +
                '}';
    }
    public int getPret() {
        return pret;
    }

    public int getCumparatorID() {
        return cumparatorID;
    }

    public int getVanzatorID() {
        return vanzatorID;
    }

    public int getObiectID() {
        return obiectID;
    }

}

