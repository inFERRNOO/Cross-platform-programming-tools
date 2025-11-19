package ki305.sapeliuk.lab3;

import ki305.sapeliuk.lab2.*;

/**
 * Клас-драйвер демонструє роботу підкласу AssaultRifle.
 */
public class AssaultRifleApp {
    public static void main(String[] args) {
        String logPath = "assault_rifle_demo.log";

        try (Logger logger = new Logger(logPath)) {
            Barrel barrel = new Barrel(45.0, "5.56x45mm");
            Magazine mag = new Magazine(30, 20);
            Sight optical = new Sight("optical", 3.5);

            AssaultRifle rifle = new AssaultRifle("M4A1", barrel, mag, optical, logger);

            System.out.println("Initial status: " + rifle.getStatus());
            rifle.toggleSafety();   // вимикаємо запобіжник
            rifle.fire(5);          // 5 пострілів
            rifle.reload();         // перезарядка
            rifle.fire(10);         // ще 10 пострілів
            System.out.println("Final status: " + rifle.getStatus());

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
