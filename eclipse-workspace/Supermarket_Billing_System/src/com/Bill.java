package com;

import java.io.*;
import java.util.Date;

public class Bill {

	
	public PurchaseLinkList getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(PurchaseLinkList purchaseList) {
		this.purchaseList = purchaseList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

		private PurchaseLinkList purchaseList;
	    private Customer customer;
	    private double totalAmount;
	    private Date date;

	    public Bill(Customer customer) {
	        this.purchaseList = new PurchaseLinkList();
	        this.customer = customer;
	        this.totalAmount = 0.0;
	        this.date = new Date();
	    }

	    public void addPurchase(Product product, int qty, double amount) {
	        purchaseList.addPurchase(product, qty, amount);
	        totalAmount += amount;
	    }

	    public void printBill() {
	    	
	        System.out.println("===== Bill Details =====");
	        System.out.println("Date: " + date);
	        System.out.println();
	        System.out.println("Customer Name: " + customer.getUser().getUsername());
	        System.out.println();
	       System.out.println("Customer Address: " + customer.getUser().getUsername());
	        System.out.println("===== Purchase Details =====");
	        purchaseList.printAllPurchases();
	        System.out.println("Total Amount: " + totalAmount);
	    }

	    public void saveBillToFile(String filename) {
	        try {
	            FileOutputStream fileOutputStream = new FileOutputStream(filename);
	            PrintWriter writer = new PrintWriter(fileOutputStream);

	            // Writing bill details to the file
	            writer.println("===== Bill Details =====");
	            writer.println("Date: " + date);
	            writer.println("Customer Name: " + customer.getName());
	            writer.println("Customer Address: " + customer.getAddress());
	            writer.println("\n===== Purchase Details =====");
	            purchaseList.printAllPurchases();
	            writer.println("\nTotal Amount: " + totalAmount);

	            // Close the PrintWriter and FileOutputStream
	            writer.close();
	            fileOutputStream.close();

	            System.out.println("Bill saved to " );
	        } catch (IOException e) {
	            System.err.println("Error while saving the bill to the file: " + e.getMessage());
	        }
	    }
}
   