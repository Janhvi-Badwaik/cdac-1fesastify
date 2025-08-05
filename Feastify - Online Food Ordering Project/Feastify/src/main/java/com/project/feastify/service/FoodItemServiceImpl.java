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

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
@Transactional
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${aws.s3.bucketname}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        String filenameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String key = UUID.randomUUID().toString() + "." + filenameExtension;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl("public-read")
                    .contentType(file.getContentType())
                    .build();

            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            if (response.sdkHttpResponse().isSuccessful()) {
                return "https://" + bucketName + ".s3.amazonaws.com/" + key;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
            }

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while uploading the file");
        }
    }

    @Override
    public FoodItemRespDTO addFood(FoodItemReqDTO request, MultipartFile file) {
        FoodItem foodItem = modelMapper.map(request, FoodItem.class);
        String imageUrl = uploadFile(file);
        foodItem.setImageURL(imageUrl);
        foodItem = foodItemRepository.save(foodItem);
        return modelMapper.map(foodItem, FoodItemRespDTO.class);
    }

    @Override
    public FoodItemRespDTO getFood(Long foodId) {
        FoodItem entity = foodItemRepository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("This food item is not available"));
        return modelMapper.map(entity, FoodItemRespDTO.class);
    }

    @Override
    public List<FoodItemRespDTO> getFoods() {
        List<FoodItem> all = foodItemRepository.findAll();
        return all.stream()
                .map(foodItem -> modelMapper.map(foodItem, FoodItemRespDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteFile(String filename) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
        return true;
    
	}

	@Override
	public void deleteFood(Long id) {
		// TODO Auto-generated method stub
		FoodItemRespDTO response = getFood(id);
		String imageUrl = response.getImageURL();
		String filename = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
		boolean isFileDelete = deleteFile(filename);
		if(isFileDelete) {
			foodItemRepository.deleteById(response.getId());
		}
		
		
	}
}

