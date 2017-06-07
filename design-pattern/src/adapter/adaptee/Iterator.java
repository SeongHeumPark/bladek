package adapter.adaptee;

/**
 * @author seongheum.park
 */

public interface Iterator {
    boolean hasNext();
    boolean next();
    void remove();
}
