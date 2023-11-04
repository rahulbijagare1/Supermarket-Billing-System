package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	    public static void main(String[] args) {
	        // Create an Admin user and save it to the txt file
	        User adminUser = new User("admin", "admin123", 1);
	        adminUser.save();

	        // Ask the user to login
	        System.out.println("Welcome to the Supermarket Billing System!");
	        System.out.println("Please login to continue.");
	        boolean loggedIn = false;

	        while (!loggedIn) {
	            String username = System.console().readLine("Username: ");
	            char[] passwordChars = System.console().readPassword("Password: ");
	            String password = new String(passwordChars);

	            if (adminUser.login(username, password)) {
	                System.out.println("Login successful! Welcome, Admin.");
	                loggedIn = true;
	            } else {
	                System.out.println("Login failed. Please try again.");
	            }
	        }
	     // Admin menu

	        boolean exitAdmin = false;
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// for input

	        while (!exitAdmin) 
	        {
	            System.out.println("Admin Menu:");
	            System.out.println("1. Create Customer");
	            System.out.println("2. Search A Customer");
	            System.out.println("3. Print Details of all Customers");
	            System.out.println("4. Add Products");
	            System.out.println("5. Exit");
	            
	            int adminChoice;
	            try 
	            {
	            	adminChoice = Integer.parseInt(reader.readLine());  //read user input 
	            }
	            
	           catch(IoException e) {
	        	   e.printStackTrace();
	        	   continue;
	           }
	            
	            switch (adminChoice) 
	            {
	            case 1:
	            	 System.out.println("Create a new customer: ");
	            	 String customerUsername = reader.readLine("Username: ");
	            	 String customerName = reader.readLine("Name: ");
	            	 String customerAddress = reader.readLine("Address: ");
	            	 String customerUser = new User(customerUsername , " Team2 ",2);
	            	 customerUser.save();
	            	 Customer newCustomer = new Customer(customerUser , customerName, customerAddress);
	            	 newCustomer.createCustomer();
	            	 break;
	            	 
	            case 2:
	            	String searchUsername = reader.readerLine("Enter the username of the customer to search: ");
	            	Customer.searchCustomer(searchUsername);
	            	break;
	            	
	            case 3:
	            	//details of all customers
	            	try 
	            	{
	            		BufferedReader fileReader = new BufferedReader(new FileReader("customers.txt"));
	            		String line;
	            		while ((line = fileReader.readLine()) != null)
	            		{
	            			String[] parts = line.split(",");
	            			String username = parts[0];
	            			customer.searchcustomer(username);
	            		}
	            		filereader.close();  //close fr
	            	}
	                catch (IOException e) 
	            	{
	                e.printStackTrace();
	                }
	                break;
	                
	            case 4:
	                System.out.println("Add a new product:");
	                int productId = Integer.parseInt(reader.readLine("Product ID: "));
	                String productDescription = reader.readLine("Description: ");
	                double productPrice = Double.parseDouble(reader.readLine("Price: "));
	                int productQuantity = Integer.parseInt(reader.readLine("Quantity: "));
	                double productGSTrate = Double.parseDouble(reader.readLine("GST Rate: "));
	                
	                Product newProduct = new Product(productId, productDescription, productPrice, productQuantity, productGSTrate);
	                newProduct.saveProduct();
	                break;
	              
	            case 5:
	            	System.out.println("Existing Admin Menu");  //exit
	            	exitAdmin = true;
	            	break;
	            default:
	            	System.out.println("Invalid option. Please select a valid option.");
	                
	            	}
	            }

	        
	    }
	}
