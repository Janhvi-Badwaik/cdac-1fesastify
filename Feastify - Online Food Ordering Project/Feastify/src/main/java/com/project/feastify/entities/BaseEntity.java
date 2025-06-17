package com.project.feastify.entities;

//import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass // below class will NOT have any table generation, sub class entities will
// inherit these common members
@Getter
@Setter
@ToString

public class BaseEntity {
	@Id // mandatory , PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
