package com.project.feastify.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.feastify.dto.CartRequestDTO;
import com.project.feastify.dto.CartResponseDTO;

import com.project.feastify.entities.CartEntity;
import com.project.feastify.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService 
{
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	

	
	@Override
	public CartResponseDTO addToCart(CartRequestDTO request) 
	{
	    Long loggedInUserId = userService.findByUserId();  // Assuming this returns Long

	    Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
	    CartEntity cart = cartOptional.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));

	    Map<Long, Integer> cartItems = cart.getItems();
	    cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId(), 0) + 1);

	    cart.setItems(cartItems);
	    cart = cartRepository.save(cart);
	    return convertToResponse(cart);
	}
	
	private CartResponseDTO convertToResponse(CartEntity cartEntity) {
        return CartResponseDTO.builder()
                .id(cartEntity.getId()).userId(cartEntity.getUserId())
                .items(cartEntity.getItems())
                .build();
    }

}
