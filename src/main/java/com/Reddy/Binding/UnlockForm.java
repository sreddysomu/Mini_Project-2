package com.Reddy.Binding;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UnlockForm {

	private String email;
	@NotBlank(message="this field should not be empty")
	private String tempPassword;
	@NotBlank(message="this field should not be empty")
	private String newPassword;
	@NotBlank(message="this field should not be empty")
	private String confirmPassword;
}
