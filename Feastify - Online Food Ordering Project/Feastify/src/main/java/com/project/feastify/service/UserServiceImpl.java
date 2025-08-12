package com.project.feastify.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.feastify.dto.UserRequestDTO;
import com.project.feastify.dto.UserResponseDTO;
import com.project.feastify.entities.UserEntity;
import com.project.feastify.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	@Autowired
	private AuthenticationFacade authenticationFacade;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserResponseDTO registerUser(UserRequestDTO request) 
	{
		UserEntity user = modelMapper.map(request, UserEntity.class);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user = userRepository.save(user);
		return modelMapper.map(user, UserResponseDTO.class);
	}

	@Override
	public Long findByUserId() 
	{
		String loggedInUserEmail = authenticationFacade.getAuthentication().getName();
		UserEntity loggedInUser = userRepository.findByEmail(loggedInUserEmail).orElseThrow(()-> new UsernameNotFoundException("user not found"));
		return loggedInUser.getId();
	}
}
//
//FoodItem  foodItem = modelMapper.map(request, FoodItem.class);
//String imageUrl= uploadFile(file);
//foodItem.setImageURL(imageUrl);
//foodItem =  foodItemRepository.save(foodItem);
//return modelMapper.map(foodItem, FoodItemRespDTO.class);
