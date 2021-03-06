package Classes.SalesRevenueReport;

import java.util.ArrayList;

import Classes.Order.Order;
import Classes.Order.OrderManager;
import Classes.Time.DateTimeFormatHelper;
import java.util.HashMap;
import java.util.Map;
import Classes.AMenuItem.AMenuItem;
import Classes.AMenuItem.AMenuItem.TYPE;

/**
 * The SalesRevenueReport Class
 * Contains the sales information of all items sold in a particular period of time.
 * @author  Min
 * @version 1.0
 * @since   2021-11-02
 */
public class SalesRevenueReport {
    /**
     * Collection of all orders.
     */
    private ArrayList<Order> orderList;
    /**
     * A specified period to generate a report from
     */
    private String period;
    /**
     * Total revenue in the given peroid.
     */
    private double totalRevenue;
    /**
     * Statistics of alacarte items sold.
     */
    private Map<AMenuItem, Integer> alacarteStatistics;
    /**
     * Statistics of promotional items sold.
     */
    private Map<AMenuItem, Integer> promotionalStatistics;

    /**
     * Constructor to create a report for a specified period.
     * 
     * @param period The period (Day, Month, Year) that the report covers.
     */
    public SalesRevenueReport(String period) {
        this.orderList = new ArrayList<Order>();
        this.period = period;
        this.totalRevenue = 0;
        this.alacarteStatistics = new HashMap<AMenuItem, Integer>();
        this.promotionalStatistics = new HashMap<AMenuItem, Integer>();
        String currentDate = DateTimeFormatHelper.formatToStringDate(DateTimeFormatHelper.inbuiltDate());

        switch (this.period) {
            case "DAY":
                for (Order order : OrderManager.getOrderHistory()) {
                    if ((order.getDateTime().charAt(0) + order.getDateTime().charAt(1)) == (currentDate.charAt(0)
                            + currentDate.charAt(1))) {
                                System.out.println(order.getDateTime());
                        this.orderList.add(order);
                        this.totalRevenue += order.getGrandTotal();
                    }
                }
                break;
    
            case "MONTH":
                for (Order order : OrderManager.getOrderHistory()) {
                    if ((order.getDateTime().charAt(3) + order.getDateTime().charAt(4)) == (currentDate.charAt(3)
                            + currentDate.charAt(4))) {
                        this.orderList.add(order);
                        this.totalRevenue += order.getGrandTotal();
                    }
                }
                break;
    
            case "YEAR":
                for (Order order : OrderManager.getOrderHistory()) {
                    if ((order.getDateTime().charAt(6) + order.getDateTime().charAt(7) + order.getDateTime().charAt(8)
                            + order.getDateTime().charAt(9)) == (currentDate.charAt(6) + currentDate.charAt(7)
                                    + currentDate.charAt(8) + currentDate.charAt(9))) {
                        this.orderList.add(order);
                        this.totalRevenue += order.getGrandTotal();
                    }
                }
                break;
            default:
        }

        for (Order order : this.orderList) {
            for (var entry : order.getItemList().entrySet()) {
                AMenuItem item = entry.getKey();
                int quantity = entry.getValue();
                if (item.getType() == TYPE.ALACARTE) {
                    if (!alacarteStatistics.containsKey(item)) { // key is not in our map yet
                        alacarteStatistics.put(item, quantity);
                    } else {
                        alacarteStatistics.put(item, alacarteStatistics.get(item));
                    }
                } else if (item.getType() == TYPE.PROMOTIONAL) {
                    if (!promotionalStatistics.containsKey(item)) { // key is not in our map yet
                        promotionalStatistics.put(item, quantity);
                    } else {
                        promotionalStatistics.put(item, alacarteStatistics.get(item));
                    }
                }
            }
        }
    }

    /**
     * Accessors to get the order history within the specified period for this report
     * 
     * @return a list of the order history for the report's period
     */
    public ArrayList<Order> getOrderList() {
        return this.orderList;
    }

    /**
     * Accessors to get the period that the report covers
     * 
     * @return the period that the report covers
     */
    public String getPeriod() {
        return this.period;
    }

    /**
     * Accessors to get the total revenue reported in the report
     * 
     * @return the total revenue covered in the report
     */
    public double getTotalRevenue() {
        return this.totalRevenue;
    }

    /**
     * Accessors to get the Map containing the statistics for Ala Carte items
     * 
     * @return a map containing statistics for Ala Carte items
     */
    public Map<AMenuItem, Integer> getAlaCarteStatistics() {
        return this.alacarteStatistics;
    }

    /**
     * Accessors to get the Map containing the statistics for Promotional items
     * 
     * @return a map containing statistics for Promotion items
     */
    public Map<AMenuItem, Integer> getPromotionalStatistics() {
        return this.promotionalStatistics;
    }
}
