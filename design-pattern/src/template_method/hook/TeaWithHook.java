package template_method.hook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author seongheum.park
 */
public class TeaWithHook extends CaffeineBeverageWithHook {
    @Override
    protected void brew() {
        System.out.println("차를 우려내는 중");
    }

    @Override
    protected void addCondiments() {
        System.out.println("레몬을 추가하는 중");
    }

    @Override
    protected boolean customerWantsCondiments() {
        String answer = getUserInput();

        if (answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }

    private String getUserInput() {
        String answer = null;

        System.out.print("차에 레몬을 넣어드릴까요? (y/n) ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException ioe) {
            System.err.println("IO 오류");
        }

        if (answer == null) {
            return "no";
        }

        return answer;
    }
}
