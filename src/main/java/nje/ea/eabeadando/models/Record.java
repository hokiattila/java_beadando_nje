package nje.ea.eabeadando.models;

import javafx.beans.property.*;

public class Record {

    private final IntegerProperty jelentkezoId;
    private final StringProperty jelentkezoNev;
    private final StringProperty jelentkezoNem;
    private final StringProperty kepzesNev;
    private final IntegerProperty sorrend;
    private final IntegerProperty szerzettPont;

    public Record(int jelentkezoId, String jelentkezoNev, String jelentkezoNem, String kepzesNev, int sorrend, int szerzettPont) {
        this.jelentkezoId = new SimpleIntegerProperty(jelentkezoId);
        this.jelentkezoNev = new SimpleStringProperty(jelentkezoNev);
        this.jelentkezoNem = new SimpleStringProperty(jelentkezoNem);
        this.kepzesNev = new SimpleStringProperty(kepzesNev);
        this.sorrend = new SimpleIntegerProperty(sorrend);
        this.szerzettPont = new SimpleIntegerProperty(szerzettPont);
    }
    public Record(String jelentkezoNev, String jelentkezoNem, String kepzesNev, int sorrend, int szerzettPont) {
        this.jelentkezoId = new SimpleIntegerProperty(100);
        this.jelentkezoNev = new SimpleStringProperty(jelentkezoNev);
        this.jelentkezoNem = new SimpleStringProperty(jelentkezoNem);
        this.kepzesNev = new SimpleStringProperty(kepzesNev);
        this.sorrend = new SimpleIntegerProperty(sorrend);
        this.szerzettPont = new SimpleIntegerProperty(szerzettPont);
    }


    // Getterek a Property objektumokhoz
    public IntegerProperty jelentkezoIdProperty() {
        return jelentkezoId;
    }

    public StringProperty jelentkezoNevProperty() {
        return jelentkezoNev;
    }

    public StringProperty jelentkezoNemProperty() {
        return jelentkezoNem;
    }

    public StringProperty kepzesNevProperty() {
        return kepzesNev;
    }

    public IntegerProperty sorrendProperty() {
        return sorrend;
    }

    public IntegerProperty szerzettPontProperty() {
        return szerzettPont;
    }

    // Egyszerű getterek az értékekhez
    public int getJelentkezoId() {
        return jelentkezoId.get();
    }

    public String getJelentkezoNev() {
        return jelentkezoNev.get();
    }

    public String getJelentkezoNem() {
        return jelentkezoNem.get();
    }

    public String getKepzesNev() {
        return kepzesNev.get();
    }

    public int getSorrend() {
        return sorrend.get();
    }

    public int getSzerzettPont() {
        return szerzettPont.get();
    }
}