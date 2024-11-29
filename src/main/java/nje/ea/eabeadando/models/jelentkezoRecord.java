package nje.ea.eabeadando.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class jelentkezoRecord {

    private final IntegerProperty id;
    private final StringProperty nev;
    private final StringProperty nem;


    public jelentkezoRecord(int id, String nev, String nem) {
        this.id = new SimpleIntegerProperty(id);
        this.nev = new SimpleStringProperty(nev);
        this.nem = new SimpleStringProperty(nem);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNev() {
        return nev.get();
    }

    public StringProperty nevProperty() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev.set(nev);
    }

    public String getNem() {
        return nem.get();
    }

    public StringProperty nemProperty() {
        return nem;
    }

    public void setNem(String nem) {
        this.nem.set(nem);
    }
}