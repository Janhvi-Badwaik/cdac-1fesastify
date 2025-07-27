package com.project.feastify.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.feastify.entities.Cart;
import com.project.feastify.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService 
{
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void addToCart(Long foodId) 
	{
	    Long loggedInUserId = userService.findById();  // Assuming this returns Long

	    Optional<Cart> cartOptional = cartRepository.findById(loggedInUserId);
	    Cart cart = cartOptional.orElseGet(() -> new Cart(loggedInUserId, new HashMap<>()));

	    Map<Long, Integer> cartItems = cart.getItems();
	    cartItems.put(foodId, cartItems.getOrDefault(foodId, 0) + 1);

	    cart.setItems(cartItems);
	    cartRepository.save(cart);
	}

}
