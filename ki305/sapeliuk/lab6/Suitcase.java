package ki305.sapeliuk.lab6;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметризований клас-контейнер "Валіза".
 * @param <T> тип елементів, що зберігаються (має реалізувати Item)
 */
public class Suitcase<T extends Item> {

    private List<T> items = new ArrayList<>();

    /**
     * Додає елемент до валізи.
     * @param item предмет
     */
    public void addItem(T item) {
        items.add(item);
    }

    /**
     * Виймає елемент з валізи.
     * @param index індекс елемента
     * @return видалений предмет
     */
    public T removeItem(int index) {
        return items.remove(index);
    }

    /**
     * Повертає елемент за індексом.
     */
    public T getItem(int index) {
        return items.get(index);
    }

    /**
     * Повертає кількість предметів у валізі.
     */
    public int size() {
        return items.size();
    }

    /**
     * Пошук мінімального елемента (за вагою).
     * @return найменший елемент
     */
    public T findMin() {
        if (items.isEmpty()) return null;

        T min = items.get(0);
        for (T item : items) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }
}
