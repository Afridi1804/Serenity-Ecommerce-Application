package com.serenity.service;

import java.util.List;

import com.serenity.exception.CategoryException;
import com.serenity.model.Category;
import com.serenity.model.Products;

public interface CategoryService {

	public Category addCategory(Category category) throws CategoryException;

//	public List<Products> getAllProductsByCategoryId(int catid) throws CategoryException;

	public Category updateCategoryById(int catId, Category category) throws CategoryException;

	public Category deleteCategoryById(int catId) throws CategoryException;

	public List<Category> getAllCategory() throws CategoryException;

}
