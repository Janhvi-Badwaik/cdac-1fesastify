package com.project.feastify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FoodItemRespDTO extends BaseDTO {
	
	private String name;
	private String description;
	@JsonProperty("imageUrl")
	private String imageURL;
	private double price;
	private String category;

}
