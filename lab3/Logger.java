   package ki305.sapeliuk.lab2;

import java.io.BufferedWriter; //буферизований запис у файл
import java.io.FileWriter; //запис у файл
import java.io.IOException; //обробка помилок вводу/виводу
import java.time.LocalDateTime; //робота з датою
import java.time.format.DateTimeFormatter; //робота з часом

/**
 * Простий логер дій класу Автомат. Підтримує коректне закриття ресурсу.
 */
public class Logger implements AutoCloseable {
    private final BufferedWriter writer;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Відкриває/створює лог файл.
     * @param filePath шлях до файлу логів
     * @throws IOException якщо не вдається відкрити файл
     */
    public Logger(String filePath) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filePath, true)); // append
    }

    /**
     * Записує рядок у лог з часовою міткою.
     * @param message повідомлення
     */
    public synchronized void log(String message) {
        try {
            String time = LocalDateTime.now().format(fmt);
            writer.write(String.format("[%s] %s", time, message));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.printf("Logger failed: %s%n", e.getMessage());
        }
    }

    /**
     * Закриває логер — обов'язково викликати при завершенні.
     */
    @Override
    public synchronized void close() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.printf("Failed to close logger: %s%n", e.getMessage());
        }
    }
}
