package com;

public class Admin 
{

	private String user;
	private String name;
	
	public Admin(String user , String name) 
	{
		
	   this.user=user;
	   this.name=name;
	}
	
public void printDetails() 
{
	System.out.println("User: " + user);
	System.out.println("Name: " + name);
}
	
}
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;*/

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
