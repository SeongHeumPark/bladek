package factory.pizza_store;

import factory.constant.Type;
import factory.ingredient.PizzaIngredientFactory;
import factory.pizza.*;

/**
 * @author seongheum.park
 */
public abstract class PizzaStore {
    PizzaIngredientFactory ingredientFactory;

    public PizzaStore(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

//    private SimplePizzaFactory factory;

//    public PizzaStore(SimplePizzaFactory factory) {
//        this.factory = factory;
//    }

    public Pizza orderPizza(String type) {
//        Pizza pizza = factory.createPizza(type);
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    public Pizza orderPizza(Type type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza(ingredientFactory);
        } else if (type.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
        }

        return pizza;
    }

    public Pizza createPizza(Type type) {
        Pizza pizza = null;

        if (type.isCheese()) {
            pizza = new CheesePizza(ingredientFactory);
        } else if (type.isPepperoni()) {
            pizza = new PepperoniPizza(ingredientFactory);
        } else if (type.isClam()) {
            pizza = new ClamPizza(ingredientFactory);
        } else if (type.isVeggie()) {
            pizza = new VeggiePizza(ingredientFactory);
        }

        return pizza;
    }
}
