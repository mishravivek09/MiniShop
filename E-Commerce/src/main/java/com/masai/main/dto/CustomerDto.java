package com.masai.main.dto;
import javax.validation.constraints.*;
import com.masai.main.entity.Address;
import com.masai.main.myenum.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	@Size(min=3,max=20,message = "Name should be between 3 to 20 characters")
	private String name;
	@Email(message = "Please provide email address")
	private String email;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message =
			"At least one upper case and one lower case English letter, At least one special characters and At least one digit ,should be minimum 8 in length ")
	private String password;
	private Gender gender;
	@NotNull(message = "Address should not be null")
	private Address address;
}
