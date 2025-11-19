package ki305.sapeliuk.lab4;

/**
 * Клас для обчислення математичного виразу y = tg(4x) / x.
 * <p>
 * Містить метод обчислення та обробку виключень при некоректному значенні x.
 * </p>
 * @author Iryna
 * @version 1.0
 */
public class ExpressionCalculator {

    /**
     * Обчислює значення виразу y = tg(4x) / x.
     *
     * @param x значення аргументу
     * @return значення функції y
     * @throws ArithmeticException якщо x = 0
     */
    public static double calculate(double x) throws ArithmeticException {
        if (x == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе");
        }
        return Math.tan(4 * x) / x;
    }
}
