package com.project.feastify.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    //private Long foodId;
	@OneToOne
    private FoodItem food;
    private int quantity;
    private double price;
    private String category;
    private String imageUrl;
    private String description;
    private String name;
}
