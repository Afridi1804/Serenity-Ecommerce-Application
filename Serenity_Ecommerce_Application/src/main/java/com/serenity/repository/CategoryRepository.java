package com.serenity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serenity.model.Category;
import com.serenity.model.Products;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

//	@Query("SELECT p FROM Products p WHERE p.category = ?1")
//	List<Products> findAllProductsByCategoryId(int catid);
	
	
}
