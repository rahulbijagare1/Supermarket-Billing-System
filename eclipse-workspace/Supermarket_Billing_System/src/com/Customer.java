package com;


import java.io.*;


public class Customer {

	private User user;
    private String name;
    private String address;

    public Customer(User user, String name, String address) {
        this.user = user;
        this.name = name;
        this.address = address;
    }

    public Customer(String string, String name2, String address2) {
		// TODO Auto-generated constructor stub
	}
    
 //   public boolean Customerlogin(String username, String password) {
     //   return this.username.equals(username) && this.password.equals(password);
  //  }

	public void printDetails() {
    	
    }

    public void createCustomer() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true));
            writer.write(user.getUsername() + "," + name + "," + address + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCustomer(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Customers.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    System.out.println("Customer Details:");
                    System.out.println("Username: " + parts[0]);
                    System.out.println("Name: " + parts[1]);
                    System.out.println("Address: " + parts[2]);
                    return;
                }
            }
            System.out.println("Customer not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}
}
