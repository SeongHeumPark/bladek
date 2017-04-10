package factory.pizza.chicago;

import factory.pizza.Pizza;

/**
 * @author seongheum.park
 */
public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
//        dough = "Extra Thick Crust Dough";
//        sauce = "Plum Tomato Sauce";
//        toppings.add("Shredded Mozzarella Cheese");
    }

    @Override
    public void prepare() {
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
