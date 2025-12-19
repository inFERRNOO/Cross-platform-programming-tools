package ki305.sapeliuk.lab2;
import ki305.sapeliuk.lab3.AssaultRifle;

import java.io.IOException;

/**
 * Клас-драйвер для демонстрації роботи Avtomat.
 */
public class AvtomatApp { // тестує всі функції автоматичної зброї
    public static void main(String[] args) {
        // Шлях до логу (створиться поруч з робочою директорією)
        String logPath = "avtomat.log"; //ініціалізація логера

        try (Logger logger = new Logger(logPath)) {
            // Створюємо об'єкти компонентів
            Barrel barrel = new Barrel(41.0, "7.62x39mm");
            Magazine mag = new Magazine(30, 10); // початково 10 набоїв
            Sight iron = new Sight("iron", 1.0);

            // Конструктор повної ініціалізації
            AssaultRifle av = new AssaultRifle("AK-Example", barrel, mag, iron, logger);

            // Демонстрація: ситуації та виклики методів
            logger.log("=== DEMO START ===");
            System.out.println("Status: " + av.getStatus());

            av.toggleSafety();              // зняти запобіжник
            av.setMode(Avtomat.FireMode.SEMI);
            av.triggerPull();               // одиночний постріл
            av.setMode(Avtomat.FireMode.AUTO);
            av.triggerPull();               // автоматична черга
            av.jam();                       // примусово заклинити
            av.triggerPull();               // не має стрільби
            av.clearJam();                  // прочистити
            av.reload(new Magazine(30, 30)); // перезарядка повним магазином
            av.setMode(Avtomat.FireMode.BURST);
            av.triggerPull();
            double acc = av.aim();
            System.out.printf("Estimated accuracy: %.1f%%%n", acc);
            av.changeBarrel(new Barrel(45.0, "7.62x39mm"));
            av.detachSight();
            av.unload();
            logger.log("=== DEMO END ===");

            // Вивід статусу в консоль
            System.out.println("Final status: " + av.getStatus());
            System.out.println("Logs written to: " + logPath);

        } catch (IOException e) {
            System.err.printf("Failed to initialize logger: %s%n", e.getMessage());
            e.printStackTrace();
        }
        // Logger буде закритий автоматично завдяки try-with-resources
    }
}
