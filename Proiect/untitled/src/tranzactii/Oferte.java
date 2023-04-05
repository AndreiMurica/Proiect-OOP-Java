package tranzactii;


public class Oferte {
    protected int  pret, cumparatorID, vanzatorID, obiectID;

    public Oferte() {
        this.pret = 0;
        this.cumparatorID = 0;
        this.vanzatorID = 0;
        this.obiectID = 0;
    }
    public Oferte( int pret, int cumparatorID, int vanzatorID, int obiectID) {
        this.pret = pret;
        this.cumparatorID = cumparatorID;
        this.vanzatorID = vanzatorID;
        this.obiectID = obiectID;
    }

}

