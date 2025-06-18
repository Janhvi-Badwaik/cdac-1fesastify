package com.project.feastify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.feastify.dto.FoodItemReqDTO;
import com.project.feastify.dto.FoodItemRespDTO;
import com.project.feastify.service.FoodItemService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/food_items")
@AllArgsConstructor
public class FoodItemController {
	//@Autowired
	private final FoodItemService foodItemService;
	
	@PostMapping
	public FoodItemRespDTO addFood(@RequestPart("food") String food ,@RequestPart("file") MultipartFile file ){
		
		ObjectMapper objectMapper = new ObjectMapper();
		FoodItemReqDTO request = null;
		try {
			request = objectMapper.readValue(food, FoodItemReqDTO.class);
		}
		catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid JSON format");
		}
		FoodItemRespDTO respose= foodItemService.addFood(request, file);
		
		return respose;
		
		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllFoodItems(){
		List<FoodItemRespDTO> foods = foodItemService.getFoods();
		if(foods.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(foods);
				}
	
	@GetMapping("/{foodid}")
	public ResponseEntity<?> getFoodById(Long id){
		return ResponseEntity.ok(foodItemService.getFood(id));
	}
	
	
	
	
	/*public ResponseEntity<?> addFoodItem (@Req)*/
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFood(@PathVariable Long id) {
		foodItemService.deleteFood(id);
	}

}
