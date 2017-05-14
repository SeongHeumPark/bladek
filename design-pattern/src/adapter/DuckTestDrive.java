package adapter;

import adapter.adaptee.Duck;
import adapter.adaptee.MallardDuck;
import adapter.adaptee.WildTurkey;
import adapter.adapter.TurkeyAdapter;

/**
 * @author seongheum.park
 */

public class DuckTestDrive {
    public static void main(String[] args) {
        WildTurkey turkey = new WildTurkey();
        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();

        MallardDuck duck = new MallardDuck();
        System.out.println("\nThe Duck says...");
        testDuck(duck);

        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        System.out.println("\nThe TurkeyAdapter says...");
        testDuck(turkeyAdapter);
    }

    private static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
