package com.project.feastify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem extends BaseDTO
{
	private Long foodId;
	private int quantity;
	private double price;
	private String category;
	private String imageUrl;
	private String description;
	private String name;
}
