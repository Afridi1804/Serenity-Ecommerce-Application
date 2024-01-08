package com.serenity.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.serenity.exception.ProductsException;
import com.serenity.model.Products;

public interface ProductsService {

	public Products addProduct(Products product) throws ProductsException;

	public Products getProductById(int prodId) throws ProductsException;

	public List<Products> getAllProducts() throws ProductsException;

	public Products updateProduct(int prodId, Products products) throws ProductsException;

	public Products deleteProduct(int prodId) throws ProductsException;

//    Custom methods

	public List<Products> searchByName(String productName) throws ProductsException;

	public List<Products> searchByBrand(String productBrand) throws ProductsException;

	public List<Products> searchByType(String productType) throws ProductsException;

	public List<Products> searchByRating(double rating) throws ProductsException;

	public List<Products> searchByisAvailable(boolean isAvailable) throws ProductsException;
	
	public List<Products> searchByPriceRangeAndType(String productType, double startingPrice, double endingPrice) throws ProductsException;

	public List<Products> searchByDiscountPercentageRangeAndType(String productType, double startingDiscount, double endingDiscount) throws ProductsException;
	

// 	Pagination and sorting methods

	public List<Products> getSortedProductsListWithField(String field, String direction);

	public List<Products> getSortedProductsTypeListWithField(String field, String direction, String Type);

	public List<Products> getProductsTypeListPageWise(Integer pageNumber, Integer NumberofRecords, String Type);

	public List<Products> getSortedProductsTypeListPageWise(Integer pageNumber, Integer NumberofRecords, String Type,
			String direction, String field);

	public List<Products> filterByTypeAndBrand(String type, String brand, Integer pageNumber, Integer numberOfRecords)
			throws ProductsException;
}
