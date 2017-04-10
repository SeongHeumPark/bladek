package factory.pizza_store;

import factory.constant.Type;
import factory.ingredient.NYPizzaIngredientFactory;
import factory.pizza.Pizza;
import factory.pizza.new_york.NYStyleCheesePizza;
import factory.pizza.new_york.NYStyleClamPizza;
import factory.pizza.new_york.NYStylePepperoniPizza;
import factory.pizza.new_york.NYStyleVeggiePizza;

/**
 * @author seongheum.park
*/
public class NYPizzaStore extends PizzaStore {
    public NYPizzaStore() {
        super(new NYPizzaIngredientFactory());
    }

    @Override
    public Pizza createPizza(String type) {
//        return super.createPizza(type);

        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new NYStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new NYStylePepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new NYStyleClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new NYStyleVeggiePizza();
        }

        return pizza;
    }

    @Override
    public Pizza createPizza(Type type) {
//        return super.createPizza(type);

        Pizza pizza = null;

        if (type.isCheese()) {
            pizza = new NYStyleCheesePizza();
        } else if (type.isPepperoni()) {
            pizza = new NYStylePepperoniPizza();
        } else if (type.isClam()) {
            pizza = new NYStyleClamPizza();
        } else if (type.isVeggie()) {
            pizza = new NYStyleVeggiePizza();
        }

        return pizza;
    }
}
