package com;
import java.io.*;



public class Main {

	
	
	public static  User customerUser;
		static double currentprice;
	 //PurchaseLinkedList purchaseList1 = new PurchaseLinkedList();
	
	public static void main(String[] args) throws IOException
    {
		
		try {
		    System.out.println("Welcome to the Supermarket Billing System.");
		    System.out.println("Press 1 for Admin Login");
		    System.out.println("Press 2 for Customer Login");

		    BufferedReader usertype = new BufferedReader(new InputStreamReader(System.in));
		    String userInput = usertype.readLine();

		    int userChoice = Integer.parseInt(userInput);

		    switch (userChoice) {
		        case 1:
		            AdminMenu();
		            break;
		        case 2:
		            CustomerMenu();
		            break;
		        default:
		            System.out.println("Select a valid menu option (1 for Admin, 2 for Customer).");
		    }
		} catch (NumberFormatException e) {
		    System.out.println("Invalid input. Please enter 1 for Admin or 2 for Customer.");
		}
    }
	  
	
               //Admin Menu
				private static void AdminMenu()throws IOException {
					   User adminUser = new User("admin","admin123",1);
				 	   adminUser.save();
				 	   
				 	   System.out.println("Welcome to the Supermarket Billing System.");
				 	   System.out.println("Please login to continue.");
				 	   boolean loggedIn = false;
				 	   
				        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				while (!loggedIn) {
				 try {
				     System.out.print("Username: ");
				     String username = br.readLine();

				     System.out.print("Password: ");
				     String password = br.readLine();

				     if (adminUser.login(username, password)) {
				         System.out.println("Login successful! Welcome, Admin.");
				         loggedIn = true;
				     } else {
				         System.out.println("Login failed. Please try again.");
				       
				     }
				 } catch (IOException e) {
				     e.printStackTrace();
				 }
				}
				 	     
				        boolean exitAdmin = false;
				        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
				        while (!exitAdmin) {
				            System.out.println("Admin Menu:");
				            System.out.println("1. Create Customer");
				            System.out.println("2. Search A Customer");
				            System.out.println("3. Print Details of all Customers");
				            System.out.println("4. Add Products");
				            System.out.println("5. Exit");
				            
				            int adminChoice;
				            try 
				            {
				                adminChoice = Integer.parseInt(reader.readLine()); 
				            } catch (IOException e) 
				            {
				                e.printStackTrace();
				                continue; 
				            }


				            switch (adminChoice) {
				                case 1:
				                   //create customer
				             	    System.out.print("Enter the new customer's username: ");
				             	    String customerUsername = reader.readLine();
				             	    
				             	    System.out.print("Enter the customer's name: ");
				             	    String customerName = reader.readLine();
				             	    
				             	    System.out.print("Enter the customer's address: ");
				             	    String customerAddress = reader.readLine();
				             	    
				             	    // Create a User object for the new customer
				             	    User customerUser = new User(customerUsername, "customer123", 2);
				             	    customerUser.save();
				             	    
				             	    // Create a Customer object and save its details
				             	    Customer newCustomer = new Customer(customerUser, customerName, customerAddress);
				             	    newCustomer.createCustomer();
				             	    
				             	    System.out.println("New customer created successfully.");
				             	    break;
				             	    
				                case 2:
				                    // Search A Customer
				                    System.out.print("Enter the username of the customer to search: ");
				                    String searchUsername = reader.readLine();
				                    Customer.searchCustomer(searchUsername);
				                    break;
				                    
				                case 3:
				                    // Print Details of all Customers
				                    try {
				                        BufferedReader fileReader = new BufferedReader(new FileReader("Customers.txt"));
				                        String line;
				                        while ((line = fileReader.readLine()) != null) {
				                            String[] parts = line.split(",");
				                            if (parts.length >= 1) {
				                                String username = parts[0];
				                                Customer.searchCustomer(username);
				                            }
				                        }
				                        fileReader.close(); 
				                    } catch (IOException e) {
				                        e.printStackTrace();
				                    }
				                    break;
				                    
				                case 4:
				                
				                    System.out.println("Add a new product:");
				                    System.out.print("Product ID: ");
				                    String productIdStr = reader.readLine();
				                    int productId = Integer.parseInt(productIdStr);
				                    System.out.print("Description: ");
				                    String productDescription = reader.readLine();
				                    System.out.print("Price: ");
				                    String productPriceStr = reader.readLine();
				                    double productPrice = Double.parseDouble(productPriceStr);
				                    System.out.print("Quantity: ");
				                    String productQuantityStr = reader.readLine();
				                    int productQuantity = Integer.parseInt(productQuantityStr);
				                    System.out.print("GST Rate: ");
				                    String productGSTrateStr = reader.readLine();
				                    double productGSTrate = Double.parseDouble(productGSTrateStr);
				                    Product newProduct = new Product(productId, productDescription, productPrice,          						
				                    		productQuantity, productGSTrate);
				                    newProduct.saveProduct();
				                    break;
				                    
				                case 5:
				                   
				                    System.out.println("Exiting Admin Menu.");
				                    exitAdmin = true;
				                    break;
				                    
				                default:
				                    System.out.println("Invalid option. Please select a valid option.");
				            }
				        
				        }
				}
				
