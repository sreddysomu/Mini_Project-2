package com.Reddy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reddy.Binding.DashboardResponse;
import com.Reddy.Binding.EnquiryForm;
import com.Reddy.Repositories.CourseRepo;
import com.Reddy.Repositories.EnqStatusRepo;
import com.Reddy.Repositories.UserDtlsRepo;
import com.Reddy.entity.CoursesEntity;
import com.Reddy.entity.EnquiryStatusEntity;
import com.Reddy.entity.StudentEnqEntity;
import com.Reddy.entity.UserDetailsEntity;

@Service
public class EnquiryServiceImpl implements EnquiryService {


	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo statusRepo;

	@Override
	public DashboardResponse getDashboardData(Integer userId) {

		DashboardResponse response =  new DashboardResponse();

		Optional<UserDetailsEntity> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDetailsEntity userEntity = findById.get();
			List<StudentEnqEntity> enquiries = userEntity.getStudentEnq();

			//total count
			Integer totalCnt = enquiries.size();

			//enrolled count
			Integer enrolledCnt = enquiries.stream()
					.filter(e ->e.getEnquiryStatus().equals("Enrolled"))
					.collect(Collectors.toList()).size();

			//lost count
			Integer lostCnt = enquiries.stream()
					.filter(e ->e.getEnquiryStatus().equals("Lost"))
					.collect(Collectors.toList()).size();

			response.setTotalEnquiriesCnt(totalCnt);
			response.setEnrolledCnt(enrolledCnt);
			response.setLostCnt(lostCnt);
			
		}
		
		return response;

	}
	
	@Override
	public List<String> getCourseNames() {
		List<CoursesEntity>findAll = courseRepo.findAll();
		
		List<String> names = new ArrayList <>();
		
		for(CoursesEntity entity : findAll) {
			names.add(entity.getCourseName());
		}
		
		return names;
	}
	
	
	@Override
	public List<String> getEnqStatuses() {
		List<EnquiryStatusEntity>findAll = statusRepo.findAll();
		
		List<String> statusList = new ArrayList<>();
		
		for(EnquiryStatusEntity entity : findAll) {
		       statusList.add(entity.getStatusName())  ;
		}
		return statusList;
	}
		
	@Override
	public boolean saveEnquiry(EnquiryForm form) {
		
		return false;
	}
	
	
}

	
	
	
	
	
	

	
