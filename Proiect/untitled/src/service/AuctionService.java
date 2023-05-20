package service;

import model.produse.Antichitate;
import model.produse.Arta;
import model.produse.Celebritate;
import model.produse.Obiect;
import model.tranzactii.Oferta;
import model.utilizatori.Cumparator;
import model.utilizatori.Vanzator;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;
public class AuctionService {
    private List<Cumparator> cumparatori = new ArrayList<>();
    private List<Vanzator> vanzatori = new ArrayList<>();
    private List<Oferta> oferte = new ArrayList<>();
    private Hashtable<Integer, Oferta> bestOffert = new Hashtable<>();
    public void AdaugaVanzator (String nume, int varsta, int balanta){
        Vanzator vanzator = new Vanzator(nume, varsta, balanta);
        vanzatori.add(vanzator);
    }

    public void AfisareVanzatori(){
        for (Vanzator vanzator : vanzatori) {
            System.out.println("ID: " + vanzator.getID() + " Nume: " + vanzator.getNume() + " Varsta: " + vanzator.getVarsta() + " Balanta: " + vanzator.getBalanta() );
            for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                System.out.println(vanzator.getObiecte().get(i).toString());
            }
        }
    }

    public void AdaugaCumparator (String nume, int varsta, int balanta){
        Cumparator cumparator = new Cumparator(nume, varsta, balanta);
        cumparatori.add(cumparator);
    }

    public void AfisareCumparatori(){
        for (Cumparator cumparator : cumparatori) {
            System.out.println("ID: " + cumparator.getID() + " Nume: " + cumparator.getNume() + " Varsta: " + cumparator.getVarsta() + " Balanta: " + cumparator.getBalanta() );
            for (int i = 0; i < cumparator.getObiecte().size(); i++) {
                System.out.println(cumparator.getObiecte().get(i).toString());
            }
        }
    }

    public void AdaugareArta(int ID, String nume, int estimare, int an, String numePictor){
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == ID){
                Arta arta = new Arta(nume, estimare, ID, numePictor, an);
                vanzator.adaugaObiect(arta);
            }
        }
        for (Cumparator cumparator : cumparatori) {
            if (cumparator.getID() == ID){
                Arta arta = new Arta(nume, estimare, ID, numePictor, an);
                cumparator.adaugaObiect(arta);
            }
        }
    }

    public void AdaugareAntichitate(int ID, String nume, int estimare, int an, String zona){
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == ID){
                Antichitate antichitate = new Antichitate(nume, estimare, ID, an, zona);
                vanzator.adaugaObiect(antichitate);
            }
        }
        for (Cumparator cumparator : cumparatori) {
            if (cumparator.getID() == ID){
                Antichitate antichitate = new Antichitate(nume, estimare, ID, an, zona);
                cumparator.adaugaObiect(antichitate);
            }
        }
    }

    public void AdaugareCelebritate(int ID, String nume, int estimare, String numeCelebritate){
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == ID){
                Celebritate celebritate = new Celebritate(nume, estimare, ID, numeCelebritate);
                vanzator.adaugaObiect(celebritate);
            }
        }
        for (Cumparator cumparator : cumparatori) {
            if (cumparator.getID() == ID){
                Celebritate celebritate = new Celebritate(nume, estimare, ID, numeCelebritate);
                cumparator.adaugaObiect(celebritate);
            }
        }
    }

    public void StergedinColectie(int PersoanaID, int ObiectId) {
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == PersoanaID) {
                for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                    if (vanzator.getObiecte().get(i).getID() == ObiectId) {
                        vanzator.StergeObiect(vanzator.getObiecte().get(i));
                    }
                }
            }
        }
    }

    public void AfisareAvere(int ID){
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == ID){
                int suma = 0;
                for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                    suma += vanzator.getObiecte().get(i).getEstimare();
                }
                System.out.println(" Nume: " + vanzator.getNume() + " are in balanta " + vanzator.getBalanta() + " si are o colectie de " + suma);
            }
        }
        for (Cumparator cumparator : cumparatori) {
            if (cumparator.getID() == ID){
                System.out.println("ID: " + cumparator.getID() + " Nume: " + cumparator.getNume() + " Varsta: " + cumparator.getVarsta() + " Balanta: " + cumparator.getBalanta() );
                for (int i = 0; i < cumparator.getObiecte().size(); i++) {
                    System.out.println(cumparator.getObiecte().get(i).toString());
                }
            }
        }
    }

    public void CreareOferta(int ProdusID, int CumparatorID, int suma) {
        for (Vanzator vanzator : vanzatori) {
            for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                if (vanzator.getObiecte().get(i).getID() == ProdusID) {
                    Oferta oferta = new Oferta(suma, CumparatorID, vanzator.getID(), ProdusID);
                    oferte.add(oferta);
                    UpdatebestOffert(oferta);
                }
            }
        }
    }

    public void AcceptareOferta(int ProdusID, int CumparatorID) {
        int VanzatorID,  suma;
        String nume = "";
        for (Oferta oferta : oferte) {
            if (oferta.getObiectID()== ProdusID && oferta.getCumparatorID() == CumparatorID) {
                VanzatorID = oferta.getVanzatorID();
                suma = oferta.getPret();
                Obiect produs = null;
                for (Vanzator vanzator : vanzatori) {
                    if (vanzator.getID() == VanzatorID) {
                        for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                            if (vanzator.getObiecte().get(i).getID() == ProdusID) {
                                produs = vanzator.getObiecte().get(i);
                                nume = vanzator.getObiecte().get(i).getNume();
                                vanzator.StergeObiect(vanzator.getObiecte().get(i));
                                vanzator.setBalanta(vanzator.getBalanta() + suma);
                            }
                        }
                    }
                }
                for (Cumparator cumparator : cumparatori) {
                    if (cumparator.getID() == CumparatorID) {
                        cumparator.setBalanta(cumparator.getBalanta() - suma);
                        if (produs instanceof Arta) {
                            AdaugareArta(cumparator.getID(), nume, produs.getEstimare(), ((Arta) produs).getAn(), ((Arta) produs).getAutor());
                        }
                        if (produs instanceof Antichitate) {
                            AdaugareAntichitate(cumparator.getID(), nume, produs.getEstimare(), ((Antichitate) produs).getAn(), ((Antichitate) produs).getZona());
                        }
                        if (produs instanceof Celebritate) {
                            AdaugareCelebritate(cumparator.getID(), nume, produs.getEstimare(), ((Celebritate) produs).getVedeta());
                        }
                    }
                }
                bestOffert.remove(ProdusID);
                StergereOferta(nume); //stergem toate ofertele pentru produsul vandut
            }
        }
    }

    public void AfisareOferte(){
        for (Oferta oferta : oferte) {
            for (Cumparator cumparator : cumparatori) {
                if (cumparator.getID() == oferta.getCumparatorID()){
                    String numeCumparator = cumparator.getNume();
                    for (Vanzator vanzator : vanzatori) {
                        if (vanzator.getID() == oferta.getVanzatorID()){
                            String numeVanzator = vanzator.getNume();
                            for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                                if (vanzator.getObiecte().get(i).getID() == oferta.getObiectID()){
                                    String numeProdus = vanzator.getObiecte().get(i).getNume();
                                    System.out.println("Cumparatorul " + numeCumparator + " ofera " + oferta.getPret() + " pentru produsul " + numeProdus + " al vanzatorului " + numeVanzator);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void AfisareOferte(int ObiectID) {
        for (Oferta oferta : oferte) {
            if (oferta.getObiectID() == ObiectID) {
                for (Cumparator cumparator : cumparatori) {
                    if (cumparator.getID() == oferta.getCumparatorID()) {
                        String numeCumparator = cumparator.getNume();
                        for (Vanzator vanzator : vanzatori) {
                            if (vanzator.getID() == oferta.getVanzatorID()) {
                                String numeVanzator = vanzator.getNume();
                                for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                                    if (vanzator.getObiecte().get(i).getID() == oferta.getObiectID()) {
                                        String numeProdus = vanzator.getObiecte().get(i).getNume();
                                        System.out.println("Cumparatorul " + numeCumparator + " ofera " + oferta.getPret() + " pentru produsul " + numeProdus + " al vanzatorului " + numeVanzator);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void AfisareColectie(int ID){
        System.out.println("Colectia utilizatorului cu ID-ul " + ID + " este: ");
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == ID){
                for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                    System.out.println(vanzator.getObiecte().get(i).toString());
                }
            }
        }
        for (Cumparator cumparator : cumparatori) {
            if (cumparator.getID() == ID){
                for (int i = 0; i < cumparator.getObiecte().size(); i++) {
                    System.out.println(cumparator.getObiecte().get(i).toString());
                }
            }
        }
    }

    public void ModificareOferta(int ObiectID, int CumparatorID, int suma){
        for (Oferta oferta : oferte) {
            if (oferta.getObiectID() == ObiectID && oferta.getCumparatorID() == CumparatorID) {
                oferta.setPret(suma);
                UpdatebestOffert(oferta);
            }
        }
    }

    public void StergereOferta(int OfertaID) {
        for (Oferta oferta : oferte) {
            if (oferta.getObiectID() == OfertaID) {
                oferte.remove(oferta);
            }
        }
    }

    public void StergereOferta(String NumeProdus) {
        for (Oferta oferta : oferte) {
            for (Vanzator vanzator : vanzatori) {
                if (vanzator.getID() == oferta.getVanzatorID()) {
                    for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                        if (vanzator.getObiecte().get(i).getNume() == NumeProdus) {
                            oferte.remove(oferta);
                        }
                    }
                }
            }
        }
    }
    
    public void UpdatebestOffert(Oferta oferta) {
        if (bestOffert.containsKey(oferta.getObiectID())) {
            if (bestOffert.get(oferta.getObiectID()).getPret() < oferta.getPret()) {
                bestOffert.replace(oferta.getObiectID(), oferta);
            }
        } else {
            bestOffert.put(oferta.getObiectID(), oferta);
        }
    }

    public void CeaMaiBunaOferta() {
        for (int i : bestOffert.keySet()) {
            System.out.println("Cea mai buna oferta pentru produsul " + i + " este " + bestOffert.get(i).getPret() + " de la cumparatorul " + bestOffert.get(i).getCumparatorID());
        }
    }
}
