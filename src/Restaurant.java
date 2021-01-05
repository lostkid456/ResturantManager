/**
 * This creates a Restaurant and allows us to add and remove customers from
 * the queue.
 *Johnathan Zhao
 * id:112892137
 * Recitation 8
 */

import java.util.ArrayList;

public class Restaurant extends ArrayList<Customer> {
    public Restaurant(){}

    /**
     *Adds a Customer to the Restaurant Queue
     * @param c
     * The customer we want added in
     */
    public void enqueue(Customer c){
        this.add(c);
    }

    /**
     *Removes the Customer at the front of the Restaurant Queue
     * @return
     * The customer we removed
     */
    public Customer dequeue() {
        return this.remove(0);
    }

}
