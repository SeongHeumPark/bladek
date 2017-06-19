package iterator;

import iterator.menu.DinerMenu;
import iterator.menu.PancakeHouseMenu;
import iterator.waitress.Waitress;

/**
 * @author seongheum.park
 */
public class MenuTestDrive {
    public static void main(String[] args) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();

        Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
        waitress.printMenu();
    }
}
