package com;

import java.io.*;
import java.io.FileReader;


public class User {

	private String userName;
	private String password;
	private String userType;
	
	
	public User(String userName, String password, String userType) {
		super();
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	
}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	public boolean login(String username, String password) {
        return this.userName.equals(username) && this.password.equals(password);
    }

    public  void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("User.txt", true));
            writer.write(userName + " " + password + " " + userType + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        System.out.println("Username: " + userName);
        System.out.println("User Type: " + userType==1+"Admin");
        System.out.println("User Type: " + userType==2+"Customer");
    }


	
	
public static void main(String [] args)
{   
	
	User u1=new User("Rahul","Rahul@07","1");
	
	u1.printData();
	
	
	
}
}