				private static PurchaseLinkList purchaseList1 = new PurchaseLinkList();
			private static void CustomerMenu() throws IOException {
				
				 PurchaseLinkList purchaseList1 = new PurchaseLinkList();
				     customerUser = new User("Santosh", "customer123", 2);
				    customerUser.save();

				    System.out.println("Welcome to the Supermarket Billing System.");
				    System.out.println("Please login to continue.");

				    boolean loggedIn = false;
				    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				    while (!loggedIn) {
				        System.out.print("Username: ");
				        String username = br.readLine();

				        System.out.print("Password: ");
				        String password = br.readLine();

				        if (customerUser.login(username, password)) {
				            System.out.println("Login successful! Welcome, Customer.");
				            loggedIn = true;
				        } else {
				            System.out.println("Login failed. Please try again.");
				        }
				    }
				    PurchaseLinkList purchaseList = new PurchaseLinkList();
				    boolean exitCustomer = false;
				    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

				    while (!exitCustomer) {
				        System.out.println("Customer Menu:");
				        System.out.println("1. Search for Products");
				        System.out.println("2. See all products");
				        System.out.println("3. Buy Products (check the quantity available for a purchase and deduct the qty of the current purchase from the available quantity. Don't allow purchase in case of insufficient stock)");
				        System.out.println("4. Create Bill (calculate the total amount)");
				        System.out.println("5. Exit");

				        int customerChoice;
				        try {
				            customerChoice = Integer.parseInt(reader.readLine());
				        } catch (IOException e) {
				            e.printStackTrace();
				            continue;
				        }

				        switch (customerChoice) {
				            case 1:
				             
				            	// Search for Products
				            	
				                Product newp = new Product(1,"Laptop",50000,1,2.00);
			             	    newp.saveProduct();
			             	    
				                System.out.print("Enter ProductID to search: ");
				                String searchProduct = reader.readLine();
				                Product.searchProduct(searchProduct);
				                break;

				            case 2:
				                // See all products
				                try {
				                    BufferedReader fileReader = new BufferedReader(new FileReader("Products.txt"));
				                    String line;
				                    while ((line = fileReader.readLine()) != null) {
				                        String[] parts = line.split(",");
				                        if (parts.length >= 1) {
				                            String allProducts = parts[0];
				                            Product.searchProduct(allProducts);
				                        }
				                    }
				                    fileReader.close();
				                } catch (IOException e) {
				                    e.printStackTrace();
				                }
				                break;

				           
				                // Buy Products
				            case 3:
				            	  // Buy Products
				                System.out.print("Enter the product ID to buy: ");
				                String productIDStr = reader.readLine();
				                int productID = Integer.parseInt(productIDStr);
				                
				                
				                System.out.print("Enter the quantity to buy: ");
				                String quantityStr = reader.readLine();
				                int quantity = Integer.parseInt(quantityStr);
				                try {
				                    BufferedReader reader1 = new BufferedReader(new FileReader("Products.txt"));
				                  

				                    String line;
				                    while ((line = reader1.readLine()) != null) {
				                        String[] parts = line.split(",");
				                        if ((parts[0]).equals(productIDStr)) {
				                             currentprice = Double.parseDouble(parts[2]);
				                            
				                        } 
				                    }
				                    reader1.close();
				                    
				         
				                    
				                } catch (IOException e) {
				                    e.printStackTrace();
				                }

				             

				                // Check if there is sufficient stock
				               Product productToBuy = new Product(productID, "",currentprice, 1, 1.0);
				                Product.searchProduct(productIDStr); // Display product details

				                // Retrieve available quantity from the file
				                int availableQuantity = 0;
				                try {
				                    BufferedReader reader4 = new BufferedReader(new FileReader("Products.txt"));
				                    String line;
				                    while ((line = reader4.readLine()) != null) {
				                        String[] parts = line.split(",");
				                        if (parts[0].equals(productIDStr)) {
				                            availableQuantity = Integer.parseInt(parts[3]);
				                            break;
				                        }
				                    }
				                    reader4.close();
				                } catch (IOException e) {
				                    e.printStackTrace();
				                }

				                // Perform the purchase operation if there is sufficient stock
				                if (quantity > 0 && quantity <= availableQuantity) {
				                    double totalAmount = productToBuy.getPrice() * quantity; // Assuming getPrice() method exists in the Product class
				                    System.out.println("Purchase successful. Total amount: $" + totalAmount);

				                    // Deduct the purchased quantity from available quantity
				                    productToBuy.updateProduct(productID, quantity);

				                    // Add the purchase to the Purchase List using the instance
				                    purchaseList1.addPurchase(productToBuy, quantity, totalAmount);
				                } else {
				                    System.out.println("Purchase failed. Please check the product and quantity.");
				                }
				                break;
				             

				            case 4:
				                // Create Bill
				                Bill bill = new Bill(new Customer(customerUser, "", ""));
				                double totalAmount = purchaseList1.calculateTotalAmount();
				                bill.setTotalAmount(totalAmount);
				                bill.printBill();
				                break;

				            case 5:
				                System.out.println("Exiting Customer Menu.");
				                exitCustomer = true;
				                break;

				            default:
				                System.out.println("Invalid option. Please select a valid option.");
				        }
				    }
				}
}
