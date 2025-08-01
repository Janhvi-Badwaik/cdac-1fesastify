package com.project.feastify.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "carts")
@NoArgsConstructor
public class CartEntity extends BaseEntity {

    private Long userId;  // Refers to the user who owns the cart

    @ElementCollection
    @CollectionTable(
        name = "cart_items",
        joinColumns = @JoinColumn(name = "cart_id") // Link cart_items to CartEntity via cart_id
    )
    @MapKeyColumn(name = "food_id")     //  CHANGED: Maps key of the Map (food ID)
    @Column(name = "quantity")          // Maps value of the Map (quantity of that food)
    private Map<Long, Integer> items = new HashMap<>();

    public CartEntity(Long userId, Map<Long, Integer> items) {
        this.userId = userId;
        this.items = items;
    }
}
