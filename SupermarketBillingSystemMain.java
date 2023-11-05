package com;
import java.io.*;
import java.io.IOException;
;
public class SupermarketBillingSystemMain 
{
       public static void main(String[] args) throws IOException
       {
    	   User adminUser = new User("admin","admin123",1);
    	   adminUser.save();
    	   
    	   System.out.println("Welcome to the Supermarket Billing System.");
    	   System.out.println("Please login to continue.");
    	   boolean loggedIn = false;
    	   
    	   while(!loggedIn) 
    	   {
    		   String username = "admin";
    		   String password = "admin123";
    		   
    		   if(adminUser.login(username,password))
    		   {
    			   System.out.println("Login successfull welcome,Admin.");
    			   loggedIn = true; 
    		   }
    		   else
    		   {
    			   System.out.println("Login failed. Please try again.");
    		   }
    	   }
    	   
    	   // Admin menu
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
                       Product newProduct = new Product(productId, productDescription, productPrice, productQuantity, productGSTrate);
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
}
