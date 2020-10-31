package com.recruitment.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.recruitment.entity.Application;
import com.recruitment.entity.Offer;
import com.recruitment.repository.ApplicationRepository;
import com.recruitment.repository.OfferRepository;
import com.recruitment.response.ResponseHandler;

@Service
public class ApplicationService {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	ApplicationRepository applicationRepository;

	public ResponseEntity<Object> applyForJob(Application application) {
		if (Objects.isNull(application)) {
			return ResponseHandler.badRequest("Invalid application");
		}
		if (Objects.isNull(application.getOffer())) {
			return ResponseHandler.badRequest("Invalid Job offer");
		}
		Application app = applicationRepository.findByEmail(application.getEmail());
		if (!Objects.isNull(app)) {
			return ResponseHandler.alreadyExist("Email should be unique");
		}
		Offer offer = offerRepository.findByJobTitle(application.getOffer());
		if (!Objects.isNull(offer)) {
			offer.addApplication(application);
			int noOfApplications = offer.getNoOfApplications();
			offer.setNoOfApplications(++noOfApplications);
			applicationRepository.save(application);
			offerRepository.save(offer);
			return ResponseHandler.success("You have successfully appied for Job");
		}
		return ResponseHandler.notFound("Job does not exist");
	}

	public ResponseEntity<Object> retrieveApplication(int applicationId) {
		if (applicationId <= 0) {
			return ResponseHandler.badRequest("Invalid application Id");
		}
		Optional<Application> application = applicationRepository.findById(applicationId);
		if (application.isPresent()) {
			return ResponseHandler.success(application, "Application");
		}
		return ResponseHandler.notFound("Application not found");
	}

	public ResponseEntity<Object> retrieveApplicationsPerOffer(String offer) {
		if (Objects.isNull(offer)) {
			return ResponseHandler.badRequest("Invalid Job offer");
		}
		Offer jobOffer = offerRepository.findByJobTitle(offer);
		if (!Objects.isNull(jobOffer)) {
			List<Application> applications = applicationRepository.findByOffer(offer);
			if (CollectionUtils.isEmpty(applications)) {
				return ResponseHandler.notFound("No Applicants found");
			}
			return ResponseHandler.success(applications, "All-Applications");
		}
		return ResponseHandler.notFound("Job does not exist");
	}

	public List<Application> retrieveApplications() {
		return applicationRepository.findAll();
	}
}
