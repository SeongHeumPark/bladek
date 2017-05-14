package adapter.adapter;

import adapter.adaptee.Duck;
import adapter.adaptee.Turkey;

/**
 * @author seongheum.park
 */

public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        turkey.fly();
    }
}
