package produse;

public class Celebritate extends Obiecte{
    protected String vedeta;

    public Celebritate() {
        super();
        this.vedeta = "Necunoscut";
    }

    public Celebritate(String nume, int estimare, int proprietarID, String vedeta) {
        super(nume, estimare, proprietarID);
        this.vedeta = vedeta;
    }

    public String getVedeta() {
        return vedeta;
    }

}
