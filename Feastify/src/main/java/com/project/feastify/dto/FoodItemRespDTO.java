package com.project.feastify.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FoodItemRespDTO extends BaseDTO {
	
	private String name;
	private String description;
	private String imageURL;
	private double price;
	private String category;

}
