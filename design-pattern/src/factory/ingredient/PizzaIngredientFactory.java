package factory.ingredient;

import factory.ingredient.cheese.Cheese;
import factory.ingredient.clam.Clam;
import factory.ingredient.dough.Dough;
import factory.ingredient.pepperoni.Pepperoni;
import factory.ingredient.sauce.Sauce;
import factory.ingredient.veggie.Veggie;

import java.util.List;

/**
 * @author seongheum.park
 */
public interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public List<Veggie> createVeggies();
    public Pepperoni createPepperoni();
    public Clam createClam();
}
