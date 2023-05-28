package database;

public class init {

    public String CreazaTabelVanzatori() {
        String s = "CREATE TABLE IF NOT EXISTS Vanzator ("
                + "	id integer PRIMARY KEY,"
                + "	nume VARCHAR(30) NOT NULL,"
                + "	varsta integer NOT NULL,"
                + "	balanta integer NOT NULL"
                + ")";
        return s;
    }

    public String CreazaTabelCumparatori() {
        String s = "CREATE TABLE IF NOT EXISTS Cumparator ("
                + "	id integer PRIMARY KEY,"
                + "	nume VARCHAR(30) NOT NULL,"
                + "	varsta integer NOT NULL,"
                + "	balanta integer NOT NULL"
                + ")";
        return s;
    }

    public String CreazaTabelObiecteArta() {
        String s = "CREATE TABLE IF NOT EXISTS Arta ("
                + "	id integer PRIMARY KEY,"
                + " proprietarID integer NOT NULL,"
                + "	nume VARCHAR(30) NOT NULL,"
                + "	estimare integer NOT NULL,"
                + "	autor VARCHAR(30) NOT NULL,"
                + "	an integer NOT NULL"
                + ")";
        return s;
    }

    public String CreazaTabelObiectAntichitate() {
        String s = "CREATE TABLE IF NOT EXISTS Antichitate ("
                + "	id integer PRIMARY KEY,"
                + " proprietarID integer NOT NULL,"
                + "	nume VARCHAR(30) NOT NULL,"
                + "	estimare integer,"
                + "	zona VARCHAR(30) NOT NULL,"
                + "	an integer NOT NULL"
                + ")";
        return s;
    }

    public String CreazaTabelObiectCelebritate () {
        String s = "CREATE TABLE IF NOT EXISTS Celebritate ("
                + "	id integer PRIMARY KEY,"
                + " proprietarID integer NOT NULL,"
                + "	nume VARCHAR(30) NOT NULL,"
                + "	estimare integer NOT NULL,"
                + "vedeta VARCHAR(30) NOT NULL"
                + ")";
        return s;
    }
}
