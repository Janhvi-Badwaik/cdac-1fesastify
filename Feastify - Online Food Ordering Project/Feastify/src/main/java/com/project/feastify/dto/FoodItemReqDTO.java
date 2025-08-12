package com.project.feastify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty("isVeg")
	private boolean veg; 
}
