package com;

import java.io.*;

class User {
    private String username;
    private String password;
    private int userType;

    public User(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public int getUserType() {
        return userType;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("User.txt", true));
            writer.write(username + "," + password + "," + userType + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        System.out.println("Username: " + username);
        System.out.println("User Type: " + (userType == 1 ? "Admin" : "Customer"));
    }
}



class Customer {
    private User user;
    private String name;
    private String address;

    public Customer(User user, String name, String address) {
        this.user = user;
        this.name = name;
        this.address = address;
    }

    public void printDetails() {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
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
}


public class Product {
    private int productId;
    private String description;
    private double price;
    private int quantity;
    private double GSTrate;

 public Product(int productId, String description, double price, int quantity, double GSTrate) {
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.GSTrate = GSTrate;
    }

    public void saveProduct() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Products.txt", true));
            writer.write(productId + "," + description + "," + price + "," + quantity + "," + GSTrate + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        System.out.println("Product ID: " + productId);
        System.out.println("Description: " + description);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("GST Rate: " + GSTrate + "%");
    }

    public void updateProduct(int productId, int newQuantity) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Products.txt"));
            StringBuilder updatedData = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == productId) {
                    int currentQuantity = Integer.parseInt(parts[3]);
                    if (currentQuantity >= newQuantity) {
                        parts[3] = String.valueOf(currentQuantity - newQuantity);
                        updatedData.append(String.join(",", parts)).append("\n");
                    } else {
                        System.out.println("Not enough stock available for the purchase.");
                        return;
                    }
                } else {
                    updatedData.append(line).append("\n");
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("Products.txt"));
            writer.write(updatedData.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


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
