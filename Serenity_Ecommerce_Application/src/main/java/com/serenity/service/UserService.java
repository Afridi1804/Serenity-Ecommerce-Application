package com.serenity.service;

import com.serenity.model.Users;

public interface UserService {

	public Users registerUser(Users user);
	
	public void removeSessionMessage();
	
}
