package com.Reddy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Reddy.Binding.DashboardResponse;
import com.Reddy.Binding.EnquiryForm;
import com.Reddy.services.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/dashboard")
	public String dashboardpag(Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		DashboardResponse dashboard = enqService.getDashboardData(userId);
		
		model.addAttribute("dashboard",dashboard);
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiry(Model model) {
		
		//courses drop down
		List<String> courses = enqService.getCourseNames();
		
		// enq status drop down
		List<String> enqStatuses = enqService.getEnqStatuses();
		
		//create binding class obj
		EnquiryForm formObj = new EnquiryForm();
		
		//set data in model obj
		model.addAttribute("courseNames",courses);
		model.addAttribute("StatusNames",enqStatuses);
		model.addAttribute("formObj",formObj);
		
		return "add-enquiry";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiries() {
		return "view-enquiries";
	}
}
