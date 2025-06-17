package com.project.feastify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
	private String mesg;

	public ApiResponse(String mesg) {
		this.mesg = mesg;
		}

}
