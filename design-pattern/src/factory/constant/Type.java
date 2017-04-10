package factory.constant;

/**
 * @author seongheum.park
 */
public enum Type {
    CHEESE,
    PEPPERONI,
    CLAM,
    VEGGIE;

    public boolean isCheese() {
        return this == CHEESE;
    }

    public boolean isPepperoni() {
        return this == PEPPERONI;
    }

    public boolean isClam() {
        return this == CLAM;
    }

    public boolean isVeggie() {
        return this == VEGGIE;
    }
}
