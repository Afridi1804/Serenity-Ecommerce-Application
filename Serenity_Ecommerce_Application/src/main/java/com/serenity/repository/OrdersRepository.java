package com.serenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serenity.model.Orders;
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}
