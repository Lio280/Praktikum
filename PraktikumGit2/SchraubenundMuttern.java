import java.util.Scanner;

public class SchraubenundMuttern {
    // Deklaration der Preise als Konstanten
    private static final int SCHRAUBE_PREIS = 5; // in Cent
    private static final int MUTTER_PREIS = 3; // in Cent
    private static final int UNTERLEGSCHEIBE_PREIS = 1; // in Cent

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Eingabe der Anzahl von Schrauben, Muttern und Unterlegscheiben
        System.out.print("Anzahl der Schrauben: ");
        int anzahlSchrauben = scanner.nextInt();
        System.out.print("Anzahl der Muttern: ");
        int anzahlMuttern = scanner.nextInt();
        System.out.print("Anzahl der Unterlegscheiben: ");
        int anzahlUnterlegscheiben = scanner.nextInt();

        // Berechnung des Gesamtbetrags
        int gesamtBetrag = (anzahlSchrauben * SCHRAUBE_PREIS) + (anzahlMuttern * MUTTER_PREIS)
                + (anzahlUnterlegscheiben * UNTERLEGSCHEIBE_PREIS);

        // Überprüfung der Bestellung
        if (anzahlSchrauben > anzahlMuttern) {
            System.out.println("Kontrollieren Sie Ihre Bestellung!");
        } else {
            System.out.println("Die Bestellung ist okay.");
        }

        // Ausgabe des Gesamtbetrags
        System.out.println("Gesamtbetrag: " + gesamtBetrag + " Cent");

        scanner.close();
    }
}
