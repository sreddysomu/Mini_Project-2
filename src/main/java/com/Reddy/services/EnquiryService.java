package com.Reddy.services;

import java.util.List;

import com.Reddy.Binding.DashboardResponse;
import com.Reddy.Binding.EnquiryForm;
import com.Reddy.Binding.EnquirySearchCriteria;
import com.Reddy.entity.CoursesEntity;
import com.Reddy.entity.EnquiryStatusEntity;
import com.Reddy.entity.StudentEnqEntity;

public interface EnquiryService {
	
	

	public DashboardResponse getDashboardData(Integer userId);
	
	public List<String> getCourseNames();
	
	public List<String> getEnqStatuses();
	
	public boolean saveEnquiry (EnquiryForm form);

	
}
