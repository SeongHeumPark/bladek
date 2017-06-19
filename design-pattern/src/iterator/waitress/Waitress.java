package iterator.waitress;

import iterator.menu_item.MenuItem;
import iterator.menu.DinerMenu;
import iterator.menu.PancakeHouseMenu;

import java.util.List;

/**
 * @author seongheum.park
 */
public class Waitress {
    private PancakeHouseMenu pancakeHouseMenu;
    private DinerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        List<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItems();
        for (MenuItem menuItem : breakfastItems) {
            System.out.println(menuItem.getName() + " ");
            System.out.println(menuItem.getPrice() + " ");
            System.out.println(menuItem.getDescription());
        }

        MenuItem[] lunchItems = dinerMenu.getMenuItems();
        for (int i = 0; i < lunchItems.length; i++) {
            MenuItem menuItem = lunchItems[i];
            System.out.println(menuItem.getName() + " ");
            System.out.println(menuItem.getPrice() + " ");
            System.out.println(menuItem.getDescription());
        }
    }
}
