package ki305.sapeliuk.lab2;

/**
 * Клас Barrel описує ствол (довжина, калібр).
 */
public class Barrel {
    private double lengthCm;
    private String caliber;

    public Barrel(double lengthCm, String caliber) {
        this.lengthCm = lengthCm;
        this.caliber = caliber;
    }

    public double getLengthCm() {
        return lengthCm;
    }

    public void setLengthCm(double lengthCm) {
        this.lengthCm = lengthCm;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    @Override
    public String toString() {
        return "Barrel[" + caliber + ", " + lengthCm + " cm]";
    }
}
