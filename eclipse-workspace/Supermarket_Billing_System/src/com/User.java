package com;

import java.io.*;
import java.io.FileReader;


public class User {

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

	    public  boolean login(String username, String password) {
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


