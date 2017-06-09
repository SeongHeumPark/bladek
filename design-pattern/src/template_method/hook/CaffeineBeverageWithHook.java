package template_method.hook;

/**
 * @author seongheum.park
 */
public abstract class CaffeineBeverageWithHook {
    public final void prepareRecipe() {
        boildWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    protected final void boildWater() {
        System.out.println("물 끓이는 중");
    }

    protected abstract void brew();

    protected abstract void addCondiments();

    protected final void pourInCup() {
        System.out.println("컵에 따르는 중");
    }

    protected boolean customerWantsCondiments() {
        return true;
    }
}
