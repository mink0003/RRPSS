package Classes.Printer;

import java.util.Map;

import Classes.AMenuItem.AMenuItem;
import Classes.SalesRevenueReport.SalesRevenueReport;
import Classes.Staff.StaffManager;

/**
 * The PrintReport class
 * Implements printing functionality for printing the Sales Revenue Report
 * @author  Min
 * @author  Her Huey
 * @version 1.0
 * @since   2021-11-01
 */
public class PrintReport extends UserInterfacePrinter {
    /**
     * Generates a SalesRevenueReport object based on choice of period (Day, Month, or Year)
     * @return SalesRevenueReport report object
     */
    public static SalesRevenueReport generateReport() {
        System.out.print("Choose a period to generate the Sales Revenue Report for:\n1) Day\n2) Month\n3) Year\nYour Choice: ");
        int choice = input.nextInt(); input.nextLine();
        String period = "";
        switch (choice) {
        case 1:
            period = "DAY";
            break;
        case 2:
            period = "MONTH";
            break;
        case 3:
        default:
            period = "YEAR";
        }
        SalesRevenueReport report = new SalesRevenueReport(period);
        return report;
    }

    /**
     * Prints the Sales Revenue Report for a particular SalesRevenueReport object
     */
    public static void print() {
        SalesRevenueReport report = PrintReport.generateReport();
        Map<AMenuItem, Integer> alacarteStatistics = report.getAlaCarteStatistics();
        Map<AMenuItem, Integer> promotionalStatistics = report.getPromotionalStatistics();
        String Header = "SALES REVENUE REPORT" + " (" + report.getPeriod() + ")";
        int count;

        // Line 1:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Line 2:
        String leftFormat = "%-" + ((rowLength-Header.length())/2) + "s";
        String rightFormat;
        if ((report.getPeriod().length()) % 2 == 0) {
            rightFormat = "%" + (((rowLength-Header.length())/2)) + "s";
        } else {
            rightFormat = "%" + (((rowLength-Header.length())/2) + 1) + "s";
        }
        System.out.format(leftFormat, "|");
        System.out.print(Header);
        System.out.format(rightFormat, "|");
        System.out.println();

        // Line 3:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Line 4 and 5:
        String totalStaffString = "| Total Staff: " + StaffManager.totalStaffNum();
        System.out.print(totalStaffString);
        System.out.format("%" + (rowLength - totalStaffString.length()) + "s", "|");
        System.out.println();
        String totalOrderString = "| Orders fulfilled: " + report.getOrderList().size();
        System.out.print(totalOrderString);
        System.out.format("%" + (rowLength - totalOrderString.length()) + "s", "|");
        System.out.println();

        // Line 6:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Line 7:
        String itemsSoldHeader = "ITEMS SOLD";
        leftFormat = "%-" + ((rowLength / 2) - (itemsSoldHeader.length() / 2)) + "s";
        rightFormat = "%" + ((rowLength / 2) - (itemsSoldHeader.length() / 2) + 1) + "s";
        System.out.format(leftFormat, "|");
        System.out.print(itemsSoldHeader);
        System.out.format(rightFormat, "|");
        System.out.println();

        // Line 8:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Line 9:
        String alacarteHeader = "Ala Carte";
        leftFormat = "%-" + ((rowLength / 2) - (alacarteHeader.length() / 2)) + "s";
        rightFormat = "%" + ((rowLength / 2) - (alacarteHeader.length() / 2)) + "s";
        System.out.format(leftFormat, "|");
        System.out.print(alacarteHeader);
        System.out.format(rightFormat, "|");
        System.out.println();

        // Line 10:
        System.out.print("|");
        System.out.printf(" ".repeat(rowLength - 2));
        System.out.println("|");

        // Ala Carte:
        if (alacarteStatistics.isEmpty()) {
            String leftString = "| No Items sold.";
            System.out.print(leftString);
            String formatString = "%" + (rowLength - leftString.length()) + "s";
            System.out.format(formatString, "|");
            System.out.println();
        } else {
            for (var entry : alacarteStatistics.entrySet()) {
                AMenuItem alacarteItem = entry.getKey();
                String name = alacarteItem.getName();
                count = (int) alacarteStatistics.get(alacarteItem);
                String leftString = "| " + name + ": "+count;
                System.out.print(leftString);
                rightFormat = "%" + (rowLength - leftString.length()) + "s";
                System.out.format(rightFormat, "|");
                System.out.println();
            }
        }

        // Line Promotional - 1:
        System.out.print("|");
        System.out.printf(" ".repeat(rowLength - 2));
        System.out.println("|");

        // Promotional:
        String promotionalHeader = "Promotions";
        leftFormat = "%-" + ((rowLength / 2) - (promotionalHeader.length() / 2)) + "s";
        rightFormat = "%" + ((rowLength / 2) - (promotionalHeader.length() / 2) + 1) + "s";
        System.out.format(leftFormat, "|");
        System.out.print(promotionalHeader);
        System.out.format(rightFormat, "|");
        System.out.println();

        // Line Promotional+1
        System.out.print("|");
        System.out.printf(" ".repeat(rowLength - 2));
        System.out.println("|");

        if (promotionalStatistics.isEmpty()) {
            String leftString = "| No Items sold.";
            System.out.print(leftString);
            String formatString = "%" + (rowLength - leftString.length()) + "s";
            System.out.format(formatString, "|");
            System.out.println();
        } else {
            for (var entry : promotionalStatistics.entrySet()) {
                AMenuItem promotionItem = entry.getKey();
                String name = promotionItem.getName();
                count = (int) promotionalStatistics.get(promotionItem);
                String leftString = "| " + name + ": "+count;
                System.out.print(leftString);
                rightFormat = "%" + (rowLength - leftString.length()) + "s";
                System.out.format(rightFormat, "|");
                System.out.println();
            }
        }

        // Line x:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Line x+1:
        System.out.print("| Sales Revenue");
        String revenueFormat = "%" + (rowLength - 15) + "s";
        String revenueString = "|    $" + String.format("%.2f", report.getTotalRevenue()) + "    |";
        System.out.format(revenueFormat, revenueString);
        System.out.println();

        // Line x+2:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Line x+3:
        double earningsBeforeIncomeTax = report.getTotalRevenue();
        System.out.print("| Earnings before income tax");
        String beforeTaxFormat = "%" + (rowLength - 28) + "s";
        String beforeTaxString = "$" + String.format("%.2f", earningsBeforeIncomeTax) + "    |";
        beforeTaxString = String
                .format("|" + " ".repeat(revenueString.length() - beforeTaxString.length() - 1) + beforeTaxString);
        System.out.format(beforeTaxFormat, beforeTaxString);
        System.out.println();

        // Line x+4:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Line x+5:
        System.out.print("| Income tax expense");
        String incomeTaxFormat = "%" + (rowLength - 20) + "s";
        String incomeTaxString = "$" + String.format("%.2f", (earningsBeforeIncomeTax * (double) 7 / (double) 107)) + "    |";
        incomeTaxString = String
                .format("|" + " ".repeat(revenueString.length() - incomeTaxString.length() - 1) + incomeTaxString);
        System.out.format(incomeTaxFormat, incomeTaxString);
        System.out.println();

        // Line x+6:
        System.out.printf("-".repeat(rowLength));
        System.out.println();

        // Line x+7:
        double netIncome = earningsBeforeIncomeTax * (double) 100 / (double) 107;
        System.out.print("| Net income");
        String netIncomeFormat = "%" + (rowLength - 12) + "s";
        String netIncomeString = "$" + String.format("%.2f", netIncome) + "    |";
        netIncomeString = String
                .format("|" + " ".repeat(revenueString.length() - netIncomeString.length() - 1) + netIncomeString);
        System.out.format(netIncomeFormat, netIncomeString);
        System.out.println();
        // Line x+8:
        System.out.printf("-".repeat(rowLength));
        System.out.println();
    }
}
