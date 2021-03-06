package Classes.Printer;

import java.util.Map;

import Classes.AMenuItem.AMenuItem;
import Classes.Order.Order;

/**
 * The PrintReceipt class Implements printing functionality for order receipts
 * 
 * @author Min
 * @version 1.0
 * @since 2021-11-01
 */
public class PrintReceipt implements Printable {
    /**
     * Prints the receipt for a particular order
     * 
     * @param order The order for which to print the receipt
     */
    public static void print(Order order) {
        Map<AMenuItem, Integer> itemList = order.getItemList();
        double subTotal = order.getSubTotal();
        double grandTotal = order.getGrandTotal();
        // Line 1:
        System.out.printf("-".repeat(rowLength));
        System.out.println();
        // Line 2:
        String leftFormat = "%-" + ((rowLength / 2) - (restaurantName.length() / 2)) + "s";
        String rightFormat = "%" + ((rowLength / 2) - (restaurantName.length() / 2)) + "s";
        System.out.format(leftFormat, "|");
        System.out.print(restaurantName);
        System.out.format(rightFormat, "|");
        System.out.println();
        // Line 3:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Info Line:
        String orderIdString = "| Order ID: " + order.getId() + " |";
        String orderIdFormat = "%-" + orderIdString.length() + "s";
        String dateTimeString = "| Date: " + order.getDateTime() + " |";
        String dateTimeFormat = "%" + dateTimeString.length() + "s";
        int midLength = rowLength - orderIdString.length() - dateTimeString.length();
        String tableIdString = " Table ID: " + order.getTableId() + " ";
        String tableIdFormat = "%-" + ((midLength / 2) + (tableIdString.length() / 2)) + "s";
        System.out.format(orderIdFormat, orderIdString);
        System.out.print(" ".repeat((midLength / 2) - tableIdString.length() / 2));
        System.out.format(tableIdFormat, tableIdString);
        System.out.format(dateTimeFormat, dateTimeString);
        System.out.println();

        System.out.printf("-".repeat(rowLength)); // print a ----- row
        System.out.println();

        String leftString = "| Order created by: " + order.getCreator().getName();
        System.out.print(leftString);
        System.out.format("%"+(rowLength-leftString.length())+"s","|");
        System.out.println();
        System.out.printf("-".repeat(rowLength)); // print a ----- row
        System.out.println();

        // Line 4:
        System.out.print("| Item");
        String priceFormat = "%" + (rowLength - 6) + "s";
        String priceHeader = "|     Price     |";
        System.out.format(priceFormat, priceHeader);
        System.out.println();
        // Line 5:
        System.out.printf("-".repeat(rowLength));
        System.out.println();
        // Line 6:
        for (var entry : itemList.entrySet()) {
            AMenuItem item = entry.getKey();
            String itemName = item.getName();
            leftString = "| " + itemName + " x" + entry.getValue();
            System.out.format("%-" + (rowLength - priceHeader.length()) + "s", leftString);
            priceFormat = "%" + (rowLength - leftString.length()) + "s";
            String priceString = "S$" + String.format("%.2f", item.getPrice() * itemList.get(item));
            leftFormat = "%-" + ((priceHeader.length() - (priceString.length())) / 2) + "s";
            leftString = String.format(leftFormat, "|") + priceString;
            rightFormat = "%" + (priceHeader.length() - leftString.length()) + "s";
            System.out.format(leftFormat, "|");
            System.out.print(priceString);
            System.out.format(rightFormat, "|");
            System.out.println();
        }
        System.out.printf("-".repeat(rowLength));
        System.out.println();
        // Bottom segment:
        String subTotalPrice = "S$" + String.format("%.2f", subTotal);
        String totalPrice = "S$" + String.format("%.2f", grandTotal);
        String discountRate = Integer.toString((int) (100 * order.getDISCOUNT_RATE())) + "%";
        String taxRate = Integer.toString((int) (100 * order.getTAX_RATE())) + "%";

        String discountFormat = String.format("%" + totalPrice.length() + "s", discountRate);
        String subTotalFormat = String.format("%" + totalPrice.length() + "s", subTotalPrice);
        String totalFormat = String.format("%" + totalPrice.length() + "s", totalPrice);
        String taxFormat = String.format("%" + totalPrice.length() + "s", taxRate);

        String subTotalString = "subTotal: " + subTotalFormat + "    |";
        String discountString = "Discount: " + discountFormat + "    |";
        String taxString = "GST:      " + taxFormat + "    |";
        String totalString = "Total:    " + totalFormat + "    |";

        String resultFormatter = "%" + (rowLength - 1) + "s";

        System.out.print("|");
        System.out.format(resultFormatter, discountString);
        System.out.println();
        System.out.print("|");
        System.out.format(resultFormatter, subTotalString);
        System.out.println();
        System.out.print("|");
        System.out.format(resultFormatter, taxString);
        System.out.println();
        System.out.print("|");
        System.out.format(resultFormatter, totalString);
        System.out.println();

        System.out.printf("-".repeat(rowLength));
        System.out.println();
    }
}