package database;
import model.produse.*;
import model.produse.Obiect;
import model.utilizatori.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {
    init baza = new init();
    public void creareTabele(){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            statement.execute(baza.CreazaTabelVanzatori());
            statement.execute(baza.CreazaTabelCumparatori());
            statement.execute(baza.CreazaTabelObiecteArta());
            statement.execute(baza.CreazaTabelObiectAntichitate());
            statement.execute(baza.CreazaTabelObiectCelebritate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Vanzator> preiaDateTabelVanzatori(){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Vanzator");
            List<Vanzator> vanzatori = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                int balanta = rs.getInt("balanta");
                Vanzator vanzator = new Vanzator(id, nume, varsta, balanta);
                vanzatori.add(vanzator);
            }
            return vanzatori;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cumparator> preiaDateTabelCumparator () {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Cumparator");
            List<Cumparator> cumparatori = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                int balanta = rs.getInt("balanta");
                Cumparator cumparator = new Cumparator(id, nume, varsta, balanta);
                cumparatori.add(cumparator);
            }
            return cumparatori;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Obiect> preiaDateTabelObiecte() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao_252", "root", "admin")) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Antichitate");
            List<Obiect> obiecte = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int proprietarID = rs.getInt("proprietarID");
                String nume = rs.getString("nume");
                int estimare = rs.getInt("estimare");
                String zona = rs.getString("zona");
                int an = rs.getInt("an");
                Antichitate antichitate = new Antichitate(id, nume, estimare, proprietarID, an, zona);
                obiecte.add(antichitate);
            }
            rs = statement.executeQuery("SELECT * FROM Celebritate");
            while (rs.next()) {
                int id = rs.getInt("id");
                int proprietarID = rs.getInt("proprietarID");
                String nume = rs.getString("nume");
                int estimare = rs.getInt("estimare");
                String vedeta = rs.getString("vedeta");
                Celebritate celebritate = new Celebritate(id, nume, estimare, proprietarID, vedeta);
                obiecte.add(celebritate);
            }
            rs = statement.executeQuery("SELECT * FROM Arta");
            while (rs.next()) {
                int id = rs.getInt("id");
                int proprietarID = rs.getInt("proprietarID");
                String nume = rs.getString("nume");
                int estimare = rs.getInt("estimare");
                String autor = rs.getString("autor");
                int an = rs.getInt("an");
                Arta arta = new Arta(id, nume, estimare, proprietarID, autor, an);
                obiecte.add(arta);
            }
            return obiecte;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
