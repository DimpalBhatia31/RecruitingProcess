package com.recruitment.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.recruitment.entity.Offer;
import com.recruitment.response.ResponseHandler;
import com.recruitment.service.OfferService;

@RestController
@RequestMapping("offer")
public class OfferController {

	@Autowired
	OfferService offerService;

	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody Offer offer) {
		if (Objects.isNull(offer)) {
			return ResponseHandler.badRequest("Object can't be null");
		}
		if (Objects.isNull(offer.getJobTitle())) {
			return ResponseHandler.badRequest("Job title can't be null or empty");
		}
		return offerService.create(offer);
	}

	@GetMapping("/retrieveAllOffers")
	public ResponseEntity<Object> retrieveAllOffers() {

		List<Offer> jobs = offerService.retrieveAllOffers();
		return ResponseHandler.success(jobs, "All-Jobs");
	}

	@GetMapping("/retrieveOffer/{jobTitle}")
	private ResponseEntity<Object> retrieveOffer(@PathVariable String jobTitle) {
		if (Objects.isNull(jobTitle)) {
			return ResponseHandler.badRequest("Invalid Job title");
		}
		return offerService.retrieveOffer(jobTitle);
	}

}
