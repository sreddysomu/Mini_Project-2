package com.Reddy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class UserDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@NotNull(message="Name is mandatory")
	private String name;
	@Column(name = "Email", unique= true)
	@NotNull(message="Email is mandatory")
	private String email;	
	//@Column(name="Phone_Number")
	//@NotNull
	//@Size(min=10,max=10)
	private Long phno;
	//@Column(name="Password")
	private String password;
	//@Column(name = "Account_Status")
	private String accountStatus;
   @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="user")
	private List<StudentEnqEntity> studentEnq;
	
	
	
	
}