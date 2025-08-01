package com.project.feastify.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.project.feastify.custom_exceptions.ResourceNotFoundException;
import com.project.feastify.dto.FoodItemReqDTO;
import com.project.feastify.dto.FoodItemRespDTO;
import com.project.feastify.entities.FoodItem;
import com.project.feastify.repository.FoodItemRepository;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
@Transactional
//@AllArgsConstructor

public class FoodItemServiceImpl implements FoodItemService {
	
	@Autowired
	private  S3Client s3Client;

	@Autowired
	private  FoodItemRepository foodItemRepository;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	@Value("${aws.s3.bucketname}")
	private  String bucketName;
	
	/*public FoodItemServiceImpl(
	        S3Client s3Client,
	        @Value("${aws.s3.bucketname}") String bucketName,
	        FoodItemRepository foodItemRepository,
	        ModelMapper modelMapper
	    ) {
	        this.s3Client = s3Client;
	        this.bucketName = bucketName;
	        this.foodItemRepository = foodItemRepository;
	        this.modelMapper = modelMapper;
	    }*/


	@Override
	public String uploadFile(MultipartFile file) {
	String filenameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
	String key = UUID.randomUUID().toString()+"."+filenameExtension;
	try {
		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName)
				.key(key)
				.acl("public-read")
				.contentType(file.getContentType())
				.build();
		
		PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
				if (response.sdkHttpResponse().isSuccessful()) 
		        {
		            return "https://"+bucketName+".s3.amazonaws.com/"+key;
		        } else 
		        {
		            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
		        }
		        
}
		catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while uploading the file");
		}
		 
	
	//return null;
	}

	@Override
	public FoodItemRespDTO addFood(FoodItemReqDTO request, MultipartFile file) {
		
		FoodItem  foodItem = modelMapper.map(request, FoodItem.class);
		String imageUrl= uploadFile(file);
		foodItem.setImageURL(imageUrl);
		foodItem =  foodItemRepository.save(foodItem);
		return modelMapper.map(foodItem, FoodItemRespDTO.class);
		
		
		//return null;
	}

	@Override
	public FoodItemRespDTO getFood(Long foodId) {
		FoodItem entity = foodItemRepository.findById(foodId)
				.orElseThrow(()-> new ResourceNotFoundException("This food Item is not Available"));
		
		return modelMapper.map(entity, FoodItemRespDTO.class);
	}

	/*@Override
	public List<FoodItemRespDTO> getFoods() {
		return foodItemRepository.findAll()
				.stream()
				.map(foodItem -> modelMapper.map(foodItem, FoodItemRespDTO.class))
				.collect(Collectors.toList());
		
	}*/
	@Override
	public List<FoodItemRespDTO> getFoods() {
	    List<FoodItem> all = foodItemRepository.findAll();
	    System.out.println("Foods in DB: " + all.size());
	    all.forEach(f -> System.out.println(f.getName() + " - " + f.getImageURL()));
	    
	    return all.stream()
	        .map(foodItem -> modelMapper.map(foodItem, FoodItemRespDTO.class))
	        .collect(Collectors.toList());
	}

	
	
}

























