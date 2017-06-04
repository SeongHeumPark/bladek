package template_method;

import template_method.hook.CoffeeWithHook;
import template_method.hook.TeaWithHook;

/**
 * @author seongheum.park
 */
public class BeverageTestDrive {
    public static void main(String[] args) {
        TeaWithHook tea = new TeaWithHook();
        tea.prepareRecipe();
        System.out.println();
        
        CoffeeWithHook coffee = new CoffeeWithHook();
        coffee.prepareRecipe();
    }
}
