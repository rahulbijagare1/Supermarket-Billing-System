package com;
import java.io.*;
import java.io.IOException;


public class Customer1 {
	private Purchase2 purchaseList;
	static private String user;
	static private String name;
	static private String address;
	 private Purchase2 next; 
	
public Customer1(String user, String name, String address) {
		super();
		this.user = user;
		this.name = name;
		this.address = address;
		this.purchaseList = null;
	}

public Purchase2 getPurchaseList() {
    return purchaseList;
}

public void setPurchaseList(Purchase2 purchaseList) {
    this.purchaseList = purchaseList;
}

public static void printDetails() {
    System.out.println("User: " + user);
    System.out.println("Name: " + name);
    System.out.println("Address: " + address);
}
public static void createCustomer(Customer1 customer) {
//String filePath = "Customers.txt";
try {
    
    FileWriter fr = new FileWriter("Customers.txt", true);
    BufferedWriter bufferedWriter = new BufferedWriter(fr);

    
    bufferedWriter.write("User: " + customer.user);
    bufferedWriter.newLine();  // Add a new line
    bufferedWriter.write("Name: " + customer.name);
    bufferedWriter.newLine();
    bufferedWriter.write("Address: " + customer.address);
    bufferedWriter.newLine();
    bufferedWriter.newLine();  
  
    bufferedWriter.close();
    
} 
catch (IOException e) 
{
    e.printStackTrace();
}
}
public static void searchCustomer(String user)
{
    try {
        // Open the file for reading
        FileReader fr1 = new FileReader("Customers.txt");
        BufferedReader bufferedReader = new BufferedReader(fr1);
     
        String line;
        // Read the file line by line
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("User: " + user)) 
            {
                // Print the customer's details when a match is found
                System.out.println("Customer is found and Customer Details are :");
                System.out.println("    ");
                System.out.println(line);
                for (int i = 0; i < 2; i++) {
                    line = bufferedReader.readLine();
                    System.out.println(line);
                }
                
                break;
            }

        }
    }
    catch(IOException e)
    {
    	e.printStackTrace();
    }  
                
    }


public static void main(String[] args) {
 
    Customer1 c = new Customer1("Rahul123", "Rahul", "123 Satav Street");

    createCustomer(c);

    c.printDetails();
    System.out.println("    ");
    searchCustomer("Rahul123");
    
}
}

