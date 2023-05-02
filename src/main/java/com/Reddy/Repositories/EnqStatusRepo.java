package com.Reddy.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reddy.entity.EnquiryStatusEntity;

public interface EnqStatusRepo extends JpaRepository<EnquiryStatusEntity, Integer>{

}
