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

