package Bank;

import soapclient.MNBArfolyamServiceSoap;
import soapclient.MNBArfolyamServiceSoapImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.*;
=======
>>>>>>> 6c99da4 (Added first iteration of MNB SOAP Service)
=======
import java.util.Scanner;
>>>>>>> 3422fb3 (Adjusted SOAP API)
=======
import java.util.*;
>>>>>>> 1f40417 (Adjusted ordering in MNB API)
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class Main {
    static public void main(String[] args) {
<<<<<<< HEAD
<<<<<<< HEAD
        String startDate = "";
        String endDate = "";
        String currency = "";
        if (args.length == 0) {
=======
        String startDate = "";
        String endDate = "";
        String currency = "";
<<<<<<< HEAD
        if(args.length == 0) {
>>>>>>> 3422fb3 (Adjusted SOAP API)
=======
        if (args.length == 0) {
>>>>>>> 1f40417 (Adjusted ordering in MNB API)
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Adja meg a valutat: ");
                currency = sc.nextLine();
                System.out.println("Adja meg a kezdonapot:");
                startDate = sc.nextLine();
                System.out.println("Adja meg a vegnapot:");
                endDate = sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            currency = args[0];
            startDate = args[1];
            endDate = args[2];
        }
<<<<<<< HEAD
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();
        StringBuilder output = new StringBuilder();
        try {
            String exchangeRates = service.getExchangeRates(startDate, endDate, currency);
            output.append(currency).append("\n").append(parseExchangeRates(exchangeRates)).append("\n");
            String filePath = "c:\\adatok\\mnb.txt";
            Files.write(Paths.get(filePath), output.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Az adatok sikeresen kiírva a következő fájlba: " + filePath);
=======
=======
>>>>>>> 3422fb3 (Adjusted SOAP API)
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();
        StringBuilder output = new StringBuilder();
        try {
            String exchangeRates = service.getExchangeRates(startDate, endDate, currency);
            output.append(currency).append("\n").append(parseExchangeRates(exchangeRates)).append("\n");
            String filePath = "c:\\adatok\\mnb.txt";
            Files.write(Paths.get(filePath), output.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Az adatok sikeresen kiírva a következő fájlba: " + filePath);
<<<<<<< HEAD

>>>>>>> 6c99da4 (Added first iteration of MNB SOAP Service)
=======
>>>>>>> 3422fb3 (Adjusted SOAP API)
        } catch (Exception e) {
            System.err.println("Hiba történt: " + e.getMessage());
        }
    }

    // XML Parsing metódus
    private static String parseExchangeRates(String xmlData) {
        StringBuilder parsedData = new StringBuilder();
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 6c99da4 (Added first iteration of MNB SOAP Service)
=======
>>>>>>> 3422fb3 (Adjusted SOAP API)
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new java.io.ByteArrayInputStream(xmlData.getBytes()));

            NodeList dayList = document.getElementsByTagName("Day");

<<<<<<< HEAD
<<<<<<< HEAD
            // Használjunk TreeMap-et a dátumok automatikus rendezéséhez
            TreeMap<String, String> sortedRates = new TreeMap<>();

            for (int i = 0; i < dayList.getLength(); i++) {
                Element dayElement = (Element) dayList.item(i);
                String date = dayElement.getAttribute("date");
=======
            for (int i = 0; i < dayList.getLength(); i++) {
                Element dayElement = (Element) dayList.item(i);
                String date = dayElement.getAttribute("date");
                parsedData.append("Date: ").append(date).append("\n");
>>>>>>> 6c99da4 (Added first iteration of MNB SOAP Service)
=======
            // Használjunk TreeMap-et a dátumok automatikus rendezéséhez
            TreeMap<String, String> sortedRates = new TreeMap<>();

            for (int i = 0; i < dayList.getLength(); i++) {
                Element dayElement = (Element) dayList.item(i);
                String date = dayElement.getAttribute("date");
>>>>>>> 1f40417 (Adjusted ordering in MNB API)

                NodeList rateList = dayElement.getElementsByTagName("Rate");

                for (int j = 0; j < rateList.getLength(); j++) {
                    Element rateElement = (Element) rateList.item(j);
<<<<<<< HEAD
<<<<<<< HEAD
                    String value = rateElement.getTextContent();

                    // Tegyük a dátumot és az értéket a TreeMap-be
                    sortedRates.put(date, value);
                }
            }

            // A rendezett adatok kiírása
            for (Map.Entry<String, String> entry : sortedRates.entrySet()) {
                parsedData.append(entry.getKey()).append("\n");
                parsedData.append(entry.getValue()).append("\n");
            }

=======
                    String currency = rateElement.getAttribute("curr");
                    String unit = rateElement.getAttribute("unit");
=======
>>>>>>> 1f40417 (Adjusted ordering in MNB API)
                    String value = rateElement.getTextContent();

                    // Tegyük a dátumot és az értéket a TreeMap-be
                    sortedRates.put(date, value);
                }
            }
<<<<<<< HEAD
>>>>>>> 6c99da4 (Added first iteration of MNB SOAP Service)
=======

            // A rendezett adatok kiírása
            for (Map.Entry<String, String> entry : sortedRates.entrySet()) {
                parsedData.append(entry.getKey()).append("\n");
                parsedData.append(entry.getValue()).append("\n");
            }

>>>>>>> 1f40417 (Adjusted ordering in MNB API)
        } catch (Exception e) {
            parsedData.append("Hiba történt az XML feldolgozása során: ").append(e.getMessage());
        }
        return parsedData.toString();
    }
}
