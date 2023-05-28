package service;

import audit.Audit;
import model.produse.Antichitate;
import model.produse.Arta;
import model.produse.Celebritate;
import model.produse.Obiect;
import model.tranzactii.Oferta;
import model.utilizatori.Cumparator;
import model.utilizatori.Vanzator;

import java.io.IOException;
import java.sql.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import database.DataBaseService;

public class AuctionService {
    private List<Cumparator> cumparatori = new ArrayList<>();
    private List<Vanzator> vanzatori = new ArrayList<>();
    private List<Oferta> oferte = new ArrayList<>();
    private Hashtable<Integer, Oferta> bestOffert = new Hashtable<>();

    private DataBaseService dataBaseService = new DataBaseService();

    private Audit auditService = new Audit();

    public void RepartizareObiecte (List<Obiect> obiecte) {
        for (Obiect obiect : obiecte) {
            if (obiect instanceof Antichitate) {
                for (Vanzator vanzator : vanzatori) {
                    if (vanzator.getID() == obiect.getProprietarID()) {
                        vanzator.adaugaObiect(obiect);
                    }
                }
            }
            if (obiect instanceof Arta) {
                for (Vanzator vanzator : vanzatori) {
                    if (vanzator.getID() == obiect.getProprietarID()) {
                        vanzator.adaugaObiect(obiect);
                    }
                }
            }
            if (obiect instanceof Celebritate) {
                for (Vanzator vanzator : vanzatori) {
                    if (vanzator.getID() == obiect.getProprietarID()) {
                        vanzator.adaugaObiect(obiect);
                    }
                }
            }
        }
    }

    public void setVanzatori(List<Vanzator> vanzatori) {
        this.vanzatori = vanzatori;
    }

    public void setCumparatori(List<Cumparator> cumparatori) {
        this.cumparatori = cumparatori;
    }

