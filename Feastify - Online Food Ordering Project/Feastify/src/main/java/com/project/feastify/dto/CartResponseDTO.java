package com.project.feastify.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDTO 
{
	private Long id;
	private Long userId;

	private Map<Long, Integer> items = new HashMap<>();
}
