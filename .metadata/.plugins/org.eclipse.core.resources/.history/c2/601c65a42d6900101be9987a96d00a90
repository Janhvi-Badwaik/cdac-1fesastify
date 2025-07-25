package com.project.feastify.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.feastify.dto.FoodItemRespDTO;
import com.project.feastify.dto.UserRequestDTO;
import com.project.feastify.dto.UserResponseDTO;
import com.project.feastify.entities.FoodItem;
import com.project.feastify.entities.User;
import com.project.feastify.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	@Override
	public UserResponseDTO registerUser(UserRequestDTO request) 
	{
		User user = modelMapper.map(request, User.class);
		user = userRepository.save(user);
		return modelMapper.map(user, UserResponseDTO.class);
	}
}
//
//FoodItem  foodItem = modelMapper.map(request, FoodItem.class);
//String imageUrl= uploadFile(file);
//foodItem.setImageURL(imageUrl);
//foodItem =  foodItemRepository.save(foodItem);
//return modelMapper.map(foodItem, FoodItemRespDTO.class);
