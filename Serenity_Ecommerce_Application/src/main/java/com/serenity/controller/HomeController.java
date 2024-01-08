package com.serenity.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.serenity.model.Users;
import com.serenity.repository.UserRepository;
import com.serenity.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
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
	
	
	@GetMapping("/")
	public String index() {
		
		return "index";
		
	}
	
	@GetMapping("/register")
	public String register() {
		
		return "register";
		
	}
	
	@GetMapping("/signin")
	public String login() {
		
		return "login";
		
	}
	
//	@GetMapping("/user/profile")
//	public String profile(Principal p,Model m) {
//		
//		String email = p.getName();
//		
//		Users user = userRepository.findByEmail(email);
//		
//		m.addAttribute("user", user);
//		
//		return "profile";
//		
//	}
	
	@GetMapping("/user/home")
	public String home() {
		
		return "home";
		
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute Users user,HttpSession session) {
		
		System.out.println(user);
		
		Users usr = userService.registerUser(user);
		
		if(usr!=null) {
			
			session.setAttribute("msg", "Register Successfully");
			
		}else {
			
			session.setAttribute("msg", "Something went wrong");
			
		}
		
		return "redirect:/register";
		
	}
	
}
