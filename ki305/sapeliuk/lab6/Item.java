package ki305.sapeliuk.lab6;

/**
 * Базовий інтерфейс для всіх предметів,
 * які можуть зберігатися у валізі.
 */
public interface Item extends Comparable<Item> {

    /**
     * @return вага предмета
     */
    double getWeight();

    @Override
    default int compareTo(Item other) {
        return Double.compare(this.getWeight(), other.getWeight());
    }
}
