package com.masai.main.dto;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	@Email(message = "Please provide email address")
	private String email;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message =
			"At least one upper case and one lower case English letter, At least one special characters and At least one digit ,should be minimum 8 in length ")
	private String password;
}
