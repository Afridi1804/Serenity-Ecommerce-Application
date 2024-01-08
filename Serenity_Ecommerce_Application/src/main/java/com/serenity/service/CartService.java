package com.serenity.service;

import com.serenity.exception.CartException;
import com.serenity.exception.ProductsException;
import com.serenity.model.Cart;
import com.serenity.model.Products;

public interface CartService {

	public Products addProductToCart(int pid, int quantity, int cid) throws ProductsException, CartException;

	public Cart updateProductQuantity(int newquantity, int pid, int cid) throws ProductsException, CartException;

	public String deleteProductFromCart(int pid, int cid) throws ProductsException, CartException;

	public Cart getCartById(int cid) throws CartException;

}
