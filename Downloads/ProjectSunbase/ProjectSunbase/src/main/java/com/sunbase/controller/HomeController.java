package com.sunbase.controller;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunbase.dao.UserRepository;
import com.sunbase.entities.User;
import com.sunbase.helper.Message;



public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Customer Management");
		return "home";
	}

	@RequestMapping("/customer_details")
	public String about(Model model) {
		model.addAttribute("title", "Customer Details");
		return "customer_details";
	}

	@RequestMapping("/login")
	public String signup(Model model) {
		model.addAttribute("title", "Login to Portal");
		model.addAttribute("user", new User());
		return "login";
	}

	// handler for registering user
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1,
			@RequestParam(value = "login", defaultValue = "false") boolean login, Model model,
			HttpSession session) {

		try {

			if (!login) {
				System.out.println("You have not authorised");
				throw new Exception("You have not authorised users");
			}

			if (result1.hasErrors()) {
				System.out.println("ERROR " + result1.toString());
				model.addAttribute("user", user);
				return "login";
			}

			

			User result = this.userRepository.save(user);

			model.addAttribute("user", new User());

			session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
			return "login";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something Went wrong !! " + e.getMessage(), "alert-danger"));
			return "login";
		}

	}

	//handler for custom login
	@GetMapping("/login")
	public String customLogin(Model model)
	{
		model.addAttribute("title","Login Page");
		return "login";
	}

}
