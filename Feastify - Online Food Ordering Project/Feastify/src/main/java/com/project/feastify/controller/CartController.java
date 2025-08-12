package com.project.feastify.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.project.feastify.dto.CartRequestDTO;
import com.project.feastify.dto.CartResponseDTO;
import com.project.feastify.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController 
{
	@Autowired
	private CartService cartService;
	
	@PostMapping
    public CartResponseDTO addToCart(@RequestBody CartRequestDTO request) 
	{
        Long foodId = request.getFoodId();
        if (foodId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "foodId not found");
        }
        return cartService.addToCart(request);
    }
	
	@GetMapping
	public CartResponseDTO getCart()
	{
		return cartService.getCart();
	}
	
	@DeleteMapping("/clear")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void clearCart()
	{
		cartService.clearCart();
	}
	
	@PostMapping("/remove")
	public CartResponseDTO removeFromCart(@RequestBody CartRequestDTO request)
	{
		Long foodId = request.getFoodId();
		if (foodId == null) 
		{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "foodId not found");
        }
		return cartService.removeFromCart(request);
	}

}
