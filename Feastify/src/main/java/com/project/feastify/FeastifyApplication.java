package com.project.feastify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FeastifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeastifyApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() 
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT) .setPropertyCondition(Conditions.isNotNull());
		return mapper;
	}


}
