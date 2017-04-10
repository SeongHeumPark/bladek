package factory.pizza_store;

import factory.constant.Type;
import factory.pizza.Pizza;
import factory.pizza.chicago.ChicagoStyleCheesePizza;
import factory.pizza.chicago.ChicagoStyleClamPizza;
import factory.pizza.chicago.ChicagoStylePepperoniPizza;
import factory.pizza.chicago.ChicagoStyleVeggiePizza;

/**
 * @author seongheum.park
 */
public class ChicagoPizzaStore extends PizzaStore {
    public ChicagoPizzaStore() {
        super(new ChicagoPizzaIngredientFactory());
    }

    @Override
    public Pizza createPizza(String type) {
//        return super.createPizza(type);

        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new ChicagoStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new ChicagoStylePepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ChicagoStyleClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new ChicagoStyleVeggiePizza();
        }

        return pizza;
    }

    @Override
    public Pizza createPizza(Type type) {
//        return super.createPizza(type);

        Pizza pizza = null;

        if (type.isCheese()) {
            pizza = new ChicagoStyleCheesePizza();
        } else if (type.isPepperoni()) {
            pizza = new ChicagoStylePepperoniPizza();
        } else if (type.isClam()) {
            pizza = new ChicagoStyleClamPizza();
        } else if (type.isVeggie()) {
            pizza = new ChicagoStyleVeggiePizza();
        }

        return pizza;
    }
}
