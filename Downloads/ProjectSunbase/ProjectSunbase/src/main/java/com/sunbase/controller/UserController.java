package com.sunbase.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSessionContext;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.razorpay.RazorpayClient;
import com.sunbase.dao.CustomerRepository;
import com.sunbase.dao.UserRepository;
import com.sunbase.entities.Customer;
import com.sunbase.entities.User;
import com.sunbase.helper.Message;





public class UserController<HttpSession> {
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	// method for adding common data to response
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME " + userName);

		// get the user using usernamne(Email)

		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER " + user);
		model.addAttribute("user", user);
		}

	// open add form handler
	@GetMapping("/add_contact_form")
	public String openAddContactForm(Model model) {
		model.addAttribute("title", "Add Customer");
		model.addAttribute("contact", new Customer());

		return "add_contact_form";
	}

	// processing add customer form
	@PostMapping("/process-customer")
	public String processContact(@ModelAttribute Customer customer,
			Principal principal, javax.servlet.http.HttpSession session) {

		try {

			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);

			user.getCustomers().add(customer);

			customer.setUser(user);

			this.userRepository.save(user);

			System.out.println("DATA " + customer);

			System.out.println("Added to data base");

			// message success.......
			session.setAttribute("message", new Message("Your Customer is added !! Add more..", "success"));

		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
			e.printStackTrace();
			// message error
			session.setAttribute("message", new Message("Some went wrong !! Try again..", "danger"));

		}

		return "add_customer_form";
	}

	// show contacts handler
	// per page = 5[n]
	// current page = 0 [page]
	@GetMapping("/show-customers/{page}")
	public String showCustomers(@PathVariable("page") Integer page, Model m, Principal principal) {
		m.addAttribute("title", "Show User Customer");
		// contact ki list ko bhejni hai

		String userName = principal.getName();

		User user = this.userRepository.getUserByUserName(userName);

		// currentPage-page
		// Contact Per page - 5
		Pageable pageable = PageRequest.of(page, 8);

		Page<Customer> contacts = this.customerRepository.findCustomersByUser(user.getId(), pageable);

		m.addAttribute("contacts", contacts);
		m.addAttribute("currentPage", page);
		m.addAttribute("totalPages", contacts.getTotalPages());

		return "normal/show_contacts";
	}

	// showing particular contact details.

	@RequestMapping("/{cId}/customer")
	public String showCustomerDetail(@PathVariable("cId") Integer cId, Model model, Principal principal) {
		System.out.println("CID " + cId);

		Optional<Customer> customerOptional = this.customerRepository.findById(cId);
		Customer customer = customerOptional.get();

		//
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);

		if (user.getId() == customer.getUser().getId()) {
			model.addAttribute("contact", customer);
			model.addAttribute("title", customer.getFirstname());
		}

		return "customer_detail";
	}

	// delete contact handler

	@GetMapping("/delete/{cid}")
	@Transactional
	public String deleteContact(@PathVariable("cid") Integer cId, Model model, HttpSession session,
			Principal principal) {
		System.out.println("CID " + cId);

		Customer customer = this.customerRepository.findById(cId).get();
		// check...Assignment..image delete

		// delete old photo

		User user = this.userRepository.getUserByUserName(principal.getName());

		user.getCustomers().remove(customer);

		this.userRepository.save(user);

		System.out.println("DELETED");
		((javax.servlet.http.HttpSession) session).setAttribute("message", new Message("Customer deleted succesfully...", "success"));

		return "redirect:/user/show-contacts/0";
	}

	// open update form handler
	@PostMapping("/update-customer/{cid}")
	public String updateForm(@PathVariable("cid") Integer cid, Model m) {

		m.addAttribute("title", "Update Customer");

		Customer contact = this.customerRepository.findById(cid).get();

		m.addAttribute("customer",null);

		return "update_form";
	}

	// update contact handler
	@RequestMapping(value = "/process-update", method = RequestMethod.POST)
	public String updateHandler(@ModelAttribute Customer customer, @RequestParam("profileImage") MultipartFile file,
			Model m, HttpSessionContext session, Principal principal) {

		try {

			// old contact details
			Customer oldcustomerDetail = this.customerRepository.findById(customer.getcId()).get();

			

			User user = this.userRepository.getUserByUserName(principal.getName());

			customer.setUser(user);

			this.customerRepository.save(customer);

			((javax.servlet.http.HttpSession) session).setAttribute("message", new Message("Your Customer is updated...", "success"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("CONTACT NAME " + customer.getFirstname());
		System.out.println("CONTACT ID " + customer.getcId());
		return "redirect:/user/" + customer.getcId() + "/contact";
	}

	// your profile handler
	@GetMapping("/profile")
	public String yourProfile(Model model) {
		model.addAttribute("title", "Profile Page");
		return "normal/profile";
	}

	// open settings handler
	@GetMapping("/settings")
	public String openSettings() {
		return "normal/settings";
	}

	// change password..handler
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal principal, HttpSessionContext session) {
		System.out.println("OLD PASSWORD " + oldPassword);
		System.out.println("NEW PASSWORD " + newPassword);

		String userName = principal.getName();
		User currentUser = this.userRepository.getUserByUserName(userName);
		System.out.println(currentUser.getPassword());

		if (this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
			// change the password

			currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			this.userRepository.save(currentUser);
			((javax.servlet.http.HttpSession) session).setAttribute("message", new Message("Your password is successfully changed..", "success"));

		} else {
			// error...
			((javax.servlet.http.HttpSession) session).setAttribute("message", new Message("Please Enter correct old password !!", "danger"));
			return "redirect:/user/settings";
		}

		return "redirect:/user/index";
	}

	
	
	}


