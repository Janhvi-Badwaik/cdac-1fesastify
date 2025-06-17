package com.project.feastify.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@ToString

public class FoodItemReqDTO {
	
	private String name;
	private String description;
	private double price;
	private String category;
	

}
