package com.recruitment.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.recruitment.entity.Application;
import com.recruitment.response.ResponseHandler;
import com.recruitment.service.ApplicationService;

@RestController
@RequestMapping("application")
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;

	@PostMapping("/applyForJob")
	private ResponseEntity<Object> applyForJob(@RequestBody Application application) {
		if (Objects.isNull(application)) {
			return ResponseHandler.badRequest("Invalid application");
		}
		if (Objects.isNull(application.getOffer())) {
			return ResponseHandler.badRequest("Offer can't be null");
		}
		return applicationService.applyForJob(application);
	}

	@GetMapping("/retrieveApplication/{applicationId}")
	public ResponseEntity<Object> getApplication(@PathVariable int applicationId) {
		if (applicationId <= 0) {
			return ResponseHandler.badRequest("Invalid application Id");
		}
		return applicationService.retrieveApplication(applicationId);

	}

	@GetMapping("/retrieveApplicationsPerJob/{offer}")
	public ResponseEntity<Object> getApplicationsPerOffer(@PathVariable String offer) {
		if (Objects.isNull(offer)) {
			return ResponseHandler.badRequest("Offer can't be null");
		}
		return applicationService.retrieveApplicationsPerOffer(offer);
	}

	@GetMapping("retrieveApplications")
	public ResponseEntity<Object> retrieveApplications() {
		return ResponseHandler.success(applicationService.retrieveApplications(), "Applications");
	}

}
