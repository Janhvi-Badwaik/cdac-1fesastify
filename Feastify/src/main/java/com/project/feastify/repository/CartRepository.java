package com.project.feastify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.feastify.entities.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>
{
	Optional<CartEntity> findByUserId(Long Id);
	
	void deleteById(Long Id);
	
}
