package com.Reddy.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.Reddy.Repositories.CourseRepo;
import com.Reddy.Repositories.EnqStatusRepo;
import com.Reddy.entity.CoursesEntity;
import com.Reddy.entity.EnquiryStatusEntity;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo statusRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
 
		courseRepo.deleteAll();
		
		CoursesEntity c1 = new CoursesEntity();
		c1.setCourseName("Java");
		
		CoursesEntity c2 = new CoursesEntity();
		c2.setCourseName("Python");
		
		CoursesEntity c3 = new CoursesEntity();
		c3.setCourseName(".Net");
		
		
		courseRepo.saveAll(Arrays.asList(c1,c2,c3));
		
		statusRepo.deleteAll();
		
		EnquiryStatusEntity s1 = new EnquiryStatusEntity();
		s1.setStatusName("New");
		
		EnquiryStatusEntity s2 = new EnquiryStatusEntity();
		s2.setStatusName("Enrolled");
		
		EnquiryStatusEntity s3 = new EnquiryStatusEntity();
		s3.setStatusName("Lost");
		
		statusRepo.saveAll(Arrays.asList(s1,s2,s3));
	}

}
