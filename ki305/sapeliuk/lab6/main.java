package ki305.sapeliuk.lab6;

/**
 * Демонстраційна програма для роботи з параметризованим класом Suitcase.
 */
public class Main {
    public static void main(String[] args) {

        Suitcase<Item> suitcase = new Suitcase<>();

        suitcase.addItem(new Clothes("Футболка", 0.3));
        suitcase.addItem(new Clothes("Куртка", 1.2));
        suitcase.addItem(new Gadget("Телефон", 0.18));
        suitcase.addItem(new Gadget("Ноутбук", 1.8));

        System.out.println("Валіза містить " + suitcase.size() + " предмети.");

        System.out.println("Найменший предмет:");
        System.out.println(suitcase.findMin());

        System.out.println("\nВидаляємо елемент 1:");
        suitcase.removeItem(1);

        System.out.println("Новий мінімальний предмет:");
        System.out.println(suitcase.findMin());
    }
}
