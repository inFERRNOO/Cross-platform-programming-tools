package ki305.sapeliuk.lab6;

/**
 * Клас гаджета, який може бути поміщений у валізу.
 */
public class Gadget implements Item {
    private String name;
    private double weight;

    public Gadget(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Gadget: " + name + " (" + weight + " кг)";
    }
}
