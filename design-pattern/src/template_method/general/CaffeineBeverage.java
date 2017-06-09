package template_method.general;

/**
 * @author seongheum.park
 */
public abstract class CaffeineBeverage {
    public final void prepareRecipe() {
        boilWater();
        brew();
        addCondiments();
        pourInCup();
    }

    private void boilWater() {
        System.out.println("물 끓이는 중");
    }

    protected abstract void brew();

    protected abstract void addCondiments();

    private void pourInCup() {
        System.out.println("컵에 따르는 중");
    }
}
