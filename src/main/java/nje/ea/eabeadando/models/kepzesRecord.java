package nje.ea.eabeadando.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class kepzesRecord {

    private final IntegerProperty id;
    private final StringProperty nev;
    private final StringProperty felveheto;
    private final StringProperty minimum;

    public kepzesRecord(int id, String nev, String felveheto, String minimum) {
        this.id = new SimpleIntegerProperty(id);
        this.nev = new SimpleStringProperty(nev);
        this.felveheto = new SimpleStringProperty(felveheto);
        this.minimum = new SimpleStringProperty(minimum);
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

    public String getFelveheto() {
        return felveheto.get();
    }

    public StringProperty felvehetoProperty() {
        return felveheto;
    }

    public void setFelveheto(String felveheto) {
        this.felveheto.set(felveheto);
    }

    public String getMinimum() {
        return minimum.get();
    }

    public StringProperty minimumProperty() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum.set(minimum);
    }
}