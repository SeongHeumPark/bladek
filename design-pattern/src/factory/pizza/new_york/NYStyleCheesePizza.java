package factory.pizza.new_york;

import factory.pizza.Pizza;

/**
 * @author seongheum.park
 */
public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
//        dough = "Thin Crust Dough";
//        sauce = "Marinara Sauce";
//        toppings.add("Grated Reggiano Cheese");
    }

    @Override
    public void prepare() {

    }
}
