package template_method;

import template_method.general.Tea;
import template_method.general.Coffee;

/**
 * @author seongheum.park
 */
public class BeverageTestDrive {
    public static void main(String[] args) {
        Tea tea = new Tea();
        tea.prepareRecipe();

        Coffee coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
