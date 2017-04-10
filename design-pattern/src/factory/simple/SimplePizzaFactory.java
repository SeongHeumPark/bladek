package factory.simple;

import factory.constant.Type;
import factory.pizza.*;

/**
 * @author seongheum.park
 */
public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        }

        return pizza;
    }

    public Pizza createPizza(Type type) {
        Pizza pizza = null;

        if (type.isCheese()) {
            pizza = new CheesePizza();
        } else if (type.isPepperoni()) {
            pizza = new PepperoniPizza();
        } else if (type.isClam()) {
            pizza = new ClamPizza();
        } else if (type.isVeggie()) {
            pizza = new VeggiePizza();
        }

        return pizza;
    }
}
