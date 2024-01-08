package com.serenity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.serenity.model.Users;
import com.serenity.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepository.findByEmail(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException("User not found");
			
		}else {
			
			return new CustomUser(user);
			
		}
		

	}

}
