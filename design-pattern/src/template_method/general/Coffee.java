package template_method.general;

/**
 * @author seongheum.park
 */
public class Coffee extends CaffeineBeverage {
    @Override
    protected void brew() {
        System.out.println("필터를 통해서 커피를 우려내는 중");
    }

    @Override
    protected void addCondiments() {
        System.out.println("설탕과 우유를 추가하는 중");
    }
}
