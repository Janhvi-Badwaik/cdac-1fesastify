package com.project.feastify.service;

import com.project.feastify.dto.CartRequestDTO;
import com.project.feastify.dto.CartResponseDTO;

public interface CartService 
{
	CartResponseDTO addToCart(CartRequestDTO request);
}
