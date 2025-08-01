package com.project.feastify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.feastify.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
	Optional<UserEntity> findByEmail(String email);
} 
