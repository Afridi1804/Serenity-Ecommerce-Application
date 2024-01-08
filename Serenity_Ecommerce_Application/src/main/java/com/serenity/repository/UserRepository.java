package com.serenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serenity.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	public Users findByEmail(String email);
	
}
