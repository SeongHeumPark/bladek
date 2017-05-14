package facade;

/**
 * @author seongheum.park
 */

public class HomeTheaterTestDrive {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater =
                new HomeTheaterFacade(amp, tunner, dvd, cd, projector, screen, lights, popper);
        homeTheater.watchMovie("Raiders of the Lost Ark");
        homeTheater.endMovie();
    }
}
