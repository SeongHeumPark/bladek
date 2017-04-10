package factory.pizza;

import factory.ingredient.cheese.Cheese;
import factory.ingredient.clam.Clam;
import factory.ingredient.dough.Dough;
import factory.ingredient.pepperoni.Pepperoni;
import factory.ingredient.sauce.Sauce;
import factory.ingredient.veggie.Veggie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seongheum.park
 */
public abstract class Pizza {
    protected String name;
    protected Dough dough;
    protected Sauce sauce;
    protected List<Veggie> veggies;
    protected Cheese cheese;
    protected Pepperoni pepperoni;
    protected Clam clam;

//    protected List<String> toppings = new ArrayList<>();

//    public void prepare() {
//        System.out.println("Preparing " + name);
//        System.out.println("Tossing dough...");
//        System.out.println("Adding sauce...");
//        System.out.println("Adding toppings: ");
//
//        for (String topping : toppings) {
//            System.out.println("   " + topping);
//        }
//    }

    public abstract void prepare();

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
