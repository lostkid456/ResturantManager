/**
 * This is the class to create a customer.
 * Johnathan Zhao
 * id:112892137
 * Recitation 8
 */
public class Customer {
    private static int totalCustomers=50;
    private int orderNumber;
    private String food;
    private int priceOfFood;
    private int timeArrived;
    private int timeToService;
    private int restaurantNumber;

    public Customer(){
    }

    /**
     * Returns the order number of the customer
     * @return
     * order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Returns the price of the food the customer ordered
     * @return
     * price of the food
     */
    public int getPriceOfFood() {
        return priceOfFood;
    }

    /**
     * Returns the time the customer arrived
     * @return
     * time arrived
     */
    public int getTimeArrived() {
        return timeArrived;
    }

    public static int getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * Returns the food the customer ordered
     * @return
     * the food the customer ordered
     */
    public String getFood() {
        return food;
    }

    /**
     * Returns the time till the customer is serviced
     * @return
     * time till the customer is serviced
     */
    public int getTimeToService() {
        return timeToService;
    }

    /**
     * Returns the Restaurant number the customer is in
     * @return
     * restaurant number the customer is in
     */
    public int getRestaurantNumber() {
        return restaurantNumber;
    }

    /**
     * Sets the food the customer orders to something
     * @param food
     * the food that we want the order to be
     */
    public void setFood(String food) {
        this.food = food;
    }

    /**
     *Sets the order number to a different number
     * @param orderNumber
     * the order number we want set to
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     *Sets the price of the food to a different price
     * @param priceOfFood
     * the price of the food to be set to
     */
    public void setPriceOfFood(int priceOfFood) {
        this.priceOfFood = priceOfFood;
    }

    /**
     *Sets the time arrived to a different time
     * @param timeArrived
     * the time arrived to be set to
     */
    public void setTimeArrived(int timeArrived) {
        this.timeArrived = timeArrived;
    }

    /**
     *Sets the time to be serviced to a different time.
     * @param timeToService
     * the time to service to be set to.
     */
    public void setTimeToService(int timeToService) {
        this.timeToService = timeToService;
    }

    /**
     *Sets the restaurant number to a different number
     * @param restaurantNumber
     * the restaurant number to be set to
     */
    public void setRestaurantNumber(int restaurantNumber) {
        this.restaurantNumber = restaurantNumber;
    }

    /**
     *Prints out the information of a customer in a neat fashion
     * @return
     * the information of a customer
     */
    public String toString(){
        String str="";
        str+=orderNumber+" "+food+" "+timeToService+" min";
        return str;
    }

}
