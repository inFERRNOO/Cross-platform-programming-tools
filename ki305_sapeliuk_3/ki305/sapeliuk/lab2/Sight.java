package ki305.sapeliuk.lab2;

/**
 * Клас Sight описує приціл (простий або оптичний).
 */
public class Sight {
    private String type; // "iron", "optical", "red-dot" і т.д.
    private double magnification; // для оптичних прицілів, 1.0 для простих

    public Sight(String type, double magnification) {
        this.type = type;
        this.magnification = magnification;
    }

    public String getType() {
        return type;
    }

    public double getMagnification() {
        return magnification;
    }

    @Override
    public String toString() {
        return String.format("Sight[%s, x%.1f]", type, magnification);
    }
}