package com;

public class PurchaseLinkList {
	
	

	        
	        
		    private Purchase head;

		    // other methods...
		    public double totalAmount=0;

		    public   void addPurchase(Product product, int qty, double amount) {
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

	 /*   public  void addPurchase(Product product, int qty, double amount) {
	        Purchase newPurchase = new Purchase(product, qty, amount);
	        if (head == null) {
	            try {
					head = newPurchase;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else {
	            Purchase currentPurchase = head;
	            while (currentPurchase.nextPurchase != null) {
	                currentPurchase = currentPurchase.nextPurchase;
	            }
	            currentPurchase.nextPurchase = newPurchase;
	        }
	    }
	*/
	    public void printAllPurchases() {
	        Purchase currentPurchase = head;
	        while (currentPurchase != null) {
	            currentPurchase.printData();
	            currentPurchase = currentPurchase.nextPurchase;
	        }
	    }

	    public double calculateTotalAmount() {
	    
	        Purchase currentPurchase = head;
	        while (currentPurchase != null) {
	            totalAmount += currentPurchase.getAmount();
	            currentPurchase = currentPurchase.nextPurchase;
	        }
	        return totalAmount;
	    }
	   
	    }








