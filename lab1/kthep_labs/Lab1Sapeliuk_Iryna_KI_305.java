package kthep_labs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Варіант №2: квадрат поділений діагоналлю.
 * Лівий нижній трикутник заштрихований символом-заповнювачем,
 * у нижньому лівому кутку знаходиться порожній квадрат.
 *
 * Програма:
 *  - зчитує розмір квадратної матриці та символ-заповнювач з клавіатури;
 *  - генерує зубчастий масив відповідно до умови;
 *  - виводить результат у консоль та у текстовий файл.
 *
 * @author Ірина
 * @version 2.0
 */
public class Lab1Sapeliuk_Iryna_KI_305 {

    /**
     * Генерує зубчастий масив для варіанту №2.
     *
     * @param n        розмір квадратної матриці
     * @param fillChar символ-заповнювач (наприклад '@')
     * @return зубчастий масив символів
     */
    public static char[][] generateJaggedArray(int n, char fillChar) {
        char[][] result = new char[n][]; // створення зубчастого масиву
        int squareSize = n / 2;          // розмір "порожнього квадрата"
        char emptyChar = ' ';            // пробіл

        for (int i = 0; i < n; i++) {
            result[i] = new char[n]; // кожен рядок може бути окремим масивом
            for (int j = 0; j < n; j++) {
                if (j <= i) { // ліва нижня половина
                    // перевірка: чи всередині "порожнього квадрата"
                    if (i >= n - squareSize && j < squareSize) {
                        result[i][j] = emptyChar;
                    } else {
                        result[i][j] = fillChar;
                    }
                } else { // права верхня половина
                    result[i][j] = emptyChar;
                }
            }
        }
        return result;
    }

    /**
     * Вивід у консоль і запис у файл.
     */
    public static void printAndSave(char[][] arr, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (char[] row : arr) {
                String line = new String(row);
                System.out.println(line);
                writer.write(line + "\n");
            }
            System.out.println("\nРезультат записано у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    /**
     * Головний метод програми.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Введіть розмір квадратної матриці n: ");
            int n = Integer.parseInt(sc.nextLine());

            if (n <= 0) {
                System.out.println("Помилка: розмір має бути додатним числом!");
                return;
            }

            System.out.print("Введіть символ-заповнювач (наприклад @): ");
            String inputFill = sc.nextLine();

            if (inputFill.length() != 1) {
                System.out.println("Помилка: потрібно ввести рівно один символ!");
                return;
            }

            char fillChar = inputFill.charAt(0);

            char[][] arr = generateJaggedArray(n, fillChar);
            printAndSave(arr, "output.txt");

        } catch (NumberFormatException e) {
            System.out.println("Помилка: розмір матриці має бути числом!");
        } finally {
            sc.close();
        }
    }
}
