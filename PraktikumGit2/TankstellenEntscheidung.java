import java.util.Scanner;

public class TankstellenEntscheidung {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Eingabe der Tankkapazität in Gallonen
        System.out.print("Tankkapazität in Gallonen (3,78l): ");
        double tankkapazitaet = scanner.nextDouble();

        // Eingabe der Benzinanzeige in Prozent
        System.out.print("Benzinanzeige in Prozent (voll=100, drei viertel voll=75 usw.): ");
        int benzinanzeigeProzent = scanner.nextInt();

        // Eingabe des Benzinverbrauchs des Fahrzeugs in Meilen pro Gallone
        System.out.print("Benzinverbrauch des Fahrzeugs in Meilen pro Gallone: ");
        double benzinverbrauch = scanner.nextDouble();

        // Berechnung der verbleibenden Reichweite in Meilen
        double tankinhalt = tankkapazitaet * (benzinanzeigeProzent / 100.0);
        double verbleibendeReichweite = tankinhalt * benzinverbrauch;

        // Ausgabe je nachdem, ob das Fahrzeug genug Benzin für X Meilen hat oder nicht
        final double MEILEN_X = 50; // Anzahl der nächsten X Meilen
        if (verbleibendeReichweite >= MEILEN_X) {
            System.out.println("Weiterfahren...");
        } else {
            System.out.println("Tanken!");
        }

        scanner.close();
    }
}
