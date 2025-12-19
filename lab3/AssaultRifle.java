package ki305.sapeliuk.lab3;

import ki305.sapeliuk.lab2.*;

/**
 * Інтерфейс Fireable описує базову можливість стрільби.
 */
interface Fireable {
    void fire(double shots); // метод для стрільби
}

/**
 * Інтерфейс Reloadable розширює Fireable
 * і додає методи, пов'язані з перезарядкою.
 */
interface Reloadable extends Fireable {
    void reload(); // метод для перезарядки
    int MAX_AMMO = 30; // максимальна кількість набоїв
}

/**
 * Клас AssaultRifle — підклас абстрактного класу Avtomat,
 * реалізує інтерфейс Reloadable.
 */
public class AssaultRifle extends Avtomat implements Reloadable {
    private int ammo;            // поточна кількість набоїв
    private double shotsFired;   // кількість зроблених пострілів
    private Logger logger;       // логер для запису дій

    /**
     * Конструктор штурмової гвинтівки.
     *
     * @param model назва моделі
     * @param barrel ствол
     * @param magazine магазин
     * @param sight приціл
     * @param logger логер для запису дій
     */
    public AssaultRifle(String model, Barrel barrel, Magazine magazine, Sight sight, Logger logger) {
        super(model, barrel, magazine, sight, logger);
        this.logger = logger; // зберігаємо посилання на логер
        this.ammo = magazine.getRounds();
        this.shotsFired = 0;
        if (logger != null)
            logger.log("Assault rifle " + model + " created with ammo=" + ammo);
    }

    /**
     * Реалізація абстрактного методу з класу Avtomat.
     * @return тип зброї
     */
    @Override
    public String getWeaponType() {
        return "Assault Rifle";
    }

    /**
     * Реалізація методу fire() з інтерфейсу Fireable.
     * @param shots кількість пострілів
     */
    @Override
    public void fire(double shots) {
        if (isSafetyOn()) {
            logger.log("Cannot fire: safety is ON."); 
            return;
        }
        if (ammo <= 0) {
            logger.log("Cannot fire: no ammo."); 
            return;
        }

        int fired = (int) Math.min(shots, ammo);
        ammo -= fired;
        shotsFired += fired;
        logger.log("Fired " + fired + " shots. Remaining ammo: " + ammo); 
    }

    /**
     * Реалізація методу reload() з інтерфейсу Reloadable.
     */
    @Override
    public void reload() {
        ammo = MAX_AMMO;
        logger.log("Weapon reloaded. Ammo now: " + ammo); 
    }

    /**
     * Повертає статус зброї.
     */
    @Override
    public String getStatus() {
        return String.format("%s | type=%s | ammo=%d | fired=%.0f",
                super.getStatus(), getWeaponType(), ammo, shotsFired);
    }
}
