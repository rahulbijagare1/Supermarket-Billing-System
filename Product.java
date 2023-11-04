package com;
import java.io.*;
import com.Main;
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

