//package com.project.feastify.service;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.feastify.dto.CartRequestDTO;
//import com.project.feastify.dto.CartResponseDTO;
//import com.project.feastify.entities.CartEntity;
//import com.project.feastify.repository.CartRepository;
//
//@Service
//public class CartServiceImpl implements CartService 
//{
//	@Autowired
//	private CartRepository cartRepository;
//	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private ModelMapper modelMapper;
//		
//	@Override
//	public CartResponseDTO addToCart(CartRequestDTO request) 
//	{
//	    Long loggedInUserId = userService.findByUserId();  // Assuming this returns Long
//
//	    Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
//	    CartEntity cart = cartOptional.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));
//
//	    Map<Long, Integer> cartItems = cart.getItems();
//	    cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId(), 0) + 1);
//
//	    cart.setItems(cartItems);
//	    cart = cartRepository.save(cart);
//	    return convertToResponse(cart);
//	}
//	
//	//change using modelMapper
//	private CartResponseDTO convertToResponse(CartEntity cartEntity) {
//        return CartResponseDTO.builder()
//                .id(cartEntity.getId()).userId(cartEntity.getUserId())
//                .items(cartEntity.getItems())
//                .build();
//    }
//	
//	@Override
//	public CartResponseDTO getCart() 
//	{
//	    Long loggedInUserId = userService.findByUserId();
//
//	    CartEntity cart = cartRepository.findByUserId(loggedInUserId)
//	                         .orElse(new CartEntity(loggedInUserId, new HashMap<>()));
//
//	    return modelMapper.map(cart, CartResponseDTO.class);
//	}
//
//	@Override
//	public void clearCart() 
//	{
//		Long loggedInUserId = userService.findByUserId();
//		cartRepository.deleteById(loggedInUserId);
//	}
//	
//	@Override
//	public CartResponseDTO removeFromCart(CartRequestDTO cartRequest) 
//	{
//	    Long loggedInUserId = userService.findByUserId();
//
//	    CartEntity cart = cartRepository.findByUserId(loggedInUserId)
//	            .orElseThrow(() -> new RuntimeException("Cart is not found"));
//
//	    Map<Long, Integer> cartItems = cart.getItems();
//
//	    if (cartItems.containsKey(cartRequest.getFoodId())) 
//	    {
//	        int currentQty = cartItems.get(cartRequest.getFoodId());
//
//	        if (currentQty > 1) 
//	        {
//	            cartItems.put(cartRequest.getFoodId(), currentQty - 1);
//	        } else 
//	        {
//	            cartItems.remove(cartRequest.getFoodId());
//	        }
//
//	        cart.setItems(cartItems); 
//	        cart = cartRepository.save(cart); 
//	    }
//	    return modelMapper.map(cart, CartResponseDTO.class);
//	}
//
//
//
//}


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
import com.project.feastify.entities.UserEntity;
import com.project.feastify.repository.CartRepository;
import com.project.feastify.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartResponseDTO addToCart(CartRequestDTO request) {
        Long loggedInUserId = userService.findByUserId();
        UserEntity user = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
        CartEntity cart = cartOptional.orElseGet(() -> new CartEntity(user, new HashMap<>()));

        Map<Long, Integer> cartItems = cart.getItems();
        cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId(), 0) + 1);

        cart.setItems(cartItems);
        cart.setUser(user); // in case it's newly created
        cart = cartRepository.save(cart);

        return convertToResponse(cart);
    }

    private CartResponseDTO convertToResponse(CartEntity cartEntity) {
        return CartResponseDTO.builder()
                .id(cartEntity.getId())
                .userId(cartEntity.getUser().getId()) // fixed: getUserId() → getUser().getId()
                .items(cartEntity.getItems())
                .build();
    }

    @Override
    public CartResponseDTO getCart() {
        Long loggedInUserId = userService.findByUserId();
        UserEntity user = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartEntity cart = cartRepository.findByUserId(loggedInUserId)
                .orElse(new CartEntity(user, new HashMap<>()));

        return convertToResponse(cart);
    }

    @Override
    public void clearCart() {
        Long loggedInUserId = userService.findByUserId();
        Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
        cartOptional.ifPresent(cart -> cartRepository.deleteById(cart.getId()));
    }

    @Override
    public CartResponseDTO removeFromCart(CartRequestDTO cartRequest) {
        Long loggedInUserId = userService.findByUserId();

        CartEntity cart = cartRepository.findByUserId(loggedInUserId)
                .orElseThrow(() -> new RuntimeException("Cart is not found"));

        Map<Long, Integer> cartItems = cart.getItems();

        if (cartItems.containsKey(cartRequest.getFoodId())) {
            int currentQty = cartItems.get(cartRequest.getFoodId());

            if (currentQty > 1) {
                cartItems.put(cartRequest.getFoodId(), currentQty - 1);
            } else {
                cartItems.remove(cartRequest.getFoodId());
            }

            cart.setItems(cartItems);
            cart = cartRepository.save(cart);
        }

        return convertToResponse(cart);
    }
}
