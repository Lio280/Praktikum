import java.util.Random;
import java.util.Scanner;

public class RandomNR {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Was möchtest du generieren?");
        System.out.println("1. Zufallszahl");
        System.out.println("2. Würfel");
        System.out.println("3. Münzwurf");
        int choice = scanner.nextInt();
        
        switch(choice) {
            case 1:
                generateRandomNumber(scanner);
                break;
            case 2:
                rollDice(scanner);
                break;
            case 3:
                flipCoin();
                break;
            default:
                System.out.println("Ungültige Auswahl.");
        }
        
        scanner.close();
    }
    
    public static void generateRandomNumber(Scanner scanner) {
        System.out.println("Bitte geben Sie den Bereich für die Zufallszahl ein:");
        System.out.print("Von (z.B. 15 100): ");
        int min = scanner.nextInt();
        System.out.print("Bis (z.B. 10000): ");
        int max = scanner.nextInt();
        
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        
        System.out.println("Die generierte Zufallszahl ist: " + randomNumber);
    }
    
    public static void rollDice(Scanner scanner) {
        System.out.println("Bitte geben Sie die Anzahl der Seiten des Würfels ein:");
        int sides = scanner.nextInt();
        
        if (sides <= 0) {
            System.out.println("Ungültige Anzahl von Seiten.");
            return;
        }
        
        Random random = new Random();
        int diceResult = random.nextInt(sides) + 1;
        
        System.out.println("Der Würfel mit " + sides + " Seiten zeigt: " + diceResult);
    }
    
    public static void flipCoin() {
        try{
            Random random = new Random();
            int coinResult = random.nextInt(2); // 0 für Kopf, 1 für Zahl
            
            if(coinResult == 0) {
                System.out.println("Die Münze zeigt Kopf.");
            } else {
                System.out.println("Die Münze zeigt Zahl.");
            }
        }catch(Exception ex){
            System.out.println(ex.getCause());
        }
    }
}