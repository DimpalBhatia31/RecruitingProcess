package com.recruitment.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.recruitment.entity.Offer;
import com.recruitment.repository.OfferRepository;
import com.recruitment.response.ResponseHandler;

@Service
public class OfferService {

	@Autowired
	OfferRepository offerRepository;

	public ResponseEntity<Object> create(Offer offer) {
		if (Objects.isNull(offer)) {
			return ResponseHandler.badRequest("Object can't be null");
		}
		if (Objects.isNull(offer.getJobTitle())) {
			return ResponseHandler.badRequest("Job title can't be null or empty");
		}
		Offer job = offerRepository.findByJobTitle(offer.getJobTitle());
		if (!Objects.isNull(job)) {
			return ResponseHandler.alreadyExist("Job Title already exist");
		}
		offerRepository.save(offer);
		return ResponseHandler.success("Job has been created");

	}

	public List<Offer> retrieveAllOffers() {
		return offerRepository.findAll();
	}

	public ResponseEntity<Object> retrieveOffer(String jobTitle) {
		Offer offer = offerRepository.findByJobTitle(jobTitle);
		if (!Objects.isNull(offer)) {
			return ResponseHandler.success(offer, "Job");
		}
		return ResponseHandler.notFound("job don't exist");
	}

}
