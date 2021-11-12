/**
 * Contains the sales information of all items sold for a particular period of time.
 */
public class SalesRevenueReport {
    /**
     * Creates a Sales Revenue Report for a certain period of time.
     * @param orderList List of all orders sold within the period
     * @param staffList List of all staff
     * @param period The period (day, month, year) which sales are being reported for
     */
    private ArrayList<Order> orderList;
    private Staff[] staffList;
    private String period;
    private double totalRevenue;
    public SalesRevenueReport(ArrayList<Order> orderList, Staff[] staffList, String period){
        int i = 0;
        this.totalRevenue = 0;
        this.orderList = new ArrayList<Order>;
        this.period = period;
        String currentDate = DateTimeFormatHelper.formatToStringDate(DateTimeFormatHelper.getTodayDate(false));
        switch(this.period){
            case "DAY":
                for (Order order : orderList){
                    if ((order.getdateTime()[0]+order.getdateTime[1]) == (currentDate[0]+currentDate[1]))
                    {
                        orderList.add(order);
                        this.totalRevenue += order.getGrandTotal();
                    }
                }
            case "MONTH":
                for (Order order : orderList){
                    if ((order.getdateTime()[3]+order.getdateTime[4]) == (currentDate[3]+currentDate[4]))
                    {
                        orderList.add(order);
                        this.totalRevenue += order.getGrandTotal();
                    }
                }
            case "YEAR":
                for (Order order : orderList){
                    if ((order.getdateTime()[6]+order.getdateTime[7]+order.getdateTime()[8]+order.getdateTime()[9]) == (currentDate[6]+currentDate[7]+currentDate[8]+currentDate[9]))
                    {
                        orderList.add(order);
                        this.totalRevenue += order.getGrandTotal();
                    }
                }
        }
            this.totalRevenue += order.getGrandTotal();

        i = 0;
        this.staffList = new Staff[staffList.length];
        for (Staff staff : staffList){
            this.staffList[i] = staff;
        }
    }



}