    public void AdaugaVanzator (String nume, int varsta, int balanta){
        Vanzator vanzator = new Vanzator(nume, varsta, balanta);
        vanzatori.add(vanzator);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO vanzator  VALUES (" + vanzator.getID() + ", '" + nume + "', " + varsta + ", " + balanta + ")");
            auditService.logAction("AdaugaVanzator");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO cumparator  VALUES (" + cumparator.getID() + ", '" + nume + "', " + varsta + ", " + balanta + ")");
            auditService.logAction("AdaugaCumparator");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        int obiectID = 0;
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == ID){
                Arta arta = new Arta(nume, estimare, ID, numePictor, an);
                vanzator.adaugaObiect(arta);
                obiectID = arta.getID();
            }
        }
        for (Cumparator cumparator : cumparatori) {
            if (cumparator.getID() == ID){
                Arta arta = new Arta(nume, estimare, ID, numePictor, an);
                cumparator.adaugaObiect(arta);
                obiectID = arta.getID();
            }
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO arta  VALUES (" + obiectID +","+ ID +", '" + nume + "', " + estimare + ", '" + numePictor + "', " + an + ")");
            auditService.logAction("AdaugareArta");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AdaugareAntichitate(int ID, String nume, int estimare, int an, String zona){
        int obiectID = 0;
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == ID){
                Antichitate antichitate = new Antichitate(nume, estimare, ID, an, zona);
                vanzator.adaugaObiect(antichitate);
                obiectID = antichitate.getID();
            }
        }
        for (Cumparator cumparator : cumparatori) {
            if (cumparator.getID() == ID){
                Antichitate antichitate = new Antichitate(nume, estimare, ID, an, zona);
                cumparator.adaugaObiect(antichitate);
                obiectID = antichitate.getID();
            }
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO antichitate  VALUES (" + obiectID +","+ ID +", '" + nume + "', " + estimare + ", '" + zona + "', " + an + ")");
            auditService.logAction("AdaugareAntichitate");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AdaugareCelebritate(int ID, String nume, int estimare, String numeCelebritate){
        int obiectID = 0;
        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == ID){
                Celebritate celebritate = new Celebritate(nume, estimare, ID, numeCelebritate);
                vanzator.adaugaObiect(celebritate);
                obiectID = celebritate.getID();
            }
        }
        for (Cumparator cumparator : cumparatori) {
            if (cumparator.getID() == ID){
                Celebritate celebritate = new Celebritate(nume, estimare, ID, numeCelebritate);
                cumparator.adaugaObiect(celebritate);
                obiectID = celebritate.getID();
            }
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO celebritate  VALUES (" + obiectID +","+ ID +", '" + nume + "', " + estimare + ", '" + numeCelebritate + "')");
            auditService.logAction("AdaugareCelebritate");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void StergedinColectie(int PersoanaID, int ObiectId) {

        for (Vanzator vanzator : vanzatori) {
            if (vanzator.getID() == PersoanaID) {
                for (int i = 0; i < vanzator.getObiecte().size(); i++) {
                    if (vanzator.getObiecte().get(i).getID() == ObiectId) {
                        if (vanzator.getObiecte().get(i) instanceof Arta) {
                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
                                Statement statement = connection.createStatement();
                                statement.execute("DELETE FROM arta WHERE ID = " + ObiectId);
                                auditService.logAction("StergedinColectie");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (vanzator.getObiecte().get(i) instanceof Antichitate) {
                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
                                Statement statement = connection.createStatement();
                                statement.execute("DELETE FROM antichitate WHERE ID = " + ObiectId);
                                auditService.logAction("StergedinColectie");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (vanzator.getObiecte().get(i) instanceof Celebritate) {
                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
                                Statement statement = connection.createStatement();
                                statement.execute("DELETE FROM celebritate WHERE ID = " + ObiectId);
                                auditService.logAction("StergedinColectie");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
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


    public void Comenzi() {
        System.out.println("Comenzi disponibile: ");
        System.out.println("1. Adaugare");
        System.out.println("2. Stergere");
        System.out.println("3. Modificare");
        System.out.println("4. Afisare");
        System.out.println("5. Exit");

        Scanner scanner = new Scanner(System.in);
        int comanda = scanner.nextInt();
        scanner.nextLine();

        switch (comanda) {
            case 1:
                Adaugare();
                break;
            case 2:
                Stergere();
                break;
            case 3:
                Modificare();
                break;
            case 4:
                Afisare();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Comanda invalida");
                break;
        }
    }

    public void Afisare() {
        System.out.println("1. Afisare oferte");
        System.out.println("2. Afisare oferte pentru un produs");
        System.out.println("3. Afisare colectie");
        System.out.println("4. Afisare cea mai buna oferta");
        System.out.println("5. Afisare avere");

        Scanner scanner = new Scanner(System.in);
        int comanda = scanner.nextInt();
        scanner.nextLine();

        switch (comanda) {
            case 1:
                AfisareOferte();
                break;
            case 2:
                System.out.println("Introduceti ID-ul Produs: ");
                int ID = scanner.nextInt();
                scanner.nextLine();
                AfisareOferte(ID);
                break;
            case 3:
                System.out.println("Introduceti ID-ul utilizatorului: ");
                int ID2 = scanner.nextInt();
                scanner.nextLine();
                AfisareColectie(ID2);
                break;
            case 4:
                CeaMaiBunaOferta();
                break;
            case 5:
                System.out.println("Introduceti ID-ul utilizatorului: ");
                int ID3 = scanner.nextInt();
                scanner.nextLine();
                AfisareAvere(ID3);
                break;
            default:
                System.out.println("Comanda invalida");
                break;
        }
    }

    public void Adaugare() {
        System.out.println("1. Adaugare oferta");
        System.out.println("2. Adaugare produs");
        System.out.println("3. Adaugare cumparator");
        System.out.println("4. Adaugare vanzator");

        Scanner scanner = new Scanner(System.in);
        int comanda = scanner.nextInt();
        scanner.nextLine();

        switch (comanda) {
            case 1:
                System.out.println("Introduceti ID-ul produsului: ");
                int ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti ID-ul cumparatorului: ");
                int ID2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti pretul: ");
                int pret = scanner.nextInt();
                scanner.nextLine();
                CreareOferta(ID, ID2, pret);
                break;
            case 2:
                AdaugareObiect();
                break;
            case 3:
                System.out.println("Introduceti numele cumparatorului: ");
                String nume = scanner.nextLine();
                System.out.println("Varsta: ");
                int varsta = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Balanta: ");
                int balanta = scanner.nextInt();
                scanner.nextLine();
                AdaugaCumparator(nume, varsta, balanta);
                break;
            case 4:
                System.out.println("Introduceti numele vanzatorului: ");
                String nume2 = scanner.nextLine();
                System.out.println("Varsta: ");
                int varsta2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Balanta: ");
                int balanta2 = scanner.nextInt();
                scanner.nextLine();
                AdaugaVanzator(nume2, varsta2, balanta2);
                break;
        }
    }

    public void Stergere () {
        System.out.println("1. Stergere oferta");
        System.out.println("2. Stergere produs");

        Scanner scanner = new Scanner(System.in);
        int comanda = scanner.nextInt();
        scanner.nextLine();

        switch (comanda) {
            case 1:
                System.out.println("Introduceti ID-ul ofertei: ");
                int ID = scanner.nextInt();
                scanner.nextLine();
                StergereOferta(ID);
                break;
            case 2:
                System.out.println("Introduceti ID-ul detinatorului: ");
                int ID2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti ID-ul produsului: ");
                int ID3 = scanner.nextInt();
                scanner.nextLine();
                StergedinColectie(ID2, ID3);
                break;
        }
    }

    public void Modificare() {
        System.out.println("1. Modificare oferta");

        Scanner scanner = new Scanner(System.in);
        int comanda = scanner.nextInt();
        scanner.nextLine();

        switch (comanda) {
            case 1:
                System.out.println("Introduceti ID-ul obiectului: ");
                int ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti ID-ul cumparatorului: ");
                int ID2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti noul pret: ");
                int pret = scanner.nextInt();
                scanner.nextLine();
                ModificareOferta(ID, ID2, pret);
                break;
        }
    }

    public void AdaugareObiect () {
        System.out.println("1. Arta");
        System.out.println("2. Antichitate");
        System.out.println("3. Celebritate");

        Scanner scanner = new Scanner(System.in);
        int comanda = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introduceti ID-ul detinatorului: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduceti numele obiectului: ");
        String nume = scanner.nextLine();
        System.out.println("Introduceti pretul: ");
        int pret = scanner.nextInt();
        scanner.nextLine();
        switch (comanda) {
            case 1:
                System.out.println("Introduceti anul: ");
                int an = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti autorul: ");
                String autor = scanner.nextLine();
                AdaugareArta(ID, nume, pret, an, autor);
                break;
            case 2:
                System.out.println("Introduceti anul: ");
                int an2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduceti zona: ");
                String zona = scanner.nextLine();
                AdaugareAntichitate(ID, nume, pret, an2, zona);
                break;
            case 3:
                System.out.println("Introduceti numele celebritatii: ");
                String nume2 = scanner.nextLine();
                AdaugareCelebritate(ID, nume, pret, nume2);
        }
    }

}
