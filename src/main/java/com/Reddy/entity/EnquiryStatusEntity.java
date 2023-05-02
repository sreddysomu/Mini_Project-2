package com.Reddy.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EnquiryStatusEntity {

	
	@Id
	@GeneratedValue
	private Integer statusId;
	private String statusName;
	
}
