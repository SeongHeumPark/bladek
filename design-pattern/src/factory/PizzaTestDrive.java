package factory;

import factory.constant.Type;
import factory.pizza.Pizza;
import factory.pizza_store.ChicagoPizzaStore;
import factory.pizza_store.NYPizzaStore;
import factory.pizza_store.PizzaStore;

/**
 * @author seongheum.park
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

//        Pizza pizza = nyStore.orderPizza("cheese");
        Pizza pizza = nyStore.orderPizza(Type.CHEESE);
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

//        pizza = chicagoStore.orderPizza("cheese");
        pizza = chicagoStore.orderPizza(Type.CHEESE);
        System.out.println("Joei ordered a " + pizza.getName() + "\n");
    }
}
