package com.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitment.entity.Offer;


public interface OfferRepository extends JpaRepository<Offer, Integer> {

	public Offer findByJobTitle(String jobTitle);

}
