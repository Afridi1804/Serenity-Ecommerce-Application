package com.serenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serenity.model.Payments;
@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer>{

}
