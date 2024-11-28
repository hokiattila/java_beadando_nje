package nje.ea.eabeadando.models;

public class Record {

    private final int jelentkezoId;
    private final String jelentkezoNev;
    private final String jelentkezoNem;
    private final String kepzesNev;
    private final int sorrend;
    private final int szerzett;

    public Record(int jelentkezoId, String jelentkezoNev, String jelentkezoNem, String kepzesNev, int sorrend, int szerzett) {
        this.jelentkezoId = jelentkezoId;
        this.jelentkezoNev = jelentkezoNev;
        this.jelentkezoNem = jelentkezoNem;
        this.kepzesNev = kepzesNev;
        this.sorrend = sorrend;
        this.szerzett = szerzett;
    }

    public int getJelentkezoId() {
        return jelentkezoId;
    }

    public String getJelentkezoNev() {
        return jelentkezoNev;
    }

    public String getJelentkezoNem() {
        return jelentkezoNem;
    }

    public String getKepzesNev() {
        return kepzesNev;
    }

    public int getSorrend() {
        return sorrend;
    }

    public int getSzerzett() {
        return szerzett;
    }
}
