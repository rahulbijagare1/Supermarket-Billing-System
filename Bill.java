package com;

import java.io.*;
import java.time.LocalDate;

class Bill {
    private PurchaseList head;
    private Customer customer;
    private double totalAmount;
    private String date;

    public Bill(Customer customer, String date) {
        this.customer = customer;
        this.date = date;
        this.head = null;
        this.totalAmount = 0.0;
    }

    public void addPurchase(Product product, int qty) {
        Purchase purchase = new Purchase(product, qty);
        if (head == null) {
            head = new PurchaseList(purchase);
        } else {
            head.addPurchase(purchase);
        }
        totalAmount += purchase.getAmount();
    }

    public void printBill() {
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Date: " + date);
        System.out.println("----------------------------");
        PurchaseList current = head;
        while (current != null) {
            current.getPurchase().printData();
            System.out.println("----------------------------");
            current = current.getNext();
        }
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("----------------------------");
    }

    public void saveBill(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Customer Name: " + customer.getName());
            writer.println("Date: " + date);
            writer.println("----------------------------");
            PurchaseList current = head;
            while (current != null) {
                current.getPurchase().printData(writer);
                writer.println("----------------------------");
                current = current.getNext();
            }
            writer.println("Total Amount: " + totalAmount);
            writer.println("----------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            // Create a customer
            Customer customer = new Customer("John Doe");

            // Create a bill
            Bill bill = new Bill(customer, "2023-11-05");

            // Add purchases to the bill
            Product product1 = new Product("Item1", 10.0);
            Product product2 = new Product("Item2", 20.0);

            bill.addPurchase(product1, 3);
            bill.addPurchase(product2, 2);

            // Print the bill
            System.out.println("Bill Details:");
            bill.printBill();

            // Save the bill to a text file
            bill.saveBill("customer_bill.txt");

            System.out.println("Bill saved to customer_bill.txt");
        }
    }

}
