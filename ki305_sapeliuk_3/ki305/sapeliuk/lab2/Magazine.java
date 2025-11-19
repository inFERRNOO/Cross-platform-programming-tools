package ki305.sapeliuk.lab2;

/**
 * Клас Magazine представляє магазин автомата.
 */
public class Magazine {
    private int capacity; //Максимальна ємність магазину
    private int rounds; // поточна кількість набоїв

    public Magazine(int capacity) { //Конструктор порожнього магазину
        this.capacity = capacity;
        this.rounds = 0;
    }

    public Magazine(int capacity, int rounds) { //Конструктор з початковим заряджанням
        this.capacity = capacity;
        this.rounds = Math.min(rounds, capacity); // Запобігає перевищенню ємності
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRounds() {
        return rounds;
    }

    public void load(int count) {
        if (count < 0) return;
        rounds = Math.min(capacity, rounds + count);
    }

    public boolean removeOne() { //Видалення одного набою
        if (rounds > 0) {
            rounds--;
            return true;
        }
        return false;
    }

    public void unloadAll() { // Повне розряджання
        rounds = 0;
    }

    @Override
    public String toString() {
        return String.format("Magazine[%d/%d]", rounds, capacity);
    }
}