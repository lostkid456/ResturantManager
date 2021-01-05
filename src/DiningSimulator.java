/**
 * This is the class where we run our simulation based on certain parameters
 * we set
 * Johnathan Zhao
 * id: 11289137
 * Recitation 8
 */

import java.util.*;

public class DiningSimulator {
    private ArrayList<Restaurant> restaurants;
    private int chefs;
    private int duration=0;
    private double arrivalProb;
    private int maxCustomerSize;
    private int numRestaurants;
    private int customersLost=0;
    private int totalServiceTime=0;
    private int customersServed=0;
    public int profit=0;
    private static int customersEntered=1;

    public DiningSimulator() {
    }

    /**
     *Method for our simulation
     * @return
     * The average time of service in the simulation
     * @throws NoSimulationException
     * If the parameters provided are not valid
     */
    public double simulate() throws NoSimulationException {
        String[] foods={"C","S","GC","CT","CW"};
        if (arrivalProb < 0 || numRestaurants <= 0 || maxCustomerSize <= 0) {
            throw new NoSimulationException();
        }
        for(int i=0;i<numRestaurants;i++){
            restaurants.add(new Restaurant());
        }
        for(int i=0;i<duration;i++){
            for(int j=0;j<numRestaurants;j++){
                for(int k=0;k<restaurants.get(j).size();k++){
                    restaurants.get(j).get(k).
                            setTimeToService(restaurants.get(j).get(k).
                                    getTimeToService()-5);
                    if(restaurants.get(j).size()>0) {
                        totalServiceTime += 5;
                    }
                }
            }
            ArrayList<Customer> customers=new ArrayList<>();
            for(int j=0;j<numRestaurants;j++){
                ArrayList<Customer> leftCustomers=new ArrayList<>();
                ArrayList<Customer> remainCustomers=new ArrayList<>();
                while(restaurants.get(j).size()>0){
                    Customer c=restaurants.get(j).dequeue();
                    if(c.getTimeToService()<=0){
                        leftCustomers.add(c);
                    }else{
                        remainCustomers.add(c);
                    }
                }
                int counter=0;
                while (remainCustomers.size()>0){
                    restaurants.get(j).enqueue(remainCustomers.get(counter));
                    remainCustomers.remove(counter);
                    counter--;
                    counter++;
                }
                for(int k=0;k<leftCustomers.size();k++){
                    System.out.println("Customer #"+ leftCustomers.
                            get(k).getOrderNumber() +" "
                                               +"has enjoyed their food! $"
                                               + leftCustomers.get(k).
                            getPriceOfFood()+" profit" );
                    profit+=leftCustomers.get(k).
                            getPriceOfFood();
                    customersServed++;
                }
            }
            System.out.println("Time "+(i+1));
            for(int j=0;j<numRestaurants;j++){
                for(int k=0;k<3;k++) {
                    double prob = Math.random();
                    if (prob < arrivalProb) {
                        if (restaurants.get(j).size() < maxCustomerSize) {
                            Customer c = new Customer();
                            c.setTimeArrived(i);
                            c.setRestaurantNumber(j);
                            c.setOrderNumber(customersEntered);
                            customersEntered++;
                            int randomFood = randInt(0, 4);
                            String food = foods[randomFood];
                            c.setFood(food);
                            int chefTime = ((chefs - 3) * 5);
                            if (chefTime > 10) {
                                chefTime = 10;
                            }
                            if (food.equals("C")) {
                                c.setTimeToService
                                        (25 - chefTime + 15 -
                                                 (5 * (i - c.getTimeArrived()))
                                        );
                                c.setPriceOfFood(15);
                            } else if (food.equals("S")) {
                                c.setTimeToService
                                        (30 - chefTime + 15 -
                                                 (5 * (i - c.getTimeArrived()))
                                        );
                                c.setPriceOfFood(25);
                            } else if (food.equals("G")) {
                                c.setTimeToService
                                        (15 - chefTime + 15 -
                                                 (5 * (i - c.getTimeArrived()))
                                        );
                                c.setPriceOfFood(10);
                            } else if (food.equals("CT")) {
                                c.setTimeToService
                                        (25 - chefTime + 15 -
                                                 (5 * (i - c.getTimeArrived()))
                                        );
                                c.setPriceOfFood(10);
                            } else {
                                c.setTimeToService
                                        (30 - chefTime + 15 -
                                                 (5 * (i - c.getTimeArrived()))
                                        );
                                c.setPriceOfFood(20);
                            }
                            restaurants.get(j).enqueue(c);
                            customers.add(c);
                        } else {
                            System.out.println("Customer #" + customersEntered +
                                                       " " +
                                                       "cannot be seated! They "
                                                       +
                                                       "have left the " +
                                                       "restaurant");
                            customersEntered++;
                            customersLost++;
                        }
                    }
                }
            }
            for(int k=0;k<customers.size();k++) {
                System.out.println("Customer #" +customers.get(k).
                        getOrderNumber()+" " +
                                           "has entered Restaurant " +
                                           (customers.get(k).
                                                   getRestaurantNumber()+1));
            }
            for(int k=0;k<customers.size();k++){
                System.out.println("Customer #"+customers.get(k).
                        getOrderNumber()+" has been seated with order "+
                                           customers.get(k).getFood());
            }
            for(int j=0;j<numRestaurants;j++){
                System.out.println((j+1)+": "+restaurants.get(j).toString());
                System.out.println();
            }
            if(i==duration){
                for(int j=0;j<numRestaurants;j++){
                    while(restaurants.get(j).size()>0){
                        restaurants.get(j).dequeue();
//                        Customer c=restaurants.get(j).dequeue();
//                        totalServiceTime+=c.getTimeToService();
//                        customersServed++;
//                        profit+=c.getPriceOfFood();
                    }
                }
            }
        }
        double rate=(double) totalServiceTime / customersServed;
        if(customersServed==0){
            return 0;
        }
        return rate;
    }

