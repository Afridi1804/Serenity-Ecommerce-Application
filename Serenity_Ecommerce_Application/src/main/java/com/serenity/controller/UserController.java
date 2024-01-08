package com.serenity.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.serenity.model.Users;
import com.serenity.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	public void CommonUser(Principal p,Model m) {
		
		if(p != null) {
			
			String email = p.getName();
			
			Users user = userRepository.findByEmail(email);
			
			m.addAttribute("user", user);
		}
		
		
	}
	
	@GetMapping("/profile")
	public String profile() {
		
		return "profile";
		
	}
	
	
	@GetMapping("/productListings")
	public String productListings() {
		
		return "product_listings";
		
	}
	
	
	@GetMapping("/productPage")
	public String productPage() {
		
		return "product_page";
		
	}
	
	
	@GetMapping("/cartPage")
	public String cartPage() {
		
		return "cart";
		
	}
	
	
	@GetMapping("/paymentPage")
	public String paymentPage() {
		
		return "payments";
		
	}
	
	
	
}
