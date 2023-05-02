package com.Reddy.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reddy.Binding.LoginForm;
import com.Reddy.Binding.SignUpForm;
import com.Reddy.Binding.UnlockForm;
import com.Reddy.Repositories.UserDtlsRepo;
import com.Reddy.entity.UserDetailsEntity;
import com.Reddy.utils.EmailUtils;
import com.Reddy.utils.PwdUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDtlsRepo repo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession session;

	@Override
	public String login(LoginForm form) {
		
	UserDetailsEntity entity = repo.findByEmailAndPassword(form.getEmail(),form.getPassword());
		
		if(entity == null) {
			return "Invalid Credentials";
		}
		if(entity.getAccountStatus().equals("LOCKED")) {
			return "Your account Locked";
		}
		
		session.setAttribute("userId", entity.getUserId());

		return "success";
	}
	
      @Override
      public boolean unlockAccount(UnlockForm form) {
    	  
    	  UserDetailsEntity entity = repo.findByEmail(form.getEmail());
    	 if(entity.getPassword().equals(form.getTempPassword())) {
    		 
    		 entity.setPassword(form.getNewPassword());
    		 entity.setAccountStatus("UNLOCKED");
    		 repo.save(entity);
    		 return true;
    		 
    	 }else {
    		 return false;
    	 }
      }
	
	@Override
	public boolean SignUp(SignUpForm form) {
		
		UserDetailsEntity user = repo.findByEmail(form.getEmail());
		
		if(user!=null) {
			return false;
		}
		
		UserDetailsEntity entity = new UserDetailsEntity();
		BeanUtils.copyProperties(form,entity);
		
		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPassword(tempPwd);
		
		entity.setAccountStatus("LOCKED");
		
		repo.save(entity);
		
		String to = form.getEmail();
		String subject="Unlock your Account";
		
		StringBuffer body = new StringBuffer("");
		body.append("<h1>Use below temporary password to unclock your account</h1>");
		body.append("Temporary pwd " + tempPwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:9090/unlock?email="+to+"\">Click Here To Unclok Your Account<a>");
		
		
		emailUtils.sendEmail(to, subject, body.toString());
		
		return true;
	}

	@Override
	public boolean forgotPwd(String email) {
		
		// db with given email
		UserDetailsEntity entity = repo.findByEmail(email);
		
		//return false
		if(entity == null) {
		    return false;
		    
		}
		
		//return true
		String Subject = "Recover Password";
		String body = "Your Pwd ::"+entity.getPassword();
		
		emailUtils.sendEmail(email, Subject, body);
		
		return true;
	}

	
	
	


	
	
	

}
