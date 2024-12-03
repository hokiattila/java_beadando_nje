package Bank;

import soapclient.MNBArfolyamServiceSoap;
import soapclient.MNBArfolyamServiceSoapImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class Main {
    static public void main(String[] args) {
        String startDate = "";
        String endDate = "";
        String currency = "";
        if (args.length == 0) {
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
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();
        StringBuilder output = new StringBuilder();
        try {
            String exchangeRates = service.getExchangeRates(startDate, endDate, currency);
            output.append(currency).append("\n").append(parseExchangeRates(exchangeRates)).append("\n");
            String filePath = "c:\\adatok\\mnb.txt";
            Files.write(Paths.get(filePath), output.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Az adatok sikeresen kiírva a következő fájlba: " + filePath);
        } catch (Exception e) {
            System.err.println("Hiba történt: " + e.getMessage());
        }
    }

    // XML Parsing metódus
    private static String parseExchangeRates(String xmlData) {
        StringBuilder parsedData = new StringBuilder();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new java.io.ByteArrayInputStream(xmlData.getBytes()));

            NodeList dayList = document.getElementsByTagName("Day");

            // Használjunk TreeMap-et a dátumok automatikus rendezéséhez
            TreeMap<String, String> sortedRates = new TreeMap<>();

            for (int i = 0; i < dayList.getLength(); i++) {
                Element dayElement = (Element) dayList.item(i);
                String date = dayElement.getAttribute("date");

                NodeList rateList = dayElement.getElementsByTagName("Rate");

                for (int j = 0; j < rateList.getLength(); j++) {
                    Element rateElement = (Element) rateList.item(j);
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

        } catch (Exception e) {
            parsedData.append("Hiba történt az XML feldolgozása során: ").append(e.getMessage());
        }
        return parsedData.toString();
    }
}
