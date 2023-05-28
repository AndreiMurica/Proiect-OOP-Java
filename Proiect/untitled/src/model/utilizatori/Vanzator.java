package model.utilizatori;

public class Vanzator extends Persoana{

    public Vanzator(String nume, int varsta, int balanta) {
        super(nume, varsta, balanta);
    }
    public Vanzator(int ID, String nume, int varsta, int balanta) {
        super(ID, nume, varsta, balanta);
    }
}
