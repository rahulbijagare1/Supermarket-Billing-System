package com.sunbase.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomeUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		if(username.equals("test@sunbasedata.com"))
		{
			return new User("test@sunbasedata.com","Test@123",new ArrayList<>());
			
		}
		else {
			throw new UsernameNotFoundException("User not Found");
		}
	}

	
	
}

