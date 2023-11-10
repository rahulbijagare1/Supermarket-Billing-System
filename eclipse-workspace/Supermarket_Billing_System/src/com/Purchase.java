package com;

import java.io.*;
public class Purchase {

	private Product product;
    private int qty;
    private double amount;
    public Purchase nextPurchase;
   
 
    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Purchase(Product product, int qty, double amount) {
        this.product = product;
        this.qty = qty;
        this.amount = amount;
        this.nextPurchase = null;
    }

    public void printData() {
        System.out.println("Product: " + product.getDescription());
        System.out.println("Quantity: " + qty);
        System.out.println("Amount: " + amount);
    }
}

