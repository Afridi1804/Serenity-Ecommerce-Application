package com.serenity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serenity.exception.CartException;
import com.serenity.exception.ProductsException;
import com.serenity.model.Cart;
import com.serenity.model.CartItem;
import com.serenity.model.Products;
import com.serenity.repository.CartRepository;
import com.serenity.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public Products addProductToCart(int pid, int quantity, int cid) throws ProductsException, CartException {
		Optional<Products> productOptional = productRepository.findById(pid);
		Optional<Cart> cartOptional = cartRepository.findById(cid);

		if (productOptional.isEmpty()) {
			throw new ProductsException("Product not found with id " + pid);
		}

		if (cartOptional.isEmpty()) {
			throw new CartException("Cart not found with id " + cid);
		}

		Products product = productOptional.get();
		Cart cart = cartOptional.get();

		if (!product.getIsAvailable()) {
			throw new ProductsException("Product not available with id " + pid);
		}

		// Check if the product already exists in the cart
		Optional<CartItem> existingCartItemOptional = cart.getCartItems().stream()
				.filter(item -> item.getProduct().getProductId() == pid).findFirst();

		if (existingCartItemOptional.isPresent()) {
			// If the product already exists in the cart, update the quantity
			CartItem existingCartItem = existingCartItemOptional.get();
			existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
		} else {
			// If the product doesn't exist in the cart, create a new CartItem and add it to
			// the cart
			CartItem newCartItem = new CartItem(product, quantity);
			cart.getCartItems().add(newCartItem);
		}

		// Update the cart total
		updateCartTotal(cart);

		// Save the updated cart
		cartRepository.save(cart);

		return product;
	}

	@Override
	public Cart updateProductQuantity(int pid, int quantity, int cid) throws ProductsException, CartException {
		Optional<Products> productOptional = productRepository.findById(pid);
		Optional<Cart> cartOptional = cartRepository.findById(cid);

		if (productOptional.isEmpty()) {
			throw new ProductsException("Product not found with id " + pid);
		}

		if (cartOptional.isEmpty()) {
			throw new CartException("Cart not found with id " + cid);
		}

		Products product = productOptional.get();
		Cart cart = cartOptional.get();

		// Check if the product exists in the cart
		Optional<CartItem> existingCartItemOptional = cart.getCartItems().stream()
				.filter(item -> item.getProduct().getProductId() == pid).findFirst();

		if (existingCartItemOptional.isEmpty()) {
			throw new ProductsException("Product not found in the cart");
		}

		// Update the product quantity
		CartItem existingCartItem = existingCartItemOptional.get();
		existingCartItem.setQuantity(quantity);

		// Update the cart total
		updateCartTotal(cart);

		// Save the updated cart
		cartRepository.save(cart);
		return cart;
	}

	@Override
	public String deleteProductFromCart(int pid, int cid) throws ProductsException, CartException {
		Optional<Products> productOptional = productRepository.findById(pid);
		Optional<Cart> cartOptional = cartRepository.findById(cid);

		if (productOptional.isEmpty()) {
			throw new ProductsException("Product not found with id " + pid);
		}

		if (cartOptional.isEmpty()) {
			throw new CartException("Cart not found with id " + cid);
		}

		Cart cart = cartOptional.get();
		Products product = productOptional.get();

		// Check if the product exists in the cart
		Optional<CartItem> existingCartItemOptional = cart.getCartItems().stream()
				.filter(item -> item.getProduct().getProductId() == pid).findFirst();

		if (existingCartItemOptional.isEmpty()) {
			throw new ProductsException("Product not found in the cart");
		}

		// Remove the product from the cart
		CartItem existingCartItem = existingCartItemOptional.get();
		cart.getCartItems().remove(existingCartItem);

		// Update the cart total
		updateCartTotal(cart);

		// Save the updated cart
		cartRepository.save(cart);
		return "Deleted successfully";
	}

	private void updateCartTotal(Cart cart) {
		double cartTotal = cart.getCartItems().stream()
				.mapToDouble(item -> item.getProduct().getSalePrice() * item.getQuantity()).sum();
		cart.setCartTotal(cartTotal);
	}

	@Override
	public Cart getCartById(int cid) throws CartException {

		Optional<Cart> cart = cartRepository.findById(cid);

		if (cart.isEmpty()) {
			throw new CartException("Cart doesn't exists for id " + cid);
		}

		return cart.get();
	}

}
