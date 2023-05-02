package com.Reddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Reddy.Binding.LoginForm;
import com.Reddy.Binding.SignUpForm;
import com.Reddy.Binding.UnlockForm;
import com.Reddy.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/signup")
	public String loadCreate(Model model) {
		model.addAttribute("user",new SignUpForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form,Model model) {
		
		boolean status = service.SignUp(form);
		if(status) {
			model.addAttribute("smsg", "please check your email");	
		}else {
			model.addAttribute("errmsg","Choose Unique Email");
		}
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String loadUnlock(@RequestParam String email,Model model) {
		UnlockForm unlockFormObj = new UnlockForm();
		unlockFormObj.setEmail(email);
		
		model.addAttribute("unlock", unlockFormObj);
		return"unlock";
	}
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock,Model model) {
		
		if(unlock.getNewPassword().equals(unlock.getConfirmPassword())) {
			boolean status = service.unlockAccount(unlock);
			
		if(status) {
				model.addAttribute("succmsg", "Your account unlocked");

		}else {
				model.addAttribute("errmsg", "Given temporary password is incorrect,check your email");
			}
			
		}else {
		model.addAttribute("errmsg", "New pwd and confirm pwd should be same");
		}
		return "unlock";
	}
	
	@GetMapping("/login")
	public String loadLogin(Model model) {
		
		model.addAttribute("loginForm",new LoginForm());
		
		return "login";		
	}
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm ,Model model) {
	
		String status = service.login(loginForm);
		
		if(status.contains("success")) {
			
			return "redirect:/dashboard";
		}
		model.addAttribute("errmsg",status);
		
		return "login";
	}
	
	
	@GetMapping("/forgot")
	public String forgotPwdPage(Model model) {
		return "forgotPwd";
		
	}
	

	@PostMapping("/forgotPwd")
	public String forgotPwd(@RequestParam("email") String email, Model model) {
		
		System.out.println(email);
		
		boolean status = service.forgotPwd(email);
		
        if(status) {
        	//send success msg
        	model.addAttribute("succMsg", "Pwd sent to your email");
        }else {
        	//send error msg
        	model.addAttribute("errMsg", "Invalid Email");
        }


		return "forgotPwd";
		
	}
	
	
}	
