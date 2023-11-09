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

 class PurchaseLinkedList {
    private Purchase head;

    public PurchaseLinkedList() {
        this.head = null;
    }

    public void addPurchase(Product product, int qty, double amount) {
        Purchase newPurchase = new Purchase(product, qty, amount);
        if (head == null) {
            head = newPurchase;
        } else {
            Purchase currentPurchase = head;
            while (currentPurchase.nextPurchase != null) {
                currentPurchase = currentPurchase.nextPurchase;
            }
            currentPurchase.nextPurchase = newPurchase;
        }
    }

    public void printAllPurchases() {
        Purchase currentPurchase = head;
        while (currentPurchase != null) {
            currentPurchase.printData();
            currentPurchase = currentPurchase.nextPurchase;
        }
    }

    public double calculateTotalAmount() {
        double totalAmount = 10000;
        Purchase currentPurchase = head;
        while (currentPurchase != null) {
            totalAmount += currentPurchase.getAmount();
            currentPurchase = currentPurchase.nextPurchase;
        }
        return totalAmount;
    }
   
    }



