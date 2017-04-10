package factory.ingredient;

import factory.ingredient.clam.Clam;
import factory.ingredient.clam.FreshClam;
import factory.ingredient.dough.Dough;
import factory.ingredient.sauce.MarinaraSauce;
import factory.ingredient.pepperoni.Pepperoni;
import factory.ingredient.dough.ThinCrustDough;
import factory.ingredient.pepperoni.SlicedPepperoni;
import factory.ingredient.sauce.Sauce;
import factory.ingredient.veggie.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seongheum.park
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public List<Veggie> createVeggies() {
        List<Veggie> veggies = new ArrayList<>();
        veggies.add(new Garlic());
        veggies.add(new Onion());
        veggies.add(new Mushroom());
        veggies.add(new RedPepper());

        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clam createClam() {
        return new FreshClam();
    }
}
