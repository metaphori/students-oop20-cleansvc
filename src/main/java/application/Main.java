package application;
import view.HomeView;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main {

    public static void main(final String[] args) {
        new HomeView().display();
        //new SaveAndLoadImpl().saveStaff();
        //new AppointmentsView().display();
    }

}
