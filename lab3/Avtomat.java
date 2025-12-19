package ki305.sapeliuk.lab2;

/**
 * Абстрактний клас Avtomat — модель автоматичної вогнепальної зброї (спрощено).
 *
 * Містить:
 * - мінімум 3 поля-об'єкти (Magazine, Barrel, Sight)
 * - кілька конструкторів із ланцюгом викликів
 * - щонайменше 10 методів
 * - логування дій через Logger
 */
public abstract class Avtomat { 
    public enum FireMode { SAFE, SEMI, BURST, AUTO }

    private String model;
    private Barrel barrel;
    private Magazine magazine;
    private Sight sight;
    private FireMode mode;
    private boolean isJammed;
    private boolean safetyOn;
    private final Logger logger;

    /**
     * Повний конструктор 
     */
    public Avtomat(String model, Barrel barrel, Magazine magazine, Sight sight, Logger logger) {
        this.model = model;
        this.barrel = barrel;
        this.magazine = magazine;
        this.sight = sight;
        this.mode = FireMode.SAFE;
        this.isJammed = false;
        this.safetyOn = true;
        this.logger = logger;

        if (this.logger != null) {
            this.logger.log(String.format(
                "Created Avtomat: model=%s | %s | %s | sight=%s | mode=%s | safety=%s | jammed=%s",
                this.model,
                this.barrel,
                this.magazine,
                this.sight == null ? "none" : this.sight,
                this.mode,
                this.safetyOn,
                this.isJammed
            ));
        }
    }

    // ---------- Абстрактний метод ----------
    /**
     * Кожен підклас має реалізувати метод, який повертає тип зброї.
     * Наприклад: "Assault Rifle", "Sniper Rifle" тощо.
     */
    public abstract String getWeaponType(); // 🔹 ДОДАНО

    // ---------- Методи ----------

    public synchronized void fireOne() {
        logger.log(String.format(
            "Attempt to fire one. Safety: %s, Jammed: %s, Mode: %s",
            safetyOn, isJammed, mode
        ));

        if (safetyOn) {
            logger.log("Cannot fire: safety is on.");
            return;
        }
        if (isJammed) {
            logger.log("Cannot fire: weapon jammed.");
            return;
        }
        if (magazine.removeOne()) {
            logger.log(String.format("Fired one round. Remaining: %d", magazine.getRounds()));
            if (Math.random() < 0.01) {
                isJammed = true;
                logger.log("Weapon jammed after firing.");
            }
        } else {
            logger.log("Click — no ammo.");
        }
    }

    public synchronized void fireBurst(int n) {
        logger.log(String.format("Attempt to fire burst of %d shots. Mode: %s", n, mode));

        if (safetyOn) {
            logger.log("Cannot fire burst: safety is on.");
            return;
        }
        if (isJammed) {
            logger.log("Cannot fire burst: weapon jammed.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!magazine.removeOne()) {
                logger.log("Burst stopped — no more ammo.");
                break;
            }
            logger.log(String.format("Burst shot #%d. Remaining: %d", i + 1, magazine.getRounds()));
            if (Math.random() < 0.01) {
                isJammed = true;
                logger.log(String.format("Weapon jammed during burst at shot #%d", i + 1));
                break;
            }
        }
    }

    public synchronized void reload(Magazine newMag) {
        if (newMag == null) {
            logger.log("Reload failed: new magazine is null.");
            return;
        }
        logger.log(String.format("Reloading. Old: %s, New: %s", magazine, newMag));
        magazine = newMag;
        isJammed = false;
        logger.log(String.format("Reloaded. Current: %s", magazine));
    }

    public synchronized void unload() {
        logger.log(String.format("Unloading magazine. Before: %s", magazine));
        magazine.unloadAll();
        logger.log(String.format("Magazine now: %s", magazine));
    }

    public synchronized void toggleSafety() {
        safetyOn = !safetyOn;
        logger.log(String.format("Safety toggled. Now safetyOn=%s", safetyOn));
    }

    public synchronized void setMode(FireMode newMode) {
        mode = newMode;
        logger.log(String.format("Fire mode changed to %s", newMode));
    }

    public synchronized void attachSight(Sight newSight) {
        sight = newSight;
        logger.log(String.format("Attached sight: %s", newSight));
    }

    public synchronized void detachSight() {
        logger.log(String.format("Detached sight: %s", sight));
        sight = null;
    }

    public synchronized void changeBarrel(Barrel newBarrel) {
        logger.log(String.format("Changing barrel. Old: %s, New: %s", barrel, newBarrel));
        barrel = newBarrel;
    }

    public synchronized String getStatus() {
        return String.format(
            "%s | %s | %s | sight=%s | mode=%s | safety=%s | jammed=%s",
            model,
            barrel,
            magazine,
            sight == null ? "none" : sight,
            mode,
            safetyOn,
            isJammed
        );
    }

    public synchronized int getAmmoCount() {
        return magazine.getRounds();
    }

    public synchronized void jam() {
        isJammed = true;
        logger.log("Manual jam invoked.");
    }

    public synchronized void clearJam() {
        if (isJammed) {
            isJammed = false;
            logger.log("Jam cleared.");
        } else {
            logger.log("Clear jam called but weapon not jammed.");
        }
    }

    public synchronized double aim() {
        double base = 50.0;
        if (sight != null) {
            base += (sight.getMagnification() - 1.0) * 10.0;
        }
        base += Math.max(0, (barrel.getLengthCm() - 30.0) * 0.5);
        double accuracy = Math.min(99.9, base);
        logger.log(String.format("Aiming. Estimated accuracy: %.1f%%", accuracy));
        return accuracy;
    }

    public synchronized void triggerPull() {
        logger.log(String.format("Trigger pulled. Mode: %s", mode));
        switch (mode) {
            case SAFE -> logger.log("Trigger pull: safe, no fire.");
            case SEMI -> fireOne();
            case BURST -> fireBurst(3);
            case AUTO -> fireBurst(10);
        }
    }

    // ---------- Гетери ----------
    public Logger getLogger() { return logger; }
    public boolean isSafetyOn() { return safetyOn; }
}

