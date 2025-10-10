package ki305.sapeliuk.lab2;

/**
 * Клас Avtomat — модель автоматичної вогнепальної зброї (спрощено).
 *
 * Містить:
 * - мінімум 3 поля-об'єкти (Magazine, Barrel, Sight)
 * - кілька конструкторів із ланцюгом викликів
 * - щонайменше 10 методів
 * - логування дій через Logger
 */
public class Avtomat {
    public enum FireMode { SAFE, SEMI, BURST, AUTO } //перелік можливих режимів стрільби

    private String model; //Поля класу
    private Barrel barrel; //об'єкт ствола
    private Magazine magazine; //об'єкт магазину
    private Sight sight; //об'єкт прицілу
    private FireMode mode; //поточний режим стрільби
    private boolean isJammed; //стан заклинення
    private boolean safetyOn;
    private final Logger logger; //логер для запису дій

    /**
     * Повний конструктор — базовий, до якого делегують інші.
     */
    public Avtomat(String model, Barrel barrel, Magazine magazine, Sight sight, Logger logger) {
        this.model = model; 
        this.barrel = barrel;
        this.magazine = magazine;
        this.sight = sight;
        this.mode = FireMode.SAFE; //зброя завжди створюється в безпечному режимі
        this.isJammed = false; // зброя не заклинена при створенні
        this.safetyOn = true; // запобіжник увімкнено при створенні
        this.logger = logger; //зберігає посилання на об'єкт для логування

        if (this.logger != null) {
            this.logger.log(String.format( //виклик методу логування, форматування рядка
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

    /**
     * Конструктор без прицілу (делегує повному).
     */
    public Avtomat(String model, Barrel barrel, Magazine magazine, Logger logger) {
        this(model, barrel, magazine, null, logger);
    }

    /**
     * Конструктор із типовими параметрами (делегує повному).
     */
    public Avtomat(Logger logger) {
        this("AK-Default",
             new Barrel(41.0, "7.62x39mm"),
             new Magazine(30, 30),
             new Sight("iron", 1.0),
             logger);
    }

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

    public synchronized void fireBurst(int n) { //Стрільба чергою
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

    public synchronized void reload(Magazine newMag) { //Перезарядка
        if (newMag == null) {
            logger.log("Reload failed: new magazine is null.");
            return;
        }
        logger.log(String.format("Reloading. Old: %s, New: %s", magazine, newMag));
        magazine = newMag;
        isJammed = false;
        logger.log(String.format("Reloaded. Current: %s", magazine));
    }

    public synchronized void unload() { //Розряджання
        logger.log(String.format("Unloading magazine. Before: %s", magazine));
        magazine.unloadAll();
        logger.log(String.format("Magazine now: %s", magazine));
    }

    public synchronized void toggleSafety() { //Перемикач запобіжника
        safetyOn = !safetyOn;
        logger.log(String.format("Safety toggled. Now safetyOn=%s", safetyOn));
    }

    public synchronized void setMode(FireMode newMode) { //Зміна режиму
        mode = newMode;
        logger.log(String.format("Fire mode changed to %s", newMode));
    }

    public synchronized void attachSight(Sight newSight) { //Кріплення прицілу
        sight = newSight;
        logger.log(String.format("Attached sight: %s", newSight));
    }

    public synchronized void detachSight() {
        logger.log(String.format("Detached sight: %s", sight));
        sight = null;
    }

    public synchronized void changeBarrel(Barrel newBarrel) { //Заміна ствола
        logger.log(String.format("Changing barrel. Old: %s, New: %s", barrel, newBarrel));
        barrel = newBarrel;
    }

    public synchronized String getStatus() { //Статус зброї
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

    public synchronized int getAmmoCount() { //Кількість набоїв
        return magazine.getRounds();
    }

    public synchronized void jam() { //Заклинення
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

    public synchronized double aim() { // Розрахунок точності
        double base = 50.0;
        if (sight != null) {
            base += (sight.getMagnification() - 1.0) * 10.0;
        }
        base += Math.max(0, (barrel.getLengthCm() - 30.0) * 0.5);
        double accuracy = Math.min(99.9, base);
        logger.log(String.format("Aiming. Estimated accuracy: %.1f%%", accuracy));
        return accuracy;
    }

    public synchronized void triggerPull() { //Спуск гачка
        logger.log(String.format("Trigger pulled. Mode: %s", mode));
        switch (mode) {
            case SAFE -> logger.log("Trigger pull: safe, no fire.");
            case SEMI -> fireOne();
            case BURST -> fireBurst(3);
            case AUTO -> fireBurst(10);
        }
    }
}
