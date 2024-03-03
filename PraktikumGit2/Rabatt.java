public class Rabatt {
    public static void main(String[] args) {
       
        int gesamtBetrag = 1500;
        
        double rabatt = 0;
        if (gesamtBetrag > 1000) { // Mehr als 10 Euro?
            rabatt = gesamtBetrag * 0.10; // Minus 10% Rabatt
        }
       
        double discountPreis = gesamtBetrag - rabatt;
       
        System.out.println("Der Discountpreis beträgt: $" + (discountPreis / 100)); // Umrechnung in Euero
    }
}