package nje.ea.eabeadando.dao;

import nje.ea.eabeadando.models.Record;

import java.sql.*;
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
    public boolean insertJelentkezo(String nev, String nem) {
        String query = "INSERT INTO jelentkezo (nev, nem) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nev);
            pstmt.setString(2, nem);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertKepzes(String nev, int felveheto, int minimum) {
        String query = "INSERT INTO kepzes (nev, felveheto, minimum) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nev);
            pstmt.setInt(2, felveheto);
            pstmt.setInt(3, minimum);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertJelentkezes(int jelentkezoId, int kepzesId, int sorrend, int szerzettPont) {
        String query = "INSERT INTO jelentkezes (jelentkezoid, kepzesid, sorrend, szerzett) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, jelentkezoId);
            pstmt.setInt(2, kepzesId);
            pstmt.setInt(3, sorrend);
            pstmt.setInt(4, szerzettPont);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getKepzesIdByName(String kepzesNev) {
        String query = "SELECT id FROM kepzes WHERE nev = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, kepzesNev);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Ha nem találunk ilyen képzést, akkor -1-et adunk vissza
    }

    // Lekérdezi az összes jelentkező nevét
    public List<String> getAllApplicants() {
        List<String> applicants = new ArrayList<>();
        String query = "SELECT nev FROM jelentkezo ORDER BY nev ASC"; // Lekérdezés a jelentkezők neveire

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                applicants.add(rs.getString("nev"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicants;
    }

    // Lekérdezi az összes képzés nevét
    public List<String> getAllCourses() {
        List<String> courses = new ArrayList<>();
        String query = "SELECT nev FROM kepzes ORDER BY nev ASC"; // Lekérdezés a képzések neveire

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                courses.add(rs.getString("nev"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }
    // Jelentkező ID lekérése a név alapján
    public int getApplicantIdByName(String name) {
        int applicantId = -1;
        String query = "SELECT id FROM jelentkezo WHERE nev = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                applicantId = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicantId;
    }

    // Képzés ID lekérése a név alapján
    public int getCourseIdByName(String name) {
        int courseId = -1;
        String query = "SELECT id FROM kepzes WHERE nev = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                courseId = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseId;
    }

    // Jelentkező hozzáadása
    public void addJelentkezo(String name, String gender) {
        String query = "INSERT INTO jelentkezo (nev, nem) VALUES (?, ?)";

        String formattedGender = "f";
        if (gender.toLowerCase() == "fiú") formattedGender = "f";
        else formattedGender = "l";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, formattedGender);
            stmt.executeUpdate();
            System.out.println("Sikeresen hozzaadva: " + name + formattedGender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Képzés hozzáadása
    public void addKepzes(String name, int capacity, int minScore) {
        String query = "INSERT INTO kepzes (nev, felveheto, minimum) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, capacity);
            stmt.setInt(3, minScore);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Jelentkezés hozzáadása
    public void addJelentkezes(int applicantId, int courseId, int rank, int score) {
        String query = "INSERT INTO jelentkezes (jelentkezoid, kepzesid, sorrend, szerzett) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, applicantId);
            stmt.setInt(2, courseId);
            stmt.setInt(3, rank);
            stmt.setInt(4, score);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }