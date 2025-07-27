package com.project.feastify.entities;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "carts")
public class Cart extends BaseEntity 
{
    private Long userId;  // Change from String to Long

    @ElementCollection
    @CollectionTable(
        name = "cart_items",
        joinColumns = @JoinColumn(name = "cart_id")
    )
    @MapKeyColumn(name = "product_id")      // This maps the key (Long)
    @Column(name = "quantity")              // This maps the value (Integer)
    private Map<Long, Integer> items = new HashMap<>();

    public Cart(Long userId, Map<Long, Integer> items) 
    {
        this.userId = userId;
        this.items = items;
    }
}
