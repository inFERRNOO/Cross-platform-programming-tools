package ki305.sapeliuk.lab4;

import java.io.FileWriter;
import java.io.IOException; //Обробка винятків вводу-виводу
import java.util.Scanner;

/**
 * Програма-драйвер для тестування класу ExpressionCalculator.
 * <p>
 * Зчитує значення x від користувача, обчислює y = tg(4x)/x
 * та записує результат у файл result.txt.
 * </p>
 */
public class MainDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть значення x: ");
        double x = scanner.nextDouble();

        try {
            double y = ExpressionCalculator.calculate(x);
            System.out.println("Результат обчислення: y = " + y);

            // Запис у файл
            try (FileWriter writer = new FileWriter("result.txt")) {
                writer.write("x = " + x + "\n");
                writer.write("y = " + y + "\n");
                System.out.println("Результат записано у файл result.txt");
            }

        } catch (ArithmeticException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
