package com.sunbase.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sunbase.dao.UserRepository;

public class ForgotController {

	Random random = new Random(1000);
	
	@Autowired
	private UserRepository userRepsitory;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	//email id form open  handler
		@RequestMapping("/forgot")
		public String openEmailForm()
		{
			return "forgot_email_form";
		}
		
		
	
}