    /**
     *Returns a random integer from the range minVal to maxVal
     * @param minVal
     * The minimum value
     * @param maxVal
     * The maximum value
     * @return
     * A random integer
     */
    public int randInt(int minVal, int maxVal) {
        return (int) (Math.random() * (maxVal - minVal + 1) + minVal);
    }

    /**
     *Runs our simulation
     * @param args
     * @throws NoSimulationException
     * If parameters provided are not valid
     */
    public static void main(String[] args) throws NoSimulationException {
        while(true) {
            Scanner inp = new Scanner(System.in);
            DiningSimulator diningSimulator = new DiningSimulator();
            System.out.println("Starting simulator");
            System.out.println("Enter the maximum of restaurants");
            try {
                diningSimulator.numRestaurants = inp.nextInt();
                System.out.println("Enter the maximum number of customers a " +
                                           "restaurant can serve:");
                diningSimulator.maxCustomerSize = inp.nextInt();
                System.out.println("Enter the arrival probability " +
                                           "of a customer:");
                diningSimulator.arrivalProb = inp.nextDouble();
                System.out.println("Enter the number of chefs:");
                diningSimulator.chefs = inp.nextInt();
                System.out.println("Enter the number of simulation units:");
                diningSimulator.duration = inp.nextInt();
                if (diningSimulator.duration <= 0) {
                    System.out.println("No simulation");
                    continue;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input");
            }
            diningSimulator.restaurants =
                    new ArrayList<Restaurant>();
            try {
                double avgTime= diningSimulator.simulate();
                System.out.println("Total customer time:"+
                                           diningSimulator.totalServiceTime+
                                           "    minutes");
                System.out.println("Total customers served:"+
                                           diningSimulator.customersServed);
                System.out.println("Average customer time lapse:"+avgTime+" " +
                                           "minutes per order");
                System.out.println("Total Profit: $"+diningSimulator.profit);
                System.out.println("Customers that left:"+
                                           diningSimulator.customersLost);
                System.out.println("Do you want to try another simulation");
                String input=inp.next();
                if(input.equals("n")||input.equals("N")){
                    System.out.println("Program terminating normally...\n");
                    System.exit(0);
                }
            } catch (NoSimulationException e) {
                System.out.println("No simulation");
            }
        }
    }

    /**
     *Throws an exception when the parameters provided for simulation are not
     *  valid
     */
    static class NoSimulationException extends Exception {
        public NoSimulationException() {
        }
    }

}
