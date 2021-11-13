package Classes.Printer;

import java.util.Scanner;

/**
 * Superclass with an initialized Scanner object.
 */
public abstract class UserInterfacePrinter implements Printer {

    protected static int rowLength = 63;
    protected static String restaurantName = "Happy Chicken Diner";
    protected static Scanner input = new Scanner(System.in);

}
