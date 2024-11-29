package nje.ea.eabeadando.dao;

import nje.ea.eabeadando.models.Record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO {

    private static final String DB_URL = "jdbc:sqlite:C:/adatok/adatok.db";

    // Lekérdezi az összes rekordot
    public List<Record> getAllRecords() {
        List<Record> records = new ArrayList<>();

        String query = "SELECT jelentkezo.id AS jelentkezoId, " +
                "jelentkezo.nev AS jelentkezoNev, " +
                "jelentkezo.nem AS jelentkezoNem, " +
                "kepzes.nev AS kepzesNev, " +
                "jelentkezes.sorrend AS sorrend, " +
                "jelentkezes.szerzett AS szerzettPont " +
                "FROM jelentkezo " +
                "INNER JOIN jelentkezes ON jelentkezo.id = jelentkezes.jelentkezoid " +
                "INNER JOIN kepzes ON jelentkezes.kepzesid = kepzes.id" +
                " ORDER BY jelentkezo.id ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int jelentkezoId = rs.getInt("jelentkezoId");
                String jelentkezoNev = rs.getString("jelentkezoNev");
                String jelentkezoNem = rs.getString("jelentkezoNem");
                String kepzesNev = rs.getString("kepzesNev");
                int sorrend = rs.getInt("sorrend");
                int szerzettPont = rs.getInt("szerzettPont");

                records.add(new Record(jelentkezoId, jelentkezoNev, jelentkezoNem, kepzesNev, sorrend, szerzettPont));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    // Lekérdezi az egyedi képzési neveket az adatbázisból
    public List<String> getUniqueKepzesNev() {
        List<String> kepzesNevList = new ArrayList<>();
        String query = "SELECT DISTINCT kepzes.nev AS kepzesNev FROM kepzes ORDER BY kepzesNev ASC"; // Lekérdezés az egyedi képzési nevekre

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                kepzesNevList.add(rs.getString("kepzesNev"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kepzesNevList;
    }
}
