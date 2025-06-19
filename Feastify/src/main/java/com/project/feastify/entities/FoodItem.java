package com.project.feastify.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="food_items")
//lombok  annotations
@NoArgsConstructor
@Getter
@Setter
@ToString

public class FoodItem extends BaseEntity {
	
	@Column(name="fooditem_name",unique = true,length = 100)
	private String name;
	@Column(name="description")
	private String description;
	private String imageURL;
	private double price;
	private String category;
	@Column(name="is_veg")
	private Boolean isVeg;


}
