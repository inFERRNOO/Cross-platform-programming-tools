package ki305.sapeliuk.lab6;

/**
 * Клас одягу, що може бути розміщений у валізі.
 */
public class Clothes implements Item {
    private String name;
    private double weight;

    public Clothes(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Clothes: " + name + " (" + weight + " кг)";
    }
}
