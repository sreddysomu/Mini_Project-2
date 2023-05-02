package com.Reddy.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reddy.entity.UserDetailsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDetailsEntity,Integer> {
   
	public UserDetailsEntity findByEmail(String email);
	
	public UserDetailsEntity findByEmailAndPassword(String email,String pwd);
}
