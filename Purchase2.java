package com;
import java.util.LinkedList;
public class Purchase2 {
	private String product;
    private int qty;
    private double amount;
    private Purchase2 next; 

    public Purchase2(String product, int qty,double amount) {
        this.product = product;
        this.qty = qty;
        this.amount=amount;
        //this.amount = product.getPrice() * qty;
        this.next = null;
    }

    public void setNextPurchase(Purchase2 next) {
        this.next = next;
    }

    public Purchase2 getNextPurchase() {
        return next;
    }

    /*public void savePurchase(Customer customer) {
    if (customer.getPurchaseList() == null) {
        customer.setPurchaseList(this);
    } else {
        Purchase1 cp = customer.getPurchaseList();
        while (cp.getNextPurchase() != null) {
            cp = cp.getNextPurchase();
        }
        cp.setNextPurchase(this);
    }
    }*/

    public void printData() {
        System.out.println("Product: " + product);
        System.out.println("Quantity: " + qty);
        System.out.println("Amount: " + amount);
    } 
   
    	public static void main(String[] args) {
    	
            //Customer customer = new Customer("John");
    		LinkedList<Purchase2> item = new LinkedList<>();

            Purchase2 p1 = new Purchase2("Bag", 1, 1000);
            Purchase2 p2 = new Purchase2("Shoes", 2, 1500);
            Purchase p3 = new Purchase("Laptop", 1, 50000);
            Purchase p4 = new Purchase("Mobile", 2, 25000);
            Purchase p5 = new Purchase("T-shirts", 4, 700);
            Purchase p6 = new Purchase("Jeans", 2, 1500);
            p1.printData();
            System.out.println("    ");
            p2.printData();
            System.out.println("    ");
            p3.printData();
            System.out.println("    ");
            p4.printData();
            System.out.println("    ");
            p5.printData();
            System.out.println("    ");
            p6.printData();
            
            /* p1.savePurchase(customer);
            p2.savePurchase(customer);
     */
            /*Purchase1 currentPurchase = customer.getPurchaseList();
            while (currentPurchase != null) {
                currentPurchase.printData();
                System.out.println();
                currentPurchase = currentPurchase.getNextPurchase();
            */

            }
    }




