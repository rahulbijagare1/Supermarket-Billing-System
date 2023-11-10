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
		
		return name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		
		return address;
	}
}
