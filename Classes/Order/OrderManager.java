package Classes.Order;

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

import Classes.Printer.PrintOrderSummary;
import Classes.Printer.PrintReceipt;
import Classes.Reservation.Reservation;
import Classes.Reservation.ReservationManager;
import Classes.AMenuItem.AMenuItem;
import Classes.AMenuItem.MenuManager;
import Classes.Staff.Staff;
import Classes.Staff.StaffManager;
import Classes.Table.TableManager;
import Classes.Time.DateTimeFormatHelper;

/**
 * The OrderManager Class
 * Manages various functionalities pertaining to Order objects.
 * @author  Her Huey
 * @author  Min
 * @version 2.0
 * @since   2021-11-12
 */
public class OrderManager {
    /**
     * Scanner object for taking in user input
     */
    private static Scanner input = new Scanner(System.in);
    /**
    * ArrayList OrderHistor to store all Order Objects
    */
    private static ArrayList<Order> orderHistory = new ArrayList<Order>();

    /**
     * Accessor for the order history (list of all orders made).
     * @return the order history
     */
    public static ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    /**
     * Assigns customer to a table and creates a new order for them.
     */
    public static void create() {
        try {
            System.out.print("Enter customer's reservation ID (enter -1 if this is a walk-in): ");
            int resvID = input.nextInt(); input.nextLine();
            int tableId, numPax;

        // assign customer to a table
        if (resvID == -1) {
            // walk in
            numPax = 0;
            while (numPax <= 0 || numPax > 10) {
				System.out.print("Enter number of pax: ");
				numPax = input.nextInt(); input.nextLine();
				if (numPax <= 0) {
					System.out.println("You have cannot have less than 1 person.");
				} else if (numPax > 10) {
					System.out.println("Sorry! The restaurant's maximum seating is 10 people.");
				}
                else break;
			}
			tableId = TableManager.findTableForWalkIn(numPax);
            if (tableId == -1) {
				System.out.println(
					"There are no available tables that can cater the number of pax for now. We're sorry!");
                return;
			}
        }
        else { // reservation
            Reservation r = ReservationManager.getReservationByReservationId(resvID);
            if(r == null){
                System.out.println("Invalid reservation ID!"); return;
            }
            else if(!r.getResvDate().equals(DateTimeFormatHelper.inbuiltDate())||
                    !r.getResvSession().equals(DateTimeFormatHelper.inbuiltSession())){
                        System.out.println("Not reserved time yet! Consider walk-in instead."); return;
                    }
            else tableId = r.getTableId();
            ReservationManager.removeReservationByReservationId(resvID);
        }
            TableManager.getTableById(tableId).setOccupied();
            System.out.print("Is the customer a member? (Y/N): ");
            char member = input.next().charAt(0);
            boolean isMember = false;
            while (member != 'Y' && member != 'y' && member != 'N' && member != 'n'){
                input.nextLine();
                System.out.println("Please enter Y or N to continue.");
                member = input.next().charAt(0);
            }
            if (member == 'Y' || member == 'y') isMember = true;
            Staff staff = StaffManager.getStaff();
            Order newOrder = new Order(tableId, staff, isMember);
            orderHistory.add(newOrder);
            System.out.printf("You have created a new order with ID %d and table ID %d\n", newOrder.getId(), tableId);
        }catch(InputMismatchException e){
            System.out.println("Please enter a valid entry.");
            input.nextLine();
        }
    }

    /**
     * Views an Order based on Order ID by printing its Order Summary.
     */
    public static void view() {
        try{
            System.out.print("Please enter the Order ID to view: ");
            int orderId = input.nextInt(); input.nextLine();
            for (Order viewOrder : orderHistory){
                if (viewOrder.getId() == orderId){
                    PrintOrderSummary.print(viewOrder);
                    return;
                }
            }
            System.out.println("Invalid Order ID.");
        }catch (InputMismatchException e){
            System.out.println("Please enter a valid entry.");
            input.nextLine();
        }
    }

    /**
     * Adds a AMenuItem with specified quantity to an existing Order.
     */
    public static void add() {
        try{

            System.out.print("Please enter the Order ID to add items to: ");
            int orderId = input.nextInt(); input.nextLine();
            for (Order userOrder : orderHistory){
                if (userOrder.getId() == orderId){
                    System.out.printf("Current summary for Order %-9d is shown below.%n", userOrder.getId());
                    PrintOrderSummary.print(userOrder);
                    
                    // Add Item
                    AMenuItem newItem = MenuManager.getMenuItem();
                    System.out.println("Enter the quantity: ");
                    int itemQty = input.nextInt(); input.nextLine();
                    userOrder.addItem(newItem, itemQty);
                    return;
                }
                System.out.println("Invalid Order ID.");
        }
        }catch (InputMismatchException e){
            System.out.println("Please enter a valid entry.");
            input.nextLine();
        }
    }

     /**
     * Removes a MenuItem with specified quantity from an existing Order.
     */
    public static void remove() {
        try{
            System.out.print("Please enter the Order ID to remove items from: ");
            int orderId = input.nextInt(); input.nextLine();
            for (Order userOrder : orderHistory){
                if (userOrder.getId() == orderId){
                    System.out.printf("Shown below is the current summary for Order %-9d %n", userOrder.getId());
                    PrintOrderSummary.print(userOrder);
    
                    // Remove Item
                    System.out.println("Choose item to remove from this order:");
                    Set<AMenuItem> itemsSet = userOrder.getItemList().keySet();
                    List<AMenuItem> items = new ArrayList<AMenuItem>(itemsSet);
                    for (int i = 0; i < items.size(); i++) {
                        System.out.printf("%-2d - %-50s x%-2d\n", i+1, items.get(i).getName(), userOrder.getItemList().get(items.get(i)));
                    }
                    System.out.print("Item number to remove: ");
                    int itemIndex = input.nextInt(); input.nextLine();
                    System.out.print("Enter the quantity: ");
                    int itemQty = input.nextInt(); input.nextLine();
                    userOrder.removeItem(items.get(itemIndex-1), itemQty);
    
                    return;
                }
            }
    
            System.out.println("Invalid Order ID.");
        }catch (InputMismatchException e){
            System.out.println("Please enter a valid entry.");
            input.nextLine();
        }
    }

    /**
     * Checks out an Order and prints its Receipt.
     * Customer to make payment and leave.
     */
    public static void checkout() {
        try{
            System.out.print("Please enter the Order to checkout: ");
            int orderId = input.nextInt(); input.nextLine();
            for (Order userOrder : orderHistory){
                if (userOrder.getId() == orderId){
                    PrintReceipt.print(userOrder);
                    int tableId = userOrder.getTableId();
                    TableManager.getTableById(tableId).setEmpty();
                    System.out.println("Order successfully checked out.");
                    return;
                }
            }
            System.out.println("Invalid Order ID.");
        }catch (InputMismatchException e){
            System.out.println("Please enter a valid entry.");
            input.nextLine();
        }
    }
}
