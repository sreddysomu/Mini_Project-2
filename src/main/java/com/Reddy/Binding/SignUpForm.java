package com.Reddy.Binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message="Name should not be empty")
	private String name;
	@NotBlank(message="email should not be null")
	private String email;
	@NotNull(message="phone number is mandatory")
	@Positive(message="Invalid phone number")
	//@Size(min=10,max=13,message="Invalid phone number")
	private Long phno;
}
