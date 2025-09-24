package kthep_labs;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Варіант №2: квадрат поділений діагоналлю.
 * <p>
 * Лівий нижній трикутник заштрихований символом-заповнювачем,
 * у нижньому лівому кутку знаходиться порожній квадрат.
 * </p>
 * Програма:
 * <ul>
 *   <li>зчитує розмір квадратної матриці та символ-заповнювач з клавіатури;</li>
 *   <li>генерує зубчастий масив відповідно до умови;</li>
 *   <li>виводить результат у консоль та у текстовий файл.</li>
 * </ul>
 *
 * @author Ірина
 * @version 1.0
 */

public class Lab1Sapeliuk_Iryna_KI_305 {

	/**
     * Генерує зубчастий масив для варіанту №2.
     * <p>
     * Утворюється квадрат, розділений по головній діагоналі.
     * Ліва нижня половина заштрихована, але у самому нижньому
     * лівому кутку вирізається порожній квадрат.
     * </p>
     *
     * @param n        розмір квадратної матриці
     * @param fillChar символ-заповнювач (наприклад '@')
     * @return масив рядків, який представляє заштриховану фігуру
     */
    public static String[] generateArray(int n, char fillChar) {
        String[] result = new String[n];

        int squareSize = n / 2; // розмір "білого квадрата" у лівому нижньому кутку
        char emptyChar = ' ';   // пробіл за замовчуванням

        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if (j <= i) { // ліва нижня половина
                    // перевірка: чи всередині "білого квадрата"
                    if (i >= n - squareSize && j < squareSize) {
                        row.append(emptyChar);
                    } else {
                        row.append(fillChar);
                    }
                } else { // права верхня половина
                    row.append(emptyChar);
                }
            }
            result[i] = row.toString();
        }
        return result;
    }

    /** Вивід у консоль і файл */
    public static void printAndSave(String[] arr, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String row : arr) {
                System.out.println(row);
                writer.write(row + "\n");
            }
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }
    /**
     * Головний метод програми.
     * <p>
     * Зчитує дані з клавіатури, перевіряє їх коректність,
     * генерує зубчастий масив і виводить результат.
     * </p>
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Введіть розмір квадратної матриці n: ");
            int n = Integer.parseInt(sc.nextLine());

            System.out.print("Введіть символ-заповнювач (наприклад @): ");
            String inputFill = sc.nextLine();

            if (inputFill.length() != 1) {
                System.out.println("Помилка: потрібно ввести рівно один символ!");
                return;
            }
            char fillChar = inputFill.charAt(0);

            String[] arr = generateArray(n, fillChar);
            printAndSave(arr, "output.txt");

        } catch (NumberFormatException e) {
            System.out.println("Помилка: розмір матриці має бути числом!");
        }
    }
}
