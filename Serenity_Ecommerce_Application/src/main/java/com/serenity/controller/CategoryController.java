package com.serenity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serenity.exception.AddressException;
import com.serenity.exception.CategoryException;
import com.serenity.exception.CustomersException;
import com.serenity.model.Address;
import com.serenity.model.Cart;
import com.serenity.model.Category;
import com.serenity.model.Products;
import com.serenity.service.AddressService;
import com.serenity.service.CategoryService;

@RestController
@RequestMapping("/Category")
public class CategoryController {

	@Autowired
	public CategoryService categoryService;

	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) throws CategoryException {
		Category cat = categoryService.addCategory(category);
		return new ResponseEntity<>(cat, HttpStatus.CREATED);
	}

//	@GetMapping("/getAllProductsByCategoryId/{catid}")
//	public ResponseEntity<List<Products>> getAllProductsByCategoryId(@PathVariable int catid) throws CategoryException {
//
//		List<Products> list = categoryService.getAllProductsByCategoryId(catid);
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}

	@PutMapping("/updateCategoryById/{catId}")
	public ResponseEntity<Category> updateCategoryById(@PathVariable int catId, @RequestBody Category category)
			throws CategoryException {
		Category cat = categoryService.updateCategoryById(catId, category);
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCategoryById/{catId}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable int catId) throws CategoryException {
		Category cat = categoryService.deleteCategoryById(catId);
		return new ResponseEntity<>(cat, HttpStatus.OK);

	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategory() throws CategoryException {
		List<Category> list = categoryService.getAllCategory();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
