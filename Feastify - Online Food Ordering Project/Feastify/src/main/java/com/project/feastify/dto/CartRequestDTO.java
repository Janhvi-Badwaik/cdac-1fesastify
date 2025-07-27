package com.project.feastify.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO 
{
	private String userId;
	
	private Map<String, Integer> items = new HashMap<>();
}
