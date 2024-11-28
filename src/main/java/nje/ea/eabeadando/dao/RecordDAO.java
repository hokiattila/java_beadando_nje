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
                "INNER JOIN kepzes ON jelentkezes.kepzesid = kepzes.id";

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
}
