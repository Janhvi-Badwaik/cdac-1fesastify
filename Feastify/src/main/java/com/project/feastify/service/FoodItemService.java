package com.project.feastify.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.feastify.dto.FoodItemReqDTO;
import com.project.feastify.dto.FoodItemRespDTO;

public interface FoodItemService {
 String	uploadFile(MultipartFile file);
 
 FoodItemRespDTO addFood(FoodItemReqDTO request, MultipartFile file);
 
 List<FoodItemRespDTO> getFoods();

 FoodItemRespDTO getFood(Long foodId);
 
 boolean deleteFile(String filename);
 
 void deleteFood(Long id);
}
