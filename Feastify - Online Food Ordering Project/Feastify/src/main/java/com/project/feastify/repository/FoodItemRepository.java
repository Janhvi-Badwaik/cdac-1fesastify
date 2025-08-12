package com.project.feastify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.feastify.entities.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}
