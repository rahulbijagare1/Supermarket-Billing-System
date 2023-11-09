
package com;

public class Admin 
{

	private User user;
	private String name;
	
	public Admin(User user , String name) 
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
