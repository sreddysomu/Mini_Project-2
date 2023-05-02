package com.Reddy.services;

import com.Reddy.Binding.LoginForm;
import com.Reddy.Binding.SignUpForm;
import com.Reddy.Binding.UnlockForm;
import com.Reddy.entity.UserDetailsEntity;

public interface UserService {

	
	public boolean SignUp(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public String login(LoginForm form);
	
	public boolean forgotPwd(String email);
	
	
}
