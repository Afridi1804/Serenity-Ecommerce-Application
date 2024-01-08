package com.serenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serenity.model.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